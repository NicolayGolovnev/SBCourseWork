<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 19.07.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% CounterAgent finder--%>
<%--    %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
Результат поиска:
<table>
    <tr>
        <th>Наименование</th>
        <th>${finderAgent.name}</th>
    </tr>
    <tr>
        <th>ИНН</th>
        <th>${finderAgent.inn}</th>
    </tr>
    <tr>
        <th>КПП</th>
        <th>${finderAgent.kpp}</th>
    </tr>
    <tr>
        <th>Номер счета</th>
        <th>${finderAgent.numberAccount}</th>
    </tr>
    <tr>
        <th>БИК банка</th>
        <th>${finderAgent.bik}</th>
    </tr>
</table>
<button type="button" onclick="location.href='/find'">Вернуться</button>
</body>
</html>
