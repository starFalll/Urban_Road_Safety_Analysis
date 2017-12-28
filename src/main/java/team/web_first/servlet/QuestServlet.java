package team.web_first.servlet;

import org.apache.ibatis.session.SqlSession;
import team.web_first.algorithms.SqlSessionFactoryUtil;
import team.web_first.javabean.FactorA;
import team.web_first.javabean.FactorB;
import team.web_first.javabean.FactorC;
import team.web_first.javabean.FactorD;
import team.web_first.mapper.FactorMapper;
import team.web_first.mapper.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class QuestServlet
 * 问卷处理 Servlet
 * AJAX 方式
 */

@WebServlet("/QuestServlet")
public class QuestServlet extends HttpServlet {
    private final int NUM = 6;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得选项参数
        String[] paramValues = request.getParameterValues("options[]");
        boolean[] aValues = new boolean[NUM];
        boolean[] bValues = new boolean[NUM];
        boolean[] cValues = new boolean[NUM];
        boolean[] dValues = new boolean[NUM];
        SqlSession sqlSession = null;

        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);

        //转换参数
        for (int i = 0; i < NUM; i++) {
            aValues[i] = Boolean.valueOf(paramValues[i]);
        }

        for (int i = NUM - 3; i < NUM + 3; i++) {
            bValues[i - (NUM - 3)] = Boolean.valueOf(paramValues[i]);
        }

        for (int i = NUM + 3; i < 2 * NUM + 3; i++) {
            dValues[i - (NUM + 3)] = Boolean.valueOf(paramValues[i]);
        }

        for (int i = NUM + 9; i < 3 * NUM + 3; i++) {
            cValues[i - (NUM + 9)] = Boolean.valueOf(paramValues[i]);
        }


        //写入数据库
        FactorA factorA = new FactorA();
        factorA.setA1(aValues[0]);
        factorA.setA2(!aValues[1]);
        factorA.setA3(aValues[2]);
        factorA.setA4(!aValues[3]);
        factorA.setA5(!aValues[4]);
        factorA.setA6(!aValues[5]);

        FactorB factorB = new FactorB();
        factorB.setB1(bValues[0]);
        factorB.setB2(bValues[1]);
        factorB.setB3(bValues[2]);
        factorB.setB4(bValues[3]);
        factorB.setB5(bValues[4]);
        factorB.setB6(bValues[5]);

        FactorD factorD = new FactorD();
        factorD.setD1(dValues[0]);
        factorD.setD2(dValues[1]);
        factorD.setD3(dValues[2]);
        factorD.setD4(dValues[3]);
        factorD.setD5(dValues[4]);
        factorD.setD6(dValues[5]);

        FactorC factorC = new FactorC();
        factorC.setC1(cValues[0]);
        factorC.setC2(cValues[1]);
        factorC.setC3(cValues[2]);
        factorC.setC4(cValues[3]);
        factorC.setC5(cValues[4]);
        factorC.setC6(cValues[5]);

        factorMapper.addFactorA(factorA);
        factorMapper.addFactorB(factorB);
        factorMapper.addFactorC(factorC);
        factorMapper.addFactorD(factorD);
        try {
            int userId = Integer.valueOf(request.getParameter("userId"));
            if (userId != 0) {
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                userMapper.addRecord(userId, factorA.getRiskPerceptionId());
            }
        } catch (NumberFormatException e) {

        }


        //事务提交 关闭连接
        sqlSession.commit();
        sqlSession.close();
    }
}
