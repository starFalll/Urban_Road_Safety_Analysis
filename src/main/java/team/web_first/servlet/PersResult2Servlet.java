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

/**
 * PersResult2Servlet
 * 处理个人分析2 的Servlet
 * @author a9043
 */
@WebServlet(name = "PersResult2Servlet", urlPatterns = "/PersResult2Servlet")
public class PersResult2Servlet extends HttpServlet {
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
             * 进行记录判断
             */
            if (recordId != 0) {
                /**
                 * 有记录 调用describeResult处理并返回分析结果
                 */
                DescribeResult describeResult = new DescribeResult(recordId);
                String pRes2 = describeResult.getPersResultTwo();
                request.getSession().setAttribute("user", user);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(pRes2);
            } else {
                /**
                 * 查询得到无记录则
                 * 返回错误状态码
                 */
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 会话无用户则
             * 返回欢迎页
             */
            response.sendRedirect("/Urban_Road_Safety_Analysis/welcome.html");
        } finally {
            sqlSession.close();
        }
    }
}
