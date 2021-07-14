<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <script src="/js/script.js"></script>
</head>

<div class="form-style-2">
<%--    <div class="form-style-2-heading">--%>
<%--        Добавление пользователя--%>
<%--    </div>--%>
    <button class="open-button" onclick="openForm()">Добавление пользователя</button>
    <div class="form-popup" id="myFormPopup">
        <form method="post" action="/counteragents" class="form-container">
            <label for="name">Наименование
                <input class="input-field" type="text" id="name" name="name">
            </label>
            <label for="inn">ИНН
                <input class="input-field" type="text" id="inn" name="inn">
            </label>
            <label for="kpp">КПП
                <input class="input-field" type="text" id="kpp" name="kpp">
            </label>
            <label for="numberAccount">Номер счета
                <input class="input-field" type="text" id="numberAccount" name="numberAccount">
            </label>
            <label for="bik">БИК банка
                <input class="input-field" type="text" id="bik" name="bik">
            </label>
            <button type="submit" class="btn">Добавить</button>
            <button type="button" class="btn cancel" onclick="closeForm()">Закрыть</button>
        </form>
    </div>
</div>


<div class="form-style-2">
    <div class="form-style-2-heading">
        Already in System!
    </div>
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
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>