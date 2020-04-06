<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.model.*"%>
<%@page import = "fr.eseo.cc3.dao.bean.Utilisateur" %>
<%@ page import="java.util.*, java.util.Date" %>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp" %>

<!DOCTYPE html>
<html>
<head>

<%@include file="../Commun/head.html" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Administrateur/host.css"/>
<script src="${pageContext.request.contextPath}/javascript/Administrateur/changementOnglet.js"></script> 


<title>Hyperviseur</title>
</head>
<body>
	<%
	ArrayList<VirtualMachine> listeVm = (ArrayList<VirtualMachine>) session.getAttribute("listeVm");
	
	String clusterName = request.getParameter("hostName");
	
	%>

	<jsp:include page="../Commun/navBar.jsp">
			<jsp:param name="servletOut" value="AccueilAdmin" />
		</jsp:include> 
	<div id="wrapper">
        <div class="overlay"></div>
    
        
		
        <%@include file="Composants/sideBarAdmin.jsp" %>

	
	
		<section>
	        <div class="container">
			  
			  <ul class="nav nav-tabs">
			    <li><a href="javascript:ChangeOngletHost('tab_resume', 'resume');" id="tab_resume">Résumé</a></li>
			    <li><a href="javascript:ChangeOngletHost('tab_vm', 'vm');" id="tab_vm">VMs</a></li>
			  </ul>
			  <br>
			  
			</div>
			
		</section>
		<div id="resume" class="content" style="display:block">    <!-- Résumé -->     
			<div class ="container">
	            <ul>
	                <li><p>Vm : <small><%= listeVm.size() %></small></p></li>
	                <li><p>CPU Max : <small>N.A</small></p></li>
	                <li><p>Memoire Max : <small>N.A</small></p></li>
	                
	            </ul>
	        </div>
	    </div>

		
	    
	    <div id="vm" class="content"> <!-- VMs -->
	            <div class="container">        
	            	
		        	
				  	<table class="table table-striped">
				    	<thead>
				      	<tr>
					        <th>Nom</th>
					        <th>Etat</th>
					        <th>Statut</th>
					        <th>Nombre CPU</th>
					        <th>Mémoire Maximum</th>
					        <th>Actions </th>
				      	</tr>
				    </thead>
				    <tbody>
				     <%
				     String etat;
				     for (VirtualMachine vm  : listeVm){ 
				     
				     	switch(vm.getPowerState()){
				     	case("POWERED_OFF") :
				     		etat = "Hors tension";
				     		break;
				     	case("POWERED_ON") :
				     		etat="Sous tension";
				     		break;
				     	case("SUSPENDED") :
				     		etat = "Interrompu";
				     		break;
				     	default :
				     		etat ="inconnus";
				     	}
				     
				     %>

				      <tr>
				        <td>
							<form action="ServletVirtualMachine" method="get">
					        <input type= submit class="btn btn-link" value = <%= vm.getName()  %>>
							<input type=hidden name=vmId value=<%=vm.getVm()%>> 
							<input type=hidden name=vmName value=<%=vm.getName()%>>
					        </form>	
						</td>
				        <td><%=etat %></td>
				        <td><%="N.A" %></td>
				        <td><%=vm.getCpu().getCount() %></td>
				        <td><%=vm.getMemorySizeMIB().getSize_MiB() +" MiB"%></td>
				        <td>
								<jsp:include page="Composants/actionAdmin.jsp">
									<jsp:param name="vmId" value="<%=vm.getVm() %>" />
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



