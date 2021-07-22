<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Справочник контрагентов</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/modalWindow.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/script.js"/>"></script>
</head>
<body>
<div class="content">
    <div class="top">
        <h2>Список контрагентов</h2>
    </div>
    <table class="showAgent">
        <tr>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>Номер счета</th>
            <th>БИК банка</th>
        </tr>
        <c:forEach items="${counterAgentsFromServer}" var="counteragent">
            <tr>
                <td align="center">${counteragent.name}</td>
                <td align="center">${counteragent.inn}</td>
                <td align="center">${counteragent.kpp}</td>
                <td align="center">${counteragent.numberAccount}</td>
                <td align="center">${counteragent.bik}</td>
                <td width="auto"><button type="button" onclick="location.href='/counteragents/update/${counteragent.id}'">Изменить</button></td>
                <td width="auto"><button type="button" onclick="location.href='/counteragents/delete/${counteragent.id}'">Удалить</button></td>
            </tr>
        </c:forEach>
    </table>
    <hr/>
    <button type="button" onclick="location.href='/counteragents/new'">Добавить пользователя</button>
    <button type="button" onclick="openDltForm()">Удалить по наименованию</button>
    <button type="button" onclick="location.href='/'">Вернуться</button>
</div>

<div id="deleteForm" class="modalDiv">
    <div class="modalDiv-content">
        <div class="top">
            <h2>Удаление контрагента по наименованию</h2>
        </div>
        <%--@elvariable id="deleteAgent" type="ru.golovnev.model.CounterAgent"--%>
        <form:form modelAttribute="deleteAgent" action="/deleteByName" method="post">
            <label>Наименование:</label>
            <form:input type="text" list="agentsNameList" path="name" autocomplete="false"/>
            <datalist id="agentsNameList">
                <c:forEach items="${counterAgentsFromServer}" var="agent">
                    <option value="${agent.name}"></option>
                </c:forEach>
            </datalist>
            <hr/>
            <button type="submit">Удалить</button>
            <button type="button" onclick="closeDltForm()">Закрыть</button>
        </form:form>
    </div>
</div>
</body>
</html>
