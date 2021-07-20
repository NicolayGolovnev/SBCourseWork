<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Find page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<label>Поиск:
    <select id="selectList" onchange="OnSelectionChange(this)">
        <option selected disabled>Выберите критерий...</option>
        <option value="byName">По ФИО контрагента</option>
        <option value="byBikAndNumberAccount">По БИК и номеру счета контрагента</option>
    </select>
</label>
<div id="finderByName" hidden>
    <form:form modelAttribute="findByName" action="/find/byName" method="post">
        <fieldset>
            <legend></legend>
            <form:input placeholder="ФИО контрагента" path="name"/>
            <button type="submit" onclick="showFinderDiv()">Найти</button>
        </fieldset>
    </form:form>
</div>

<div id="finderByBikAndNumberAccount" hidden>
    <%--@elvariable id="findByBikAndNumberAccount" type="ru.golovnev.model.CounterAgent"--%>
    <form:form modelAttribute="findByBikAndNumberAccount" action="/find/byBikAndNumberAccount" method="post">
        <fieldset>
            <legend></legend>
            <form:input placeholder="БИК банка" path="bik"/>
            <form:input placeholder="Номер счета" path="numberAccount"/>
            <button type="submit" onclick="showFinderDiv()">Найти</button>
        </fieldset>
    </form:form>
</div>
<button type="button" onclick="location.href='/'">Вернуться</button>
<script>
    function OnSelectionChange(select) {
        var selectedValue = select.options[select.selectedIndex].value;
        if (selectedValue === 'byName') {
            document.getElementById("finderByName").hidden = false;
            document.getElementById("finderByBikAndNumberAccount").hidden = true;
        }
        else if (selectedValue === 'byBikAndNumberAccount') {
            document.getElementById("finderByName").hidden = true;
            document.getElementById("finderByBikAndNumberAccount").hidden = false;
        }
    }

    function showFinderDiv() {
        document.getElementById("finderDiv").hidden = false;
    }
</script>

</body>
</html>
