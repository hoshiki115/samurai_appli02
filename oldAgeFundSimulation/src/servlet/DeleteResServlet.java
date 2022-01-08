package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ResultsDAO;
import model.GetSaveResLogic;
import model.SaveResult;

@WebServlet("/DeleteResServlet")
public class DeleteResServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<String> checkNames = new ArrayList<String>();
            String [] simNames = request.getParameterValues("name");
            for(int i = 0; i < simNames.length; i++) {
                checkNames.add(simNames[i]);
            }
            HttpSession session = request.getSession();
            int msg = (int) session.getAttribute("msg");
            ResultsDAO dao = new ResultsDAO();
            dao.delete(checkNames);
            int saveNum = dao.count();
            if(saveNum == 0) {
                msg = 1; // 保存された結果がない場合
            } else {
                // 保存されたシミュレーション結果を取得して、リクエストスコープに保存
                GetSaveResLogic getSaveResLogic = new GetSaveResLogic();
                List<SaveResult> saveList = getSaveResLogic.execute();
                request.setAttribute("saveList", saveList);
            }
            if(msg == 3) {
                msg = 5; // 保存件数が10件を超えていたのを削除したため3から5に変更
            }
            session.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resManage.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
}