<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'enregistrement</title>
</head>
<body>
<div class="container">            
      <div style="margin-top:2%" class="row">     
          <div class="col s6 offset-s3 z-depth-1" id="panell">
            <div class="card-panel teal">
	          <h4 style="text-align: center;" class="white-text">Topaidi</h4>
	          <h5 style="text-align: center;" class="white-text" id="title">Register Form</h5>
	        </div>
			<form action="connexion?action=create" method="post">
		        <div class="input-field" id="username">
		             <i class="material-icons prefix">email</i>
			         <input name="mail" type="text" class="validate">
			         <label for="mail">Email</label>
		        </div>
		        <div class="input-field" id="password">
		            <i class="material-icons prefix">lock_open</i>        
		            <input name="password" type="password" class="validate">
		            <label for="password">Password</label>
		        </div>
		        <div class="input-field" id="password">
		            <i class="material-icons prefix">lock_open</i>        
		            <input name="passwordbis" type="password" class="validate">
		            <label for="passwordbis">Confirm Password</label>
		        </div>
		        <c:if test="${ Erreur }">
		        	<div>
			       	 <span style="color:red;">Attention veuillez renseigner tous les champs !</span>
			        </div>        
		    	</c:if>
		    	<c:if test="${ ErreurMail }">
		        	<div>
			       	 <span style="color:red;">Le format de l'adresse mail est invalide !</span>
			        </div>        
		    	</c:if>
		    	<c:if test="${ ErreurPW }">
		        	<div>
			       	 <span style="color:red;">Les password ne correspondent pas !</span>
			        </div>        
		    	</c:if>
		        <button style="cursor:pointer" class="btn waves-effect waves-light" type="submit" name="action">Register</button>		        
		        <a href="connexion" class="waves-effect waves-light btn teal lighten-1" id="loginbtn">Back to login</a>        
		             
        </form>

        </div>
      </div>

    </div>
		
</body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</html>
