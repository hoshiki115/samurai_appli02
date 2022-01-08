<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String errorMsg = (String) request.getAttribute("errorMsg");
String flag = (String) request.getAttribute("flag");
String selectName = (String) request.getAttribute("selectName");
String comment = (String) request.getAttribute("comment"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>老後資金シミュレーション</title>
        <link rel="stylesheet" type="text/css" href="/oldAgeFundSimulation/css/style.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${flag == 1}">
                <h1>シミュレーション結果の保存</h1>
                <form action="/oldAgeFundSimulation/ResManageServlet" method="post">
                    <p>シミュレーション</P>
                    <p>　名称（10文字以内）【必須】：<input type="text" name="simName"></p>
                    <p>　　　コメント（50文字以内）：<textarea class="textarea" rows="2" cols="22" name="simCom"></textarea></p>
                    <p>　　　　　　　　　<input type="submit" value="保存">　　　　　　　
                        <a href="/oldAgeFundSimulation/InputResServlet">＜＜ キャンセル</a></p>
                </form>
            </c:when>
            <c:otherwise>
                <h1>シミュレーション結果コメントの編集</h1>
                <form action="/oldAgeFundSimulation/SaveComServlet" method="get">
                    <p>　　　シミュレーション名称：　<input type="text" name="selectName" value="${selectName}" 
                        style="font-size:16px; border:none;" readonly></p>
                    <p>　　　コメント（50文字以内）：<textarea class="textarea" rows="2" cols="22" name="comment" 
                        placeholder="${comment}"></textarea></p>
                    <p>　　　　　　　　　<input type="submit" value="保存">　　　　　　　
                        <a href="/oldAgeFundSimulation/ResManageServlet">＜＜ キャンセル</a></p>
                </form>
            </c:otherwise>
        </c:choose>
        <c:if test="${errorMsg != null}">
            <p class="red"><c:out value="${errorMsg}" /></p>
        </c:if>
     </body>
</html>