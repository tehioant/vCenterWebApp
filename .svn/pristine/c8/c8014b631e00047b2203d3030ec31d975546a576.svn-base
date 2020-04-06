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

<title>Serveurs</title>
</head>
<body>
	<%
	ArrayList<Serveur> listeServeurs = (ArrayList<Serveur>) request.getAttribute("listeServeurs");
	
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
				
				<div id = "formServeur" style="display:none;">
				    <form class="form-inline" method="POST" action="CreationServeurs">
					  <div class="form-group">
					    <label for="nom">Nom</label>
					    <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom du serveur" required>
					  </div>
					  <div class="form-group">
					    <label for="referent">IP</label>
					    	 <input type="text" class="form-control" id="ip" name="ip" placeholder="ip" required>
					  </div>
					   <div class="form-group">
					    <label for="login">login</label>
					    <input type="text" class="form-control" id="login" name="login" placeholder="login" required>
					  </div>
					   <div class="form-group">
					    <label for="Mot de passe">Mot de passe</label>
					    <input type="password" class="form-control" id="Mot de passe" name="Mot de passe" placeholder="Mot de passe" required>
					  </div>
					
					  <button type="submit" class="btn btn-default">Créer</button>
					</form>
				</div>
				
				
				  <table class="table table-striped">
				    <thead>
				      <tr>
				        <th>Nom</th>
				        <th>IP</th>
				      </tr>
				    </thead>
				    <tbody>
				    <% for (int i = 0 ; i< listeServeurs.size() ; i++){ 
				    		Serveur serveur =listeServeurs.get(i);
				    		long serveurId = serveur.getNumServeur();
				   %>
				    
				      <tr>   
				        <td>
					        <form class="form-inline">
						        <a  onclick="modifServeur(<%=i%>)"> <%=listeServeurs.get(i).getNomServeur()%> </a>
						        <input type = hidden name = nomServeur value =<%=listeServeurs.get(i).getNomServeur()%>>
						        
						      

						    </form>
						    
						    <div id = "modifServeur<%=i%>"style="display:none;margin-top: 20px;">
								<form class="form-inline" method="POST" action="ModificationServeurs?serveurId=<%=serveurId%>">
									
									<div class="form-group">
										<label for="nom">Nom</label> <input type="text"
											class="form-control" id="nom" name="nom"
											placeholder="Nom du serveur" required>
									</div>
									<div class="form-group">
										<label for="referent">IP</label> <input type="text"
											class="form-control" id="ip" name="ip" placeholder="ip"
											required>
									</div>
									<div class="form-group">
										<label for="login">login</label> <input type="text"
											class="form-control" id="login" name="login"
											placeholder="login" required>
									</div>
									<div class="form-group">
										<label for="Mot de passe">Mot de passe</label> <input
											type="password" class="form-control" id="Mot de passe"
											name="Mot de passe" placeholder="Mot de passe" required>
									</div>

									<button type="submit" class="btn btn-default">Modifier</button>
								</form>
							</div>
					    </td>
				       	<td>  <%=listeServeurs.get(i).getIpServeur()%></td>

				        <td> </td>
				        <td> 
				        <form method="post" action ="SuppressionServeurs" onclick="return confirm('Etes-vous sûr ?');">
				        	<input type="hidden" name = "serveurs" value =<%=listeServeurs.get(i).getNomServeur() %> >
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
	    var x = document.getElementById("formServeur");
	    if (x.style.display == "none") {
	        x.style.display = "block";
	    } else {
	        x.style.display = "none";
	    }
	}
	
	function modifServeur(i) {
	    var x = document.getElementById("modifServeur"+i);
	    if (x.style.display == "none") {
	        x.style.display = "block";
	    } else {
	        x.style.display = "none";
	    }
	}
	</script>
	
</body>
</html>



