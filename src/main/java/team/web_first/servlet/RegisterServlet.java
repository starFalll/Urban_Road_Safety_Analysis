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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * RegisterServlet
 * Servlet implementation class RegisterServlet
 * 用户注册 Servlet
 * @author a9043
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset = UTF-8");
        response.setCharacterEncoding("UTF-8");

        /**
         * 设定账户创建日期,及有效日期
         */
        Date userCurrentTime = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(userCurrentTime);
        calendar.add(Calendar.DATE, 90);
        Date userValidTime = calendar.getTime();

        String userEncryptPassword;
        String userPassword;
        User tempUser = new User();
        /**
         * 获得参数并
         * 处理成User 临时对象
         */
        try {
            userPassword = request.getParameter("password");
            userEncryptPassword = UserPasswordEncrypt.encrypt(userPassword);
            tempUser.setUserName(request.getParameter("username"));
            tempUser.setUserPassword(userEncryptPassword);
            tempUser.setUserCreateTime(userCurrentTime);
            tempUser.setUserValidTime(userValidTime);
        } catch (NullPointerException e) {
            response.sendRedirect("/Urban_Road_Safety_Analysis/login.html");
            e.printStackTrace();
        }

        /**
         * 检查该临时用户是否存在
         * 是则返回信息并重定向login.html
         * 否则保存用户记录并重定向到index Servlet
         */
        SqlSession sqlSession = null;
        try {
            User checkedUser;
            sqlSession = SqlSessionFactoryUtil.openSqlsession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            checkedUser = userMapper.getUserByName(tempUser.getUserName());
            if (checkedUser != null) {
                response.sendRedirect("/Urban_Road_Safety_Analysis/login.html?userExist=1");
                return;
            } else {
                userMapper.addUser(tempUser);
                sqlSession.commit();
                request.getSession().setAttribute("user", tempUser);
                String url = new String("/Urban_Road_Safety_Analysis/index?id=" + tempUser.getUserID());
                response.sendRedirect(url);
                return;
            }
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        //存入数据库
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
