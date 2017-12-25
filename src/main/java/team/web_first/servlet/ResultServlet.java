package team.web_first.servlet;

import org.json.JSONArray;
import team.web_first.algorithms.Apriori;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray resArr = Apriori.getJson();
        response.setCharacterEncoding("UTF-8");
        System.out.println(resArr.toString());
        response.getWriter().write(resArr.toString());
        return;
    }
}
