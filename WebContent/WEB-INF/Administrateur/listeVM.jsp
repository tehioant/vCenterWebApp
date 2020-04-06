<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.dao.bean.*"%>
<%@page import="fr.eseo.cc3.model.VirtualMachine"%>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp" %>

<!DOCTYPE html>
<html>
<head>

<%@include file="../Commun/head.html" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Administrateur/listeCours.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Administrateur/Composants/sideBarAdmin.jsp" />

<title>Listes VM Cours</title>
</head>
<body>
	<%
	ArrayList<Cour> listeCours = (ArrayList<Cour>) request.getAttribute("listeCours");
	ArrayList<Utilisateur> listeReferents = (ArrayList<Utilisateur>) request.getAttribute("listeReferents");
    ArrayList<VirtualMachine> listeVm = (ArrayList<VirtualMachine>) session.getAttribute("listeVm");
	%>

	<jsp:include page="../Commun/navBar.jsp">
			<jsp:param name="servletOut" value="AccueilAdmin" />
		</jsp:include> 


	<div id="wrapper">
        <div class="overlay"></div>
    
		
		<%@include file="Composants/sideBarAdmin.jsp" %>

		
		 <div class="container">
		  <table class="table table-striped">
		    <thead>
		      <tr>
		        <th>Virtual Machine</th>
		        <th>Etudiant</th>
		        <th>Action</th>
		      </tr>
		    </thead>
		    
		<div id="vm" class="content" style="margin-top: 10px">
            <div class="container">
               <div>
                  <form>
                     <button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
                        data-target="#myModal"  style="margin-right: 10px" >Créer une Vm</button>
                  </form>
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
                  <%@include file="Composants/creationVmAdmin.jsp"%>
               </div>
            </div>
            <!-- VMs -->
            <div class="container">
               <table class="table table-striped">
                  <thead>
                     <tr>
                        <th>Nom</th>
                        <th>Etat</th>
                        <th>Propriétaire</th>
                        <th>Nombre CPU</th>
                        <th>Mémoire Maximum</th>
                        <th>Actions</th>
                     </tr>
                  </thead>
                  <tbody>
                     <%
                        String etat;
                        for (VirtualMachine vm : listeVm) {
                        	switch (vm.getPowerState()) {
                        	case ("POWERED_OFF"):
                        		etat = "danger";
                        		break;
                        	case ("POWERED_ON"):
                        		etat = "success";
                        		break;
                        	case ("SUSPENDED"):
                        		etat = "warning";
                        		break;
                        	default:
                        		etat = "inconnus";
                        	}
                        %>
                     <tr>
                        <td>
                           <form action="VirtualMachine" method="get">
                              <input type=submit class="btn btn-link" value=<%=vm.getName()%>>
                              <input type=hidden name=vmId value=<%=vm.getVm()%>> <input
                                 type=hidden name=vmName value=<%=vm.getName()%>>
                           </form>
                        </td>
                        <td ><button type="button" class="btn btn-<%= etat %>">
                           <span class="glyphicon glyphicon-off" aria-hidden="true"></span></button>
                        </td>
                        <td><%="N.A"%></td>
                        <td><%=vm.getCpu().getCount()%></td>
                        <td ><%=vm.getMemorySizeMIB().getSize_MiB() + " MiB"%></td>
                        <td>
                           <jsp:include page="Composants/actionAdmin.jsp">
                              <jsp:param name="vmId" value="<%=vm.getVm()%>" />
                              <jsp:param name="vmName" value="<%=vm.getName()%>" />
                           </jsp:include>
                        </td>
                     </tr>
                     <%
                        }
                        %>
                      
                  </tbody>
		
				  </table>
		</div>

	    </div>
	
	<script>
	
	function showHide() {
	    var x = document.getElementById("formCours");
	    if (x.style.display === "none") {
	        x.style.display = "block";
	    } else {
	        x.style.display = "none";
	    }
	}
	
	</script>
	
	
</body>
</html>


