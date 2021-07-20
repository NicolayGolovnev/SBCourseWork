<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 16.07.2021
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Справочник контрагентов</title>
    <meta charset="UTF-8">
    <link href="<c:url value="css/modalWindow.css"/>" rel="stylesheet" type="text/css">

    <script src="<c:url value="js/script.js"/>"></script>
</head>
<body>
<div>
    <div class="form-style-2">
        Список контрагентов
    </div>
    <hr/>
    <table>
        <tr>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>Номер счета</th>
            <th>БИК банка</th>
        </tr>
        <c:forEach items="${counterAgentsFromServer}" var="counteragent">
            <tr>
                <td>${counteragent.name}</td>
                <td>${counteragent.inn}</td>
                <td>${counteragent.kpp}</td>
                <td>${counteragent.numberAccount}</td>
                <td>${counteragent.bik}</td>
                <td><button type="button" onclick="location.href='/counteragents/update/${counteragent.id}'">Edit</button></td>
                <td><button type="button" onclick="location.href='/counteragents/delete/${counteragent.id}'">Delete</button></td>
<%--                <td><a href="/counteragents/update/${counteragent.id}">Edit</a></td>--%>
<%--                <td><a href="/counteragents/delete/${counteragent.id}">Delete</a></td>--%>
            </tr>
        </c:forEach>

    </table>
    <hr/>
</div>

<%--<!-- Модальное окно №1 -->--%>
<%--<a href="#x" class="overlay" id="win1"></a>--%>
<%--<div class="popup">--%>
<%--    gfsdgsdfgsdgsdfg--%>
<%--    <a class="close"title="Закрыть" href="#close"></a>--%>
<%--</div>--%>

<button type="button" onclick="location.href='/counteragents/new'">Добавить пользователя</button>
<a href="/">Назад</a>
</body>
</html>
