<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="misenpage1.css" rel="stylesheet"/>

    <!-- les liens des css-->
    <!--<link rel="stylesheet" type="text/css" href="/css/misenpage.css" /> -->
    <!-- les metas classiques-->
    <meta name="author" content="Maël THIRE"/>
    <meta name="keywords" content="vente multimedia"/>
    <!-- Le titre de la page -->
    <title>OFFLINE</title>
    <script src="ajouter.js"></script>
</head>
<body>
<div id="entete">
    <img src="banniere.png" width="100%">

    <nav>
        <ul class="menu">
            <li>
                <a href="accueil">Accueil</a>
            </li>
            <li>
                <a>Ventes</a>
                <ul class="sousmenu">
                    <li>
                        <a href="console">Consoles</a>
                        <a href="accessoireC">Accessoires Console</a>
                        <a href="jeux">Jeux</a>
                        <a href="ordinateur">Ordinateurs</a>
                        <a href="accessoireP">Accessoires Ordinateur</a>
                        <a href="telephone">Téléphones</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="connexion">Connexion</a>
                <ul class="sousmenu">
                    <li>
                        <a href="inscription">Inscription</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="panier">Panier</a>
            </li>
        </ul>
    </nav>
</div>
<div id="milieu">
    <script>
        function del(param1) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/supprUser?id="+param1);
            xhr.send();
            document.getElementById("contenu").innerHTML = "Votre compte a été supprimé";
        }
    </script>
    <div id="contenu">
        <p>Voulez-vous supprimer votre compte ?</p>
        <input type="button" value="Oui" onclick="del(${userId})">
        <a href="accueil">Annuler</a>
    </div>
</div>
<div id="footer">
    <p> Tous droits réservés à <b>OFFLINE </b></p>
    <p> 7 rue Saint John Perse, BP45 64000 Pau </p>
    <p><u>email:</u> offline@gmail.com </p>
    <p><u>Téléphone :</u> 0612345678 </p>
</div>
</body>
</html>
