package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 入力画面にフォワード
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/error");
        }
    }
    // 入力値全クリアして、入力画面にフォワード
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
}
