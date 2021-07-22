function openDltForm() {
    document.getElementById("deleteForm").style.display = "block";
}

function closeDltForm() {
    document.getElementById("deleteForm").style.display = "none";
}

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
