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
<%--    <style>--%>
<%--        .backgroundForm {--%>
<%--            display: none;--%>
<%--            background: rgba(102, 102, 102, 0.5);--%>
<%--            width: 100%;--%>
<%--            height: 100%;--%>
<%--            position: absolute;--%>
<%--            top: 0;--%>
<%--            left: 0;--%>
<%--        }--%>
<%--        .formWindow {--%>
<%--            display: none;--%>
<%--            width: 300px;--%>
<%--            height: 50px;--%>
<%--            text-align: center;--%>
<%--            padding: 15px;--%>
<%--            border: 3px solid #0000cc;--%>
<%--            border-radius: 10px;--%>
<%--            color: #0000cc;--%>
<%--            position: absolute;--%>
<%--            top: 0;--%>
<%--            right: 0;--%>
<%--            bottom: 0;--%>
<%--            left: 0;--%>
<%--            margin: auto;--%>
<%--            background: #fff;--%>
<%--        }--%>
<%--        .backgroundForm:target {display: block;}--%>
<%--        .close {--%>
<%--            display: inline-block;--%>
<%--            border: 1px solid #0000cc;--%>
<%--            color: #0000cc;--%>
<%--            padding: 0 12px;--%>
<%--            margin: 10px;--%>
<%--            text-decoration: none;--%>
<%--            background: #f2f2f2;--%>
<%--            font-size: 14pt;--%>
<%--            cursor:pointer;--%>
<%--        }--%>
<%--        .close:hover {background: #e6e6ff;}--%>

<%--    </style>--%>
    <script src="<c:url value="/js/script.js"/>"></script>
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
                <td><a href="/counteragents/update/${counteragent.id}">Edit</a></td>
                <td><a href="/counteragents/delete/${counteragent.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
    <hr/>
</div>

<div id="backgroundForm">
    <form:form method="POST" action="/counteragents/new" modelAttribute="agentForm" id="formWindow">
        <label for="name">Наименование
                <%--                <input class="input-field" type="text" id="name" name="name"/>--%>
            <form:input type="text" id="name" path="name"/>
            <form:errors path="name" cssClass="error"/>
        </label>
        <label for="inn">ИНН
                <%--                <input class="input-field" type="text" id="inn" name="inn">--%>
            <form:input type="text" id="inn"  path="inn"/>

        </label>
        <label for="kpp">КПП
                <%--                <input class="input-field" type="text" id="kpp" name="kpp">--%>
            <form:input type="text" id="kpp" path="kpp"/>
        </label>
        <label for="numberAccount">Номер счета
                <%--                <input class="input-field" type="text" id="numberAccount" name="numberAccount">--%>
            <form:input type="text" id="numberAccount" path="numberAccount"/>
        </label>
        <label for="bik">БИК банка
                <%--                <input class="input-field" type="text" id="bik" name="bik">--%>
            <form:input type="text" id="bik" path="bik"/>
        </label>
        <button type="submit" >Добавить</button>
        <button type="button" onclick="closeForm()" class="close">Отменить</button>
    </form:form>
</div>

<%--<!-- Модальное окно №1 -->--%>
<%--<a href="#x" class="overlay" id="win1"></a>--%>
<%--<div class="popup">--%>
<%--    gfsdgsdfgsdgsdfg--%>
<%--    <a class="close"title="Закрыть" href="#close"></a>--%>
<%--</div>--%>

<button type="button" onclick="openForm()">Добавить пользователя</button>
<a href="/">Назад</a>
</body>
</html>
