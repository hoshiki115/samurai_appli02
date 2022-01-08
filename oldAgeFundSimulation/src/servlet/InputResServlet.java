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

import model.Calc;
import model.InputArray;
import model.InputCost;
import model.InputIncome;

@WebServlet("/InputResServlet")
public class InputResServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 結果画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
    // 入力値を取得
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            InputIncome inputIncome = new InputIncome(request);
            InputCost inputCost = new InputCost(request);
            InputArray inputArray = new InputArray(inputIncome, inputCost);

            // 入力値をチェック
            List<String> errorList = inputArray.inputCheck();

            // 入力値をセッションスコープに保存
            HttpSession session = request.getSession();
            session.setAttribute("inputIncome", inputIncome);
            session.setAttribute("inputCost", inputCost);

            // エラーメッセージをリクエストスコープに保存
            request.setAttribute("errorList", errorList);

            // エラーがある場合、入力画面にフォワード
            if(errorList.size() != 0) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
                dispatcher.forward(request, response);
            } else {
                // エラーがない場合、入力値からグラフ表示用の値を計算
                int [][] outputArray = Calc.calc(inputIncome, inputCost);
                // 計算値が格納されている配列をListに変換
                ArrayList<Integer> ageList = new ArrayList<>();
                for(int i = 0; i < outputArray[0].length; i++) {
                    ageList.add(outputArray[0][i]);
                }
                ArrayList<Integer> incomeList = new ArrayList<>();
                for(int i = 0; i < outputArray[1].length; i++) {
                    incomeList.add(outputArray[1][i]);
                }
                ArrayList<Integer> costList = new ArrayList<>();
                for(int i = 0; i < outputArray[2].length; i++) {
                    costList.add(outputArray[2][i]);
                }
                ArrayList<Integer> balanceList = new ArrayList<>();
                for(int i = 0; i < outputArray[3].length; i++) {
                    balanceList.add(outputArray[3][i]);
                }
                ArrayList<Integer> savingList = new ArrayList<>();
                for(int i = 0; i < outputArray[4].length; i++) {
                    savingList.add(outputArray[4][i]);
                }
                // 計算した値をリクエストスコープに保存
                session.setAttribute("ageList", ageList);
                session.setAttribute("incomeList", incomeList);
                session.setAttribute("costList", costList);
                session.setAttribute("balanceList", balanceList);
                session.setAttribute("savingList", savingList);
                // 結果画面にフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/oldAgeFundSimulation/Error");
        }
    }
}