<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<Integer> ageList = (ArrayList<Integer>)session.getAttribute("ageList");
ArrayList<Integer> incomeList = (ArrayList<Integer>)session.getAttribute("incomeList");
ArrayList<Integer> costList = (ArrayList<Integer>)session.getAttribute("costList");
ArrayList<Integer> balanceList = (ArrayList<Integer>)session.getAttribute("balanceList");
ArrayList<Integer> savingList = (ArrayList<Integer>)session.getAttribute("savingList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>老後資金シミュレーション</title>
        <link rel="stylesheet" type="text/css" href="/oldAgeFundSimulation/css/style.css">
    </head>
    <body>
        <h1>老後資金シミュレーション結果</h1>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
        <canvas id="ex_chart"></canvas>
        <script>
            var age = ${ageList};
            var income = ${incomeList};
            var cost = ${costList};
            var balance = ${balanceList};
            var saving = ${savingList};
        </script>
        <script type="text/javascript" src="/oldAgeFundSimulation/js/result.js"></script>
        
        <form action="/oldAgeFundSimulation/InputServlet" method="get">
        <input type="submit" value="入力画面に戻る">　
        </form>
        <br>
        <form action="/oldAgeFundSimulation/CalResServlet" method="get">
        <p>　　　　　　　　　　　　　　　　　　　　<input type="submit" value="シミュレーション結果を保存">　　　　　　　　　　　　　　　　　　　　　
        <a href="/oldAgeFundSimulation/Top">＜＜ TOPに戻る</a></p>
        </form>
        
</body>
</html>
