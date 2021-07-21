<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Добавление контрагента</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="content">
    <div class="top">
        <h2>Добавление нового контрагента</h2>
    </div>
    <%--@elvariable id="agentForm" type="ru.golovnev.model.CounterAgent"--%>
    <form:form method="POST" action="/counteragents/new" modelAttribute="agentForm">
        <table>
            <tr hidden>
                <th align="right"><label for="id">ID</label></th>
                <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
            </tr>
            <tr>
                <th align="right"><label for="name">Наименование</label></th>
                <th align="left"><form:input type="text" id="name" path="name" maxlength="20"/></th>
            </tr>
            <tr>
                <th></th>
                <th align="left"><form:errors path="name" cssClass="error"/></th>
            </tr>
            <tr>
                <th align="right"><label for="inn">ИНН</label></th>
                <th align="left"><form:input type="text" id="inn" path="inn" maxlength="12"/></th>
            </tr>
            <tr>
                <th></th>
                <th align="left"><form:errors path="inn" cssClass="error"/></th>
            </tr>
            <tr>
                <th align="right"><label for="kpp">КПП</label></th>
                <th align="left"><form:input type="text" id="kpp" path="kpp" maxlength="9"/></th>
            </tr>
            <tr>
                <th></th>
                <th align="left"><form:errors path="kpp" cssClass="error"/></th>
            </tr>
            <tr>
                <th align="right"><label for="numberAccount">Номер счета</label></th>
                <th align="left"><form:input type="text" id="numberAccount" path="numberAccount" maxlength="20"/></th>
            </tr>
            <tr>
                <th></th>
                <th align="left"><form:errors path="numberAccount" cssClass="error"/></th>
            </tr>
            <tr>
                <th align="right"><label for="bik">БИК банка</label></th>
                <th align="left"><form:input type="text" id="bik" path="bik" maxlength="9"/></th>
            </tr>
            <tr>
                <th></th>
                <th align="left"><form:errors path="bik" cssClass="error"/></th>
            </tr>
            <tr>
                <th></th>
                <th align="right">
                    <button type="button" onclick="location.href='/counteragents'">Назад</button>
                    <button type="submit">Добавить</button>
                </th>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
