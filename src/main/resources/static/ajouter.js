function ajouter(param1,param2) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/boutonAjouter?id="+param1+"&idP="+param2);
    xhr.send();
}

function suppr(param1) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/boutonSupprimer?idP="+param1);
    xhr.send();
}