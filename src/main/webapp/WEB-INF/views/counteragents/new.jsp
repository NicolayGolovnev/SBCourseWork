<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 16.07.2021
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Update Page</title>
</head>
<body>
<%--@elvariable id="agentForm" type="ru.golovnev.model.CounterAgent"--%>
<form:form method="POST" action="/counteragents/new" modelAttribute="agentForm">
    <table>
        <tr hidden>
            <th><label for="id">ID</label></th>
            <th><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th><label for="name">Наименование</label></th>
            <th><form:input type="text" id="name" path="name"/></th>
            <th><form:errors path="name" cssClass="error"/></th>
        </tr>
        <tr></tr>
        <tr>
            <th><label for="inn">ИНН</label></th>
            <th><form:input type="text" id="inn" path="inn"/></th>
            <th></th>
        </tr>
        <tr>
            <th></th>
            <th><form:errors path="inn" cssClass="error" cssStyle="color: #bf0808"/></th>
        </tr>
        <tr>
            <th><label for="kpp">КПП</label></th>
            <th><form:input type="text" id="kpp" path="kpp"/></th>
        </tr>
        <tr>
            <th></th>
            <th><form:errors path="kpp" cssClass="error" cssStyle="color: #bf0808"/></th>
        </tr>
        <tr>
            <th><label for="numberAccount">Номер счета</label></th>
            <th><form:input type="text" id="numberAccount" path="numberAccount"/></th>
            <th><form:errors path="numberAccount" cssClass="error" cssStyle="color: #bf0800"/></th>
        </tr>
        <tr>
            <th><label for="bik">БИК банка</label></th>
            <th><form:input type="text" id="bik" path="bik"/></th>
            <th><form:errors path="bik" cssClass="error" cssStyle="color: #bf0800"/></th>
        </tr>
        <tr>
            <th></th>
            <th>
                <button type="button"><a href="/counteragents">Назад</a></button>
                <button type="submit">Добавить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
