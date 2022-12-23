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
<% if (session.getAttribute("userId") != null) { int userId = (int) session.getAttribute("userId"); }%>
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
<div id="contenu">
    <br> <br><br> <br>
    <table align="center" width="75%" border="0" margin="20px" padding="20px" margin-left="20px"
           backgroound-color="#e8e5df">
        <thead>
        <tr>
            <th colspan="5"></th>
        </tr>
        </thead>

        <tr>

        <tr>
            <td align="center"> Photo</td>
            <td align="center"> Prix</td>
            <td align="center"> Commande</td>
        </tr>
        <tr>
            <td width="30%"><br><br> <img src="joycon.png"/></td>
            <td width="5%" align="center"> 59.99€</td>
            <td width="20%" align="center">
                <button onclick="ajouter(${userId},13)"> Ajouter</button>
            </td>
        </tr>
        <tr>
            <td><br><br><img src="mPS5.png"/></td>
            <td align="center"> 69.99€</td>
            <td width="20%" align="center">
                <button onclick="ajouter(${userId},14)"> Ajouter</button>
            </td>
        </tr>
        <tr>
            <td width="30%"><br><br><img src="mxbox.png"/></td>
            <td width="5%" align="center"> 64.99€</td>
            <td width="20%" align="center">
                <button onclick="ajouter(${userId},15)"> Ajouter</button>
            </td>
        </tr>
        <tr>
            <td width="30%"><br><br><img src="mDS.png"/></td>
            <td width="5%" align="center"> 29.99€</td>
            <td width="20%" align="center">
                <button onclick="ajouter(${userId},16)"> Ajouter</button>
            </td>
        </tr>
        </tr>
        </tbody>
    </table>
    <br>


</div>
<div id="footer">
    <p> Tous droits réservés à <b>OFFLINE </b></p>
    <p> 7 rue Saint John Perse, BP45 64000 Pau </p>
    <p><u>email:</u> offline@gmail.com </p>
    <p><u>Téléphone :</u> 0612345678 </p>
</div>
</body>
</html>	


