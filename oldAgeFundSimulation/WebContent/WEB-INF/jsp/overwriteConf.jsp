<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>老後資金シミュレーション</title>
        <link rel="stylesheet" type="text/css" href="/oldAgeFundSimulation/css/style.css">
    </head>
    <body>
        <h2>入力データ上書き確認</h2>
        <p>　入力したデータが保存データに</P>
        <p>　上書きされますがよいですか？</p>
        <div class="display">
            <form action="/oldAgeFundSimulation/DispResServlet" method="post">
                <p>　　<input type="submit" value="ＯＫ">
            </form>
            <form action="/oldAgeFundSimulation/CancelServlet" method="get">
                <p>　　<input type="submit" value="キャンセル">
            </form>
        </div>
    </body>
</html>