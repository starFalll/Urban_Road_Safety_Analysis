package team.web_first.servlet;

import org.apache.ibatis.session.SqlSession;
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

@WebServlet("/RefreshServlet")
public class RefreshServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            try {
                SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession();
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                int recordId = userMapper.getRecord(user.getUserID());
                DescribeResult describeResult = new DescribeResult(recordId);
                PersResult persResult = describeResult.getPersResult();
                request.getSession().setAttribute("persResult", persResult);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/Urban_Road_Safety_Analysis/index.jsp");
            } catch (Exception e) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/Urban_Road_Safety_Analysis/index.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("/Urban_Road_Safety_Analysis/index.jsp");
        }

    }
}
