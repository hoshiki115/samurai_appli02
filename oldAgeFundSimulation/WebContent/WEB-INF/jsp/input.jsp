<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.InputIncome,model.InputCost" %>
<% InputIncome imputIncome = (InputIncome) session.getAttribute("inputIncome");
InputCost imputCost = (InputCost) session.getAttribute("inputCost");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>老後資金シミュレーション</title>
        <link rel="stylesheet" type="text/css" href="/oldAgeFundSimulation/css/style.css">
    </head>
    <body>
        <h2>あなたの現状について教えてください</h2>
        <form action="/oldAgeFundSimulation/InputServlet" method="Post">
        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
            <input type="submit" value="入力値全クリア">
        </form>

        <form action="/oldAgeFundSimulation/InputResServlet" name="form1" method="post">
            <table border="1" rules="none" class="border">
                <tr>
                    <th class="left">収入の部</th>
                </tr>
                <tr>
                    <td>　現在の年齢（４０以上）：<span class="required">必須</span>　
                        <input type="number" name="currentAge" value="${inputIncome.currentAge}">歳</td>　
                    <td>手取り年収：<span class="required">必須</span>　　　　
                        <input type="number" name="currentIncome" value="${inputIncome.currentIncome}">万円/年　</td>
                </tr>
                <tr>
                    <td>　定年は何歳？：<span class="required">必須</span>　　　　　　
                        <input type="number" name="retireAge" value="${inputIncome.retireAge}">歳</td>
                    <td>退職金：<span class="required">必須</span>　　　　　　
                        <input type="number" name="severanceIncome" value="${inputIncome.severanceIncome}">万円</td>
                </tr>
                <tr>
                    <td>　年金受給は何歳？：<span class="required">必須</span>　　　　
                        <input type="number" name="pensionAge" value="${inputIncome.pensionAge}">歳</td>
                    <td>年金受給額（年）：<span class="required">必須</span>　
                        <input type="number" name="pensionIncome" value="${inputIncome.pensionIncome}">万円/年</td>
                </tr>
                <tr>
                    <td>　</td>
                    <td>現在の預貯金：<span class="required">必須</span>　　　
                        <input type="number" name="currentSaving" value="${inputIncome.currentSaving}">万円　</td>
                </tr>
                <tr>
                    <td>　定年後の収入：</td>
                </tr>
                <tr>
                    <td>　　定期的な手取り年収（再就職、フリーランス等）：</td>
                    <td>　　　　　　　　　　　　<input type="number" name="regIncomeAge" value="${inputIncome.regIncomeAge}">歳まで</td>
                    <td>　　　　<input type="number" name="regIncome" value="${inputIncome.regIncome}">万円/年　</td>
                </tr>
                <tr>
                    <td>　　その他収入（株式売却等）：</td>
                    <td>　　　　　　　　　　　　<input type="number" name="otherIncomeAge" value="${inputIncome.otherIncomeAge}">歳で</td>
                    <td>　　　　<input type="number" name="otherIncome" value="${inputIncome.otherIncome}">万円/年　</td>
                </tr>
                <tr><td>　</td></tr>
            </table>
            <table border="1" rules="none" class="border">
                <tr>
                    <th class="left">支出の部</th>
                </tr>
                <tr>
                    <td>　必要な生活費</td>
                </tr>
                <tr>
                    <td>　　毎月必ず発生する費用：<span class="required">必須</span></td>
                    <td>　　　　　　　<input type="number" name="monthlyCost" value="${inputCost.monthlyCost}" onblur="calc()">万円/月</td>
                    <td>×12 ＝　<input type="number" name="yearlyCost" value="0" class="font-border" readonly>万円/年　</td>
                </tr>
                <tr>
                    <td colspan="2">　　（例：食費・生活必需品・住居費・水道光熱費・通信費・交通費・月払い保険料・その他）　　　</td>
                </tr>
                <tr>
                    <td>　　毎年必ず発生する費用：<span class="required">必須</span></td>
                    <td>　</td>
                    <td>　　　　 <input type="number" name="everyYearCost" value="${inputCost.everyYearCost}">万円/年</td>
                </tr>
                <tr>
                    <td>　　（例：税金・年払い保険料・交際費・その他）</td>
                </tr>
                <tr>
                    <td>　　期限付きで毎年発生する費用１：</td>
                    <td>　　　　　　　<input type="number" name="timeLimitAge1" value="${inputCost.timeLimitAge1}">歳まで</td>　
                    <td>　　　　 <input type="number" name="timeLimitCost1" value="${inputCost.timeLimitCost1}">万円/年</td>
                </tr>
                <tr>
                    <td>　　（例：教育費・年払い保険料・ローン返済・その他）</td>
                </tr>
                <tr>
                    <td>　　期限付きで毎年発生する費用２：</td>
                    <td>　　　　　　　<input type="number" name="timeLimitAge2" value="${inputCost.timeLimitAge2}">歳まで</td>
                    <td>　　　　 <input type="number" name="timeLimitCost2" value="${inputCost.timeLimitCost2}">万円/年</td>
                </tr>
                <tr><td>　</td></tr>
                <tr>
                    <td>　特別費</td>
                </tr>
                <tr>
                    <td>　　期限付きで予定する費用：</td>
                    <td>　　　　　　　<input type="number" name="expectedAge" value="${inputCost.expectedAge}">歳までに</td>
                    <td>　　　　 <input type="number" name="expectedCost" value="${inputCost.expectedCost}">万円</td>
                </tr>
                <tr>
                    <td>　　（例：リフォーム・子供の結婚祝い・イベント・その他）</td>
                </tr>
                <tr>
                    <td>　　毎年予定する費用：</td>
                    <td>
                        <select id="plan">
                        <option value="noPlan" selected>選択してください</option>
                        <option value="planA">プランA：節約タイプ（1万円/月）</option>
                        <option value="planB">プランB：標準タイプ（5万円/月）</option>
                        <option value="planC">プランC：贅沢タイプ（10万円/月）</option>
                        <option value="planF">プランF：金額を入力してください</option>
                        </select>
                    </td>
                    <td>　　　　 <input type="number" name="planCostF" value="${inputCost.planCostF}" id="planCost">万円/年</td>
                </tr>
                <tr>
                    <td>　　（例：娯楽・旅行・イベント・その他）</td>
                </tr>
                <tr><td>　</td></tr>
            </table>
            <p>　シミュレーション終了年齢（100以下）：<span class="required">必須</span>
                <input type="number" name="endAge" value="${inputIncome.endAge}">歳まで　　
                <input type="submit" value="シミュレーション結果の表示">　　　　　　　
                <a href="/oldAgeFundSimulation/Top">＜＜ TOPに戻る</a>
            </p>

            <c:if test="${errorList.size() != 0 && errorList != null}">
                <p class="red">エラーメッセージ</p>
                <c:forEach var="list" items="${errorList}">
                    <p class="red">　　${list} </p>
                </c:forEach>
            </c:if>
        </form>
        <script type="text/javascript" src="/oldAgeFundSimulation/js/input.js"></script>
    </body>
</html>