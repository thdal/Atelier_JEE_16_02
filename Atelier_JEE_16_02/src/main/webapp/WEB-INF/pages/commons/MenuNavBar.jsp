<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	  
</head>

 <nav class="blue">
    <div class="nav-wrapper">
      <a href="home" class="brand-logo"><i style="margin-left:10px; font-size:55px" class="large material-icons">home</i></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <c:if test="${ sessionScope.Admin }">
        	<li><a href="user?action=list">Liste des utilisateurs</a></li>        
    	</c:if>
        <li><a href="idees?action=list">Liste des idees</a></li>      
        <li><a href="classements?action=list">Liste des classements</a></li>          
        <li><a href="connexion"><i class="material-icons prefix">power_settings_new</i></a></li>
        
        <!-- <li><a href="users?action=list">Liste des utilisateurs</a></li>  -->
      </ul>
    </div>
  </nav>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
