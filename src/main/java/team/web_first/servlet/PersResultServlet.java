package team.web_first.servlet;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import team.web_first.algorithms.DescribeResult;
import team.web_first.algorithms.SqlSessionFactoryUtil;
import team.web_first.javabean.PersResult;
import team.web_first.javabean.User;
import team.web_first.mapper.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PersResultServlet", urlPatterns = "/PersResultServlet")
public class PersResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        try {
            /**
             * 获得Session 对象 user
             * 查询最新Id记录
             */
            User user = (User) request.getSession().getAttribute("user");
            int recordId = userMapper.getRecord(user.getUserID());

            /**
             * 记录判断
             */
            if (recordId != 0) {
                DescribeResult describeResult = new DescribeResult(recordId);
                PersResult persResult = describeResult.getPersResultOne();
                request.getSession().setAttribute("user", user);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new JSONObject(persResult).toString());
            } else {
                /**
                 * 查询得到无记录
                 */
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 会话无用户
             */
            response.sendRedirect("/Urban_Road_Safety_Analysis/login.html");
        } finally {
            sqlSession.close();
        }
    }

}
