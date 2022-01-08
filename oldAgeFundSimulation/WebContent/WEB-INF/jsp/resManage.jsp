<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.SaveResult, java.util.List" %>
<% List<SaveResult> saveList = (List<SaveResult>) request.getAttribute("saveList");
int msg = (int)session.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>老後資金シミュレーション</title>
        <link rel="stylesheet" type="text/css" href="/oldAgeFundSimulation/css/style.css">
    </head>
    <body>
        <h1>シミュレーション結果管理</h1>
        <c:if test="${msg == -1}">
            <p class="red">　　<c:out value="DBに接続できません。DBに接続してから再度実行してください。" /></p>
        </c:if>
        <c:if test="${msg == 1}">
            <p class="red">　　<c:out value="保存された結果はありません" /></p>
        </c:if>
        <c:if test="${msg == 2 || msg == 5}">
            <p>　　<c:out value="結果を表示したいシミュレーション名称をクリックしてください" /></p>
        </c:if>
        <c:if test="${msg == 3}">
            <p class="red">　　<c:out value="保存件数が10件を超えます" /></p>
            <p class="red">　　<c:out value="不要なものを削除してから「入力画面に戻る」をクリックし、シミュレーション結果を保存し直してください" /></p>
        </c:if>
        <c:if test="${msg == 4}">
            <p class="blue">　　<c:out value="シミュレーション結果が正常に保存されました" /></p>
            <p>　　<c:out value="結果を表示したいシミュレーション名称をクリックしてください" /></p>
        </c:if>
        <c:if test="${msg == 2 || msg == 3 || msg == 4 || msg == 5}">

        <p>※保存できる件数は10件まで</p>
        　 <input id="checkAll" type="checkbox" form="bulkDel" name="name" value="checkall">全選択
        <table border="1" class="border">
            <tr>
                <td class="aqua">　削除　</td>
                <td class="lime1">　No.　</td>
                <td class="lime1">　シミュレーション名称　</td>
                <td class="lime2">保存日</td>
                <td class="lime3">　　　　コメント　　　　</td>
                <td class="lime4"></td>
            </tr>
            <c:forEach var="i" items="${saveList}" varStatus="j">
                <tr>
                    <th class="cyan"><input type="checkbox" form="bulkDel" class="chk" name="name" value="${i.getSimName()}"></th>
                    <form action="/oldAgeFundSimulation/EditComServlet" method="get">
                        <c:choose>
                            <c:when test="${msg == 4 && j.count == 1}">
                                <td class="lightyellow1">　<c:out value="${j.count}" /></td>
                            </c:when>
                            <c:otherwise>
                                <td>　<c:out value="${j.count}" /></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${msg == 3}">
                                <td>　<c:out value="${i.getSimName()}" />　</td>
                            </c:when>
                            <c:when test="${msg == 4 && j.count == 1}">
                                <td class="lightyellow1">　<a href="/oldAgeFundSimulation/DispResServlet?simName=${i.getSimName()}&num=${j.count}">
                                    ${i.getSimName()}</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>　<a href="/oldAgeFundSimulation/DispResServlet?simName=${i.getSimName()}&num=${j.count}">
                                    ${i.getSimName()}</a></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${msg == 4 && j.count == 1}">
                                <td class="lightyellow1">　<c:out value="${i.getSaveDate()}" />　</td>
                                <td class="lightyellow2">　<c:out value="${i.getSimCom()}" /></td>
                            </c:when>
                            <c:otherwise>
                                <td>　<c:out value="${i.getSaveDate()}" />　</td>
                                <td class="border-right">　<c:out value="${i.getSimCom()}" /></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${msg == 4 && j.count == 1}">
                                <td class="lightyellow3">
                                    <input type="hidden" name="simName" value="${i.getSimName()}">
                                    <input type="image" src="/oldAgeFundSimulation/img/icons8-32.png" alt="編集" >
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="border-left">
                                    <input type="hidden" name="simName" value="${i.getSimName()}">
                                    <input type="image" src="/oldAgeFundSimulation/img/icons8-32.png" alt="編集" >
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:if test="${msg == 2 || msg == 3 || msg ==4 || msg == 5}">
            <form id="bulkDel" name="delConfirm"><input name="btn1" type="submit" id="btn2" value="削除">　　
                <c:out value="${'削除したいものの□にチェックマークを付けて「削除」をクリックしてください'}" /></form>
        </c:if>
        </c:if>
        <c:if test="${msg == 4 || msg == 5}">
            <form action="/oldAgeFundSimulation/InputServlet" method="get">
                <p>　　　　　　　　　　　　　　　　<input type="submit" value="入力画面に戻る">　　　　　　　　　　　
            </form>
        </c:if>
        <p>　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　<a href="/oldAgeFundSimulation/Top">＜＜ TOPに戻る</a></p>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script type="text/javascript" src="/oldAgeFundSimulation/js/resManage.js"></script>
    </body>
</html>