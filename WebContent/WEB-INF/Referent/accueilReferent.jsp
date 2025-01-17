<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.model.*"%>
<%@page import= "java.text.DecimalFormat"%>
<%@page import = "java.text.NumberFormat" %>
<%@page import = "fr.eseo.cc3.dao.bean.Utilisateur" %>
<%@ page import="java.util.*, java.util.Date" %><!DOCTYPE html>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp" %>

<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="../Commun/head.html" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Referent/accueilRef.css"/>
<title>Accueil</title>
</head>
<body>
	<% int banniere1 = (int) request.getAttribute("banniere1"); %>
	<% int banniere = (int) request.getAttribute("banniere"); %>

	<% if(banniere == 1) {%>
	<div class="container">
		<br />
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>

			<p>
				Vous ne pouvez pas supprimer une Vm sous tension 
			</p>

			
		</div>
		<!-- Fin du bandeau d'alerte -->

		<script> 
                  $('#myButton').click(function(){ /* Apr�s le clic sur l'�l�ment dont l'id est "myButton" (le bouton dans notre cas) "*/
                        $('.alert').alert('close'); /* Faire disparaitre le bandeau d'alerte*/
                  }); 
            </script>
	</div>
	<%} %>
	
	<% if(banniere1 == 1) {%>
	<div class="container">
		<br />
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>

			<p>
				La VM est d�j� dans l'�tat que vous souhaitez.
			</p>

			
		</div>
		<!-- Fin du bandeau d'alerte -->

		<script> 
                  $('#myButton').click(function(){ /* Apr�s le clic sur l'�l�ment dont l'id est "myButton" (le bouton dans notre cas) "*/
                        $('.alert').alert('close'); /* Faire disparaitre le bandeau d'alerte*/
                  }); 
            </script>
	</div>

	<%} %>
	
	<%ArrayList<VirtualMachine> listeVm = (ArrayList<VirtualMachine>) session.getAttribute("listeVm"); %>
	
	<jsp:include page="../Commun/navBar.jsp">
            <jsp:param name="servletOut" value="AccueilReferent" />
         </jsp:include>
      
	<div id="wrapper">
        <div class="overlay"></div>
    

		      
		<%@include file="Composants/sideBarRef.jsp" %>
	
 		<div id="vm" class="content"> <!-- VMs -->
	    
			
	    
	            <div class="container">     
	            
	            <h1 style="margin-bottom:30px">Liste VMs</h1>
	            	<div>
				
<!-- 					<Label>Allumer et eteindre toutes les VMs </Label> -->

						
						<form action="ChangementEtatListeVm" method="post"
							>
							<input type=submit class="btn btn-success btn-lg"
								aria-hidden="true" value="Allumer" title="Allumer toutes les VMs !" > <input type=hidden
								name=etat value="1">
						</form>
		
						<form action="ChangementEtatListeVm" method="post"
							style="margin-left: 10px">
							<input type=submit class="btn btn-warning btn-lg"
								aria-hidden="true" value="Eteindre" title="Eteindre toutes les VMs !" > <input type=hidden
								name=etat value="0">
						</form>
						
					</div>
		          
				
			 
				  	<table class="table table-striped">
				    	<thead>
				      	<tr>
					        <th>Nom</th>
					        <th>Etat</th>
					        <th>Propri�taire</th>
					        <th>Nombre CPU</th>
					        <th>M�moire Maximum</th>
					        <th>Actions </th>

				      	</tr>
				    </thead>
				    <tbody>
				    
				    <%
				    String etat;
				    for (VirtualMachine vm : listeVm){
				    	switch(vm.getPowerState()){
				     	case("POWERED_OFF") :
				     		etat = "danger";
				     		break;
				     	case("POWERED_ON") :
				     		etat="success";
				     		break;
				     	case("SUSPENDED") :
				     		etat = "warning";
				     		break;
				     	default :
				     		etat ="inconnus";
				     	}
				    	
				    	
				    	%>
				      <tr>
				        <td>
							<form action="VirtualMachine" method="get">
						        <input type= submit class="btn btn-link" value = <%= vm.getName()  %>>
						        <input type = hidden name = vmId value = <%=vm.getVm() %>>
						         <input type = hidden name = vmName value = <%=vm.getName() %>>
						    </form>
						</td>
				        <td><button type="button" class="btn btn-<%= etat %>">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span></button></td>
				        <td><%="N.A" %></td>
				        <td><%=vm.getCpu().getCount() %></td>
				        <td><%=vm.getMemorySizeMIB().getSize_MiB() +" MiB"%></td>
						<td>
							<jsp:include page="Composants/actionRef.jsp">
								<jsp:param name="vmId" value="<%=vm.getVm() %>" />
								<jsp:param name="vmName" value="<%=vm.getName() %>" />
							</jsp:include>
				        </td>
				      </tr>
					<%} %>
				    </tbody>
				  </table>				  
				</div>
				
			
				
			
				
				
	    </div>
	    

	        
	
	    </div>
		
		
</body>
</html>



