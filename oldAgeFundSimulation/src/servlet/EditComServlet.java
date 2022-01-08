package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ResultsDAO;


@WebServlet("/EditComServlet")
public class EditComServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String selectName = request.getParameter("simName");
            ResultsDAO dao = new ResultsDAO();
            String comment = dao.findByNameCom(selectName);
            request.setAttribute("flag", "2");
            request.setAttribute("selectName", selectName);
            request.setAttribute("comment", comment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/save.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
}
