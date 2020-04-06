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

<title>Cours</title>
</head>
<body>
	<%
	ArrayList<Cour> listeCours = (ArrayList<Cour>) request.getAttribute("listeCours");
	ArrayList<Utilisateur> listeReferents = (ArrayList<Utilisateur>) request.getAttribute("listeReferents");
	
	%>


	<jsp:include page="../Commun/navBar.jsp">
			<jsp:param name="servletOut" value="AccueilAdmin" />
		</jsp:include> 

	<div id="wrapper">
        <div class="overlay"></div>
		
		<%@include file="Composants/sideBarAdmin.jsp" %>

		
		 <div class="container">        
			    <button type="button" class="btn btn-default" aria-label="Left Align" onclick="showHide()">
				  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				
				<div id = "formCours" style="display:none;">
				    <form class="form-inline" method="POST" action="CreationCours">
					  <div class="form-group">
					    <label for="nom">Nom</label>
					    <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom du cours">
					  </div>
					  <div class="form-group">
					    <label for="referent">Referent</label>
					    <select class=form-control id="nomUtilisateurReferent" name="nomUtilisateurReferent">
					    <% for (Utilisateur referent :  listeReferents){  %>
								<option value=<%=referent.getNomUtilisateur() %>><%=referent.getPrenom()+ " "+referent.getNom() %></option>
						<%} %>
						</select>
					  </div>

					  <button type="submit" class="btn btn-default">Créer</button>
					</form>
				</div>
				
				  <table class="table table-striped">
				    <thead>
				      <tr>
				        <th>Nom</th>
				        <th>Etudiants</th>
				        <th>Machine Virtuelles</th>
				      </tr>
				    </thead>
				    <tbody>
				    <% for (int i = 0 ; i< listeCours.size() ; i++){ %>
				    
				      <tr>   
				        <td>
				        
				        <form action="DetailsCours" method="get">
					        <input type= submit class="btn btn-link" value = <%= listeCours.get(i).getNomCour()  %>>
					        <input type = hidden name ="nomCours" value = <%=listeCours.get(i).getNomCour()%>>
					        </form>
				        </td>
				        
				        <td> <%=listeCours.get(i).getNbEtudiant() %></td>
				        <td> <%=listeCours.get(i).getNbVm() %></td>
				        <td> 
				        <form method="post" action ="SuppressionCours">
				        	<input type="hidden" name = "cours" value =<%=listeCours.get(i).getNomCour() %> >
				        	<button type="submit" class="btn btn-default" aria-label="Left Align">
							  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button>
				        </form>
				        
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
	    if (x.style.display == "none") {
	        x.style.display = "block";
	    } else {
	        x.style.display = "none";
	    }
	}
	
	</script>
	
	
</body>
</html>



