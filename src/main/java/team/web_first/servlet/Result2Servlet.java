package team.web_first.servlet;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import team.web_first.algorithms.SqlSessionFactoryUtil;
import team.web_first.javabean.ResultTwo;
import team.web_first.mapper.FactorMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Result2Servlet", urlPatterns = "/Result2Servlet")
public class Result2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray resJson = new JSONArray();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        ResultTwo resultTwos[] = factorMapper.showResultTwo();

        for (ResultTwo resultTwo : resultTwos) {
            resJson.put(resultTwo.getConfidenceTwo());
        }

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resJson.toString());

        sqlSession.close();
    }
}
