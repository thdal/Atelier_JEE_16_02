<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire d'idee</title>
</head>
<body>
<div class="container">
<%@include file="../commons/MenuNavBar.jsp" %>
<h1>Formulaire d'idee</h1>
<hr>
<form action="idees?action=create" method="post">
   <label for="categorieIdee">Catégorie :</label> <input type="text"  name="categorieIdee">
   <label for="descriptionIdee">Description :</label>  <input type="text"  name="descriptionIdee">
   <label for="imgIdee">URL de l'image :</label>  <input type="text" name="imgIdee">
   <button style="cursor:pointer" class="btn waves-effect waves-light blue" type="submit" name="action">Valider</button>    
 </form>
 </div>
</body>
</html>
