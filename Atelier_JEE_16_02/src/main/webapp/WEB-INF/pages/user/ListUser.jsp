<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des utilisateurs</title>
</head>
<body>
<div class="container">
<%@include file="../commons/MenuNavBar.jsp" %>
<h1>Liste des utilisateurs</h1>
<hr> 										 
<table class="centered">
   <thead>
       <tr>
           <th>Numero</th>       
           <th>Mail</th>
           <th>Valid�</th>  
           <th>Statut</th>           
           <th>Actions</th>           
                    
       </tr>
   </thead>
   <tbody> 
    <c:forEach items="${UserList}" var="u" varStatus="loop">   
	    <tr>
	    	<td>
	    	<c:out value="${loop.index + 1}"/>
	    	</td>
	        <td><c:out value="${u.mail}"/></td>
	        <td>
	        <c:if test="${empty u.validated}">
	        	Non
	        </c:if>
	        <c:if test="${u.validated}">
	        	Oui
	        </c:if>
	        <c:if test="${u.validated == false}">
	        	Non
	        </c:if>	        
	        </td> 
	        <td>
	        <c:if test="${empty u.status}">
	        	Activ�
	        </c:if>
	        <c:if test="${u.status}">
	        	Activ�
	        </c:if>
	        <c:if test="${u.status == false}">
	        	D�sactiv�
	        </c:if>	 
	        </td>        
	        <td>
	        	<form style="display:inline;" action="user?action=setStatus" method="post">
	        		<input type="hidden" name="userId" value="${u.id}"/>	        
					<c:if test="${u.status}">
						<input type="hidden" name="status" value="false"/>					
						<button style="cursor:pointer" class="btn waves-effect waves-light blue" type="submit" name="action">D�sactiver
					    	<i class="material-icons right">lock_outline</i>
						</button>				
					</c:if>				
					<c:if test="${u.status == false}">
						<input type="hidden" name="status" value="true"/>					
						<button style="cursor:pointer" class="btn waves-effect waves-light blue" type="submit" name="action">Activer
							<i class="material-icons right">lock_open</i>
						</button>				
					</c:if>
				</form>	
				<form style="display:inline;"  action="user?action=validate" method="post">	
					<input type="hidden" name="userId" value="${u.id}"/>				
					<c:if test="${u.validated == false}">
						<input type="hidden" name="validated" value=true/>				
						<button style="cursor:pointer" class="btn waves-effect waves-light orange" type="submit" name="action">Valider
						  <i class="material-icons right">check</i>
						</button>				
		       		</c:if>
	       		</form>		       		
	       		<form style="display:inline;" action="user?action=delete" method="post">
	       			<input type="hidden" name="userId" value="${u.id}"/>	       		
	       			<button style="cursor:pointer" class="btn waves-effect waves-light red" type="submit" name="action">Supprimer
	       				 <i class="material-icons right">delete_forever</i>
	       			</button>
	       		</form>								
	        </td>            
	    </tr>
	</c:forEach>	    
    </tbody>
 </table> 

</div>
</body>
<script>
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems);
});
</script>
</html>