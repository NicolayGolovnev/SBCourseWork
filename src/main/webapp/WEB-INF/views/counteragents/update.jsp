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
<%--@elvariable id="updateAgent" type="ru.golovnev.model.CounterAgent"--%>
<form:form method="POST" action="/counteragents/update" modelAttribute="updateAgent">
    <label for="id">ID
        <form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/>
    </label>
    <label for="name">Наименование
            <%--                <input class="input-field" type="text" id="name" name="name"/>--%>
        <form:input type="text" id="name" path="name"/>
        <form:errors path="name" cssClass="error"/>
    </label>
    <label for="inn">ИНН
            <%--                <input class="input-field" type="text" id="inn" name="inn">--%>
        <form:input type="text" id="inn" path="inn"/>

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
    <button type="submit">Изменить</button>
    <button type="button"><a href="/counteragents">Назад</a></button>
</form:form>
</body>
</html>
