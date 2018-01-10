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

/**
 * Result2Servlet
 * 获得
 * 总分析数据2
 * @author a9043
 */
@WebServlet(name = "Result2Servlet", urlPatterns = "/Result2Servlet")
public class Result2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray resJson = new JSONArray();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);

        /**
         * 获得总分析数据
         */
        ResultTwo resultTwos[] = factorMapper.showResultTwo();

        /**
         * 转换成JSON对象
         */
        for (ResultTwo resultTwo : resultTwos) {
            resJson.put(resultTwo.getConfidenceTwo());
        }

        /**
         * response返回JSON 对象字符串
         */
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resJson.toString());

        sqlSession.close();
    }
}
