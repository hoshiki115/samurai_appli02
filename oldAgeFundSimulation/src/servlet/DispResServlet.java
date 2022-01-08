package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetSaveInputLogic;
import model.InputCost;
import model.InputIncome;

@WebServlet("/DispResServlet")
public class DispResServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int msg = (int)session.getAttribute("msg");
            String selectName = request.getParameter("simName");
            String selectNum = request.getParameter("num");

            if(msg == 4 && selectNum.equals("1")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
                dispatcher.forward(request, response);
            } else if((msg == 4 && !(selectNum.equals("1"))) || msg == 5) {
                session.setAttribute("selectName", selectName);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/overwriteConf.jsp");
                dispatcher.forward(request, response);
            } else {
                GetSaveInputLogic getSaveInputLogic = new GetSaveInputLogic();
                Object [] input = getSaveInputLogic.execute(selectName);
                InputIncome inputIncome = (InputIncome) input[0];
                InputCost inputCost = (InputCost) input[1];
                session.setAttribute("inputIncome", inputIncome);
                session.setAttribute("inputCost", inputCost);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String selectName = (String)session.getAttribute("selectName");

            GetSaveInputLogic getSaveInputLogic = new GetSaveInputLogic();
            Object [] input = getSaveInputLogic.execute(selectName);
            InputIncome inputIncome = (InputIncome) input[0];
            InputCost inputCost = (InputCost) input[1];
            session.setAttribute("inputIncome", inputIncome);
            session.setAttribute("inputCost", inputCost);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
}
