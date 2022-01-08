package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ResultsDAO;

/**
 * Servlet implementation class SaveComServlet
 */
@WebServlet("/SaveComServlet")
public class SaveComServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String selectName = request.getParameter("selectName");
            String comment = request.getParameter("comment");
            ResultsDAO dao = new ResultsDAO();
            dao.updateCom(selectName, comment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ResManageServlet");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }

}
