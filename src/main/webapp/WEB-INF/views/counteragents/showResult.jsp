<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результат поиска</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="content">
    <div class="top">
        <h2>Результат поиска:</h2>
    </div>
    <table class="showAgent">
        <tr>
            <th align="right">Наименование</th>
            <th align="left">${finderAgent.name}</th>
        </tr>
        <tr>
            <th align="right">ИНН</th>
            <th align="left">${finderAgent.inn}</th>
        </tr>
        <tr>
            <th align="right">КПП</th>
            <th align="left">${finderAgent.kpp}</th>
        </tr>
        <tr>
            <th align="right">Номер счета</th>
            <th align="left">${finderAgent.numberAccount}</th>
        </tr>
        <tr>
            <th align="right">БИК банка</th>
            <th align="left">${finderAgent.bik}</th>
        </tr>
    </table>
    <hr/>
    <button type="button" onclick="location.href='/find'">Вернуться</button>
</div>
</body>
</html>
