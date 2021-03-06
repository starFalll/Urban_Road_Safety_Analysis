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


/**
 * ResultServlet
 * 获得总分析数据1
 * @author a9043
 */
@WebServlet(name = "ResultServlet", urlPatterns = "/ResultServlet")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 查询数据库
         * 获得 总分析数据 results
         */
        JSONArray resJson = new JSONArray();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        Result[] results = factorMapper.showResult();


        /**
         * results 转换成 JSON Object
         */
        for (Result result : results) {
            JSONObject ele = new JSONObject();
            ele.put("name1", result.getfChar());
            ele.put("name2", result.getsChar());
            ele.put("confidence", result.getConfidence());
            resJson.put(ele);
        }

        /**
         * response返回JSON 对象字符串
         */
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resJson.toString());
        sqlSession.close();
    }
}
