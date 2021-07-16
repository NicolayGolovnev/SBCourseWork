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
    <title>Title</title>
</head>
<body>
<div>
    <div >
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
<button type="button"><a href="/counteragents/new">Добавить пользователя</a></button>
<a href="/">Назад</a>
</body>
</html>
