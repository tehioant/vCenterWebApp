<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.dao.bean.*"%>

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
	ArrayList<VirtualMachine> listeVm = (ArrayList<VirtualMachine>) request.getAttribute("listeVm");
	ArrayList<Utilisateur> listeUtilisateur = (ArrayList<Utilisateur>) request.getAttribute("listeUtilisateur");
	
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
		    <tbody>
			      <% for (int i = 0 ; i< listeVm.size() ; i++){ %>
			      <tr>   
			         <td>
						<form action="ServletVirtualMachine" method="get">
					        <input type= submit class="btn btn-link" value = <%= listeVm.get(i).getRefVm()  %>>
					        <input type = hidden name = vmId value = <%=listeVm.get(i).getRefVm() %>>
					    </form>
					</td>
			         <td>
						<%=listeUtilisateur.get(i).getNomUtilisateur() %>
					</td>
					<td>
			      			<jsp:include page="Composants/actionAdmin.jsp">
								<jsp:param name="vmId" value="<%=listeVm.get(i).getRefVm() %>" />
							</jsp:include> 
			        </td>
			      </tr>
			      <%} %>
		
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



