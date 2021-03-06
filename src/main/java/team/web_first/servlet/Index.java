package team.web_first.servlet;

import team.web_first.javabean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Index
 * Servlet 登录后主页处理Servlet
 * 检查session中用户对象
 * 有则服务器转发到 /WEB-INF/index.jsp 页面
 * 无则退出
 * @author a9043
 */
@WebServlet(name = "Index", urlPatterns = "/index")
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            return;
        } else {
            response.sendRedirect("/Urban_Road_Safety_Analysis/welcome.html");
            return;
        }
    }
}
