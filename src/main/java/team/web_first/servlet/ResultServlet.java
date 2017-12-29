package team.web_first.servlet;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import team.web_first.algorithms.Apriori;
import team.web_first.algorithms.SqlSessionFactoryUtil;
import team.web_first.javabean.Result;
import team.web_first.mapper.FactorMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "ResultServlet", urlPatterns = "/ResultServlet")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 获得 Result[] results
         */
        JSONArray resJson = new JSONArray();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        Result[] results = factorMapper.showResult();
        sqlSession.close();

        /**
         * results 转换成 JSONObject
         */
        for (Result result : results) {
            JSONObject ele = new JSONObject();
            ele.put("name1", result.getfChar());
            ele.put("name2", result.getsChar());
            ele.put("confidence", result.getConfidence());
            resJson.put(ele);
        }

        /**
         * 传递json字符串
         */
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resJson.toString());
        return;
    }
}
