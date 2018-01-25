package team.web_first.servlet;

import org.apache.ibatis.session.SqlSession;
import team.web_first.algorithms.SqlSessionFactoryUtil;
import team.web_first.algorithms.UserPasswordEncrypt;
import team.web_first.javabean.User;
import team.web_first.mapper.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * LoginServlet
 * Servlet implementation class LoginServlet
 * 登录处理 Servlet
 * @author a9043
 */
@WebServlet(name = "login", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /**
         * 移除前用户对象
         * 设定request&response字符集为UTF-8
         */
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("persResult");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset = UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userName = "";
        String userPassword;
        String userEncryptPassword = "";

        /**
         * 获得登录参数
         * 用户名&密码
         * 对登录密码进行加密
         */
        try {
            userName = request.getParameter("username");
            userPassword = request.getParameter("password");
            userEncryptPassword = UserPasswordEncrypt.encrypt(userPassword);
        } catch (NullPointerException e) {
            response.sendRedirect("/Urban_Road_Safety_Analysis/login.html?checkUnValid=1");
            e.printStackTrace();
        }

        /**
         * 对参数内容检验
         * 是否合法用户
         * 是则进入主页
         * 否则返回欢迎页面
         */
        User checkedUser;
        try (SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            checkedUser = userMapper.getUserByAbs(userName, userEncryptPassword);
            if (checkedUser != null) {
                if (checkedUser.getUserValidTime() == null || checkedUser.getUserValidTime().after(new Date())) {
                    request.getSession().setAttribute("user", checkedUser);
                    String url = new String("/Urban_Road_Safety_Analysis/index?id=" + checkedUser.getUserID());
                    response.sendRedirect(url);
                    return;
                } else {
                    response.sendRedirect("/Urban_Road_Safety_Analysis/login.html?userExpired=1");
                    return;
                }
            } else {
                response.sendRedirect("/Urban_Road_Safety_Analysis/login.html?checkUnValid=1");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Urban_Road_Safety_Analysis/welcome.html");
            return;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
