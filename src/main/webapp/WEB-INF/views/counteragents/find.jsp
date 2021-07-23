<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Поиск контрагента</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/styles.css">
    <script src="<c:url value="/js/script.js"/>"></script>
</head>
<body>
<div class="content">
    <div class="top">
        <h2>Поиск по заданным параметрам</h2>
    </div>
    <label>Поиск
        <select id="selectList" onchange="OnSelectionChange(this)">
            <option selected disabled>Выберите критерий...</option>
            <option value="byName">По ФИО контрагента</option>
            <option value="byBikAndNumberAccount">По БИК и номеру счета контрагента</option>
        </select>
    </label>
    <button type="button" onclick="location.href='/'">Вернуться</button>
    <div id="finderByName" hidden style="margin: 5px">
        <form:form modelAttribute="findByName" action="/find/byName" method="post">
            <fieldset>
                <legend></legend>
                <form:input path="name" placeholder="ФИО контрагента" maxlength="20"/>
                <button type="submit" onclick="showFinderDiv()">Найти</button>
            </fieldset>
        </form:form>
    </div>

    <div id="finderByBikAndNumberAccount" hidden style="margin: 5px">
        <%--@elvariable id="findByBikAndNumberAccount" type="ru.golovnev.model.CounterAgent"--%>
        <form:form modelAttribute="findByBikAndNumberAccount" action="/find/byBikAndNumberAccount" method="post">
            <fieldset>
                <legend></legend>
                <form:input path="bik" placeholder="БИК банка" maxlength="9"/>
                <form:input path="numberAccount" placeholder="Номер счета" maxlength="20"/>
                <button type="submit" onclick="showFinderDiv()">Найти</button>
            </fieldset>
        </form:form>
    </div>
</div>
</body>
</html>
