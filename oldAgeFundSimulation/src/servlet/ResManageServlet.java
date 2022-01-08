package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ResultsDAO;
import model.CheckSaveNameLogic;
import model.GetSaveResLogic;
import model.InputCost;
import model.InputIncome;
import model.PostSaveResLogic;
import model.SaveResult;

@WebServlet("/ResManageServlet")
public class ResManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int msg = 0;
            // 保存件数のチェック
            ResultsDAO dao = new ResultsDAO();
            int saveNum = dao.count();
            if(saveNum == -1) {
                msg = -1; //DBに接続できない場合
            } else if(saveNum == 0) {
                msg = 1; // 保存された結果がない場合
            } else {
                msg = 2; // 結果を表示したいシミュレーション名称をクリック
                // 保存されたシミュレーション結果を取得して、リクエストスコープに保存
                GetSaveResLogic getSaveResLogic = new GetSaveResLogic();
                List<SaveResult> saveList = getSaveResLogic.execute();
                request.setAttribute("saveList", saveList);
            }
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resManage.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("flag","1");
            int msg = 0;
            // 保存件数のチェック
            ResultsDAO dao = new ResultsDAO();
            int saveNum = dao.count();
            if(saveNum == -1) {
                msg = -1; //DBに接続できない場合
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resManage.jsp");
                dispatcher.forward(request, response);
            }
            String simName = request.getParameter("simName");
            String simCom = request.getParameter("simCom");
            CheckSaveNameLogic checkSaveNameLogic = new CheckSaveNameLogic();
            boolean isCheck = checkSaveNameLogic.execute(simName);

            if(saveNum >= 10) {
                msg = 3; // 保存件数が10件を超える場合
            } else if(simName == "" || simName == null) {
                // シミュレーション名称の入力をチェック
                request.setAttribute("errorMsg", "シミュレーション名称が入力されていません");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/save.jsp");
                dispatcher.forward(request, response);
            } else if(simName.length() > 10) {
                // シミュレーション名称の文字数をチェック
                request.setAttribute("errorMsg", "シミュレーション名称 " + simName + " が10文字を超えています");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/save.jsp");
                dispatcher.forward(request, response);
            } else if(simCom.length() > 50) {
                // コメントの入力をチェック
                request.setAttribute("errorMsg", "コメントが5０文字を超えています");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/save.jsp");
                dispatcher.forward(request, response);
            } else if(isCheck) {
                // シミュレーション名称の重複をチェック
                request.setAttribute("errorMsg", "シミュレーション名称 " + simName + " が重複しています");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/save.jsp");
                dispatcher.forward(request, response);
            } else {
                // 保存日を取得
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
                String saveDate = sdf.format(date);

                SaveResult saveResult = new SaveResult(simName, saveDate, simCom);
                HttpSession session = request.getSession();
                InputIncome inputIncome = (InputIncome) session.getAttribute("inputIncome");
                InputCost inputCost = (InputCost) session.getAttribute("inputCost");
                // シミュレーション結果を登録
                PostSaveResLogic postSaveResLogic = new PostSaveResLogic();
                postSaveResLogic.execute(saveResult, inputIncome, inputCost);
                msg = 4; // シミュレーション結果が正常に保存
            }
            // 保存されたシミュレーション結果を取得して、リクエストスコープに保存
            GetSaveResLogic getSaveResLogic = new GetSaveResLogic();
            List<SaveResult> saveList = getSaveResLogic.execute();
            request.setAttribute("saveList", saveList);

            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resManage.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
}
