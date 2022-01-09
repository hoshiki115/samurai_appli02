<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>老後資金シミュレーション</title>
    </head>
    <body>
        <h1>老後資金シミュレーションへようこそ！</h1>
        <form action="/oldAgeFundSimulation/InputServlet" method="get">
            <input type="submit" value="新規シミュレーション">
        </form>
        <br>
        <form action="/oldAgeFundSimulation/ResManageServlet" method="get">
            <input type="submit" value="過去のシミュレーション結果を表示">
        </form>
    </body>
</html>