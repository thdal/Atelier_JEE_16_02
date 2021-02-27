<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<%@ include file="../commons/MenuNavBar.jsp" %>
<h1>Liste des classements</h1>
<hr> 

<h5>Classement Tops : Les 3 id�es les mieux not�es</h5>

<table style="margin-bottom:50px;" class="left">
   <thead>
       <tr>
           <th>Classement</th>       
           <th>Categorie</th>
           <th>Description</th>  
           <th style="text-align:right; padding-right:20px;" >Votes top</th>           
                    
       </tr>
   </thead>
   <tbody> 
    <c:forEach items="${topsClassement}" var="tc" varStatus="loop">   
	    <tr>
	    	<td>
	    	<c:if test="${loop.index eq 0 }">
	    	<img style="max-width:100px; max-height:100px" src="https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-gold-medal-vector-golden-1st-place-badge-sport-game-golden-challenge-png-image_1887633.jpg">
	    	</c:if>
	    	<c:if test="${loop.index eq 1 }">
	    	<img style="max-width:100px; max-height:100px" src="https://png.pngtree.com/png-vector/20191027/ourmid/pngtree-bronze-medal-vector-best-first-placement-winner-champion-number-one-3rd-png-image_1887553.jpg">
	    	</c:if>
	    	<c:if test="${loop.index eq 2 }">
	    	<img style="max-width:100px; max-height:100px" src="https://image.freepik.com/vecteurs-libre/medaille-argent-2e-place-argent_87720-2499.jpg">
	    	</c:if>
	        <td><c:out value="${tc.categorie}"/></td>
	        <td> <c:out value="${tc.description}"/>    
	        </td> 
	        <td  style="text-align:right; padding-right:40px;"><c:out value="${tc.topPercentage}%"/> 
	        </td>            
	    </tr>
	</c:forEach>	    
    </tbody>
 </table>

<h5>Classement Brains : Les 3 utilisateurs ayants post�s le plus d'id�es</h5>

<table style="margin-bottom:40px;" class="left">
   <thead>
       <tr>
       	   <th>Numero</th>   
           <th>Mail</th>       
           <th>Status</th>
           <th style="text-align:right;" >Validit�</th>            
                    
       </tr>
   </thead>
   <tbody> 
    <c:forEach items="${brainsClassement}" var="u" varStatus="loop">   
	    <tr>
	    	<td>
	    	<c:if test="${loop.index eq 0 }">
	    	<img style="max-width:100px; max-height:100px" src="https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-gold-medal-vector-golden-1st-place-badge-sport-game-golden-challenge-png-image_1887633.jpg">
	    	</c:if>
	    	<c:if test="${loop.index eq 1 }">
	    	<img style="max-width:100px; max-height:100px" src="https://png.pngtree.com/png-vector/20191027/ourmid/pngtree-bronze-medal-vector-best-first-placement-winner-champion-number-one-3rd-png-image_1887553.jpg">
	    	</c:if>
	    	<c:if test="${loop.index eq 2 }">
	    	<img style="max-width:100px; max-height:100px" src="https://image.freepik.com/vecteurs-libre/medaille-argent-2e-place-argent_87720-2499.jpg">
	    	</c:if>
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
	        <td style="text-align:right;" >
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
	    </tr>
	</c:forEach>	    
    </tbody>
 </table>

<h5>Classement Buzz : Les 3 id�es ayant d�clench� le plus de votes</h5>

<table style="margin-bottom:50px;" class="left">
   <thead>
       <tr>
           <th>Classement</th>       
           <th>Categorie</th>
           <th>Description</th>  
           <th style="text-align:right; padding-right:20px;" >Votes top</th>           
                    
       </tr>
   </thead>
   <tbody> 
    <c:forEach items="${buzzClassement}" var="b" varStatus="loop">   
	    <tr>
	    	<td>
	    	<c:if test="${loop.index eq 0 }">
	    	<img style="max-width:100px; max-height:100px" src="https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-gold-medal-vector-golden-1st-place-badge-sport-game-golden-challenge-png-image_1887633.jpg">
	    	</c:if>
	    	<c:if test="${loop.index eq 1 }">
	    	<img style="max-width:100px; max-height:100px" src="https://png.pngtree.com/png-vector/20191027/ourmid/pngtree-bronze-medal-vector-best-first-placement-winner-champion-number-one-3rd-png-image_1887553.jpg">
	    	</c:if>
	    	<c:if test="${loop.index eq 2 }">
	    	<img style="max-width:100px; max-height:100px" src="https://image.freepik.com/vecteurs-libre/medaille-argent-2e-place-argent_87720-2499.jpg">
	    	</c:if>
	        <td><c:out value="${b.categorie}"/></td>
	        <td> <c:out value="${b.description}"/>    
	        </td> 
	        <td style="text-align:right; padding-right:40px;" ><c:out value="${b.topPercentage}%"/> 
	        </td>            
	    </tr>
	</c:forEach>	    
    </tbody>
 </table>

</div>	
</body>
</html>