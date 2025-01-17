<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.model.*"%>
<%@page import="fr.eseo.cc3.json.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>


<%@ page import="java.util.*, java.util.Date"%><!DOCTYPE html>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="../Commun/head.html"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Etudiant/accueilEtu.css" />
<title>Accueil</title>
</head>
<body>

	<% int banniere = (int) request.getAttribute("banniere"); %>

	<% if(banniere == 1) {%>
	<div class="container">
		<br />
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>

			<p>Vous ne pouvez pas supprimer une Vm sous tension</p>


		</div>
		<!-- Fin du bandeau d'alerte -->

		<script> 
                  $('#myButton').click(function(){ /* Apr�s le clic sur l'�l�ment dont l'id est "myButton" (le bouton dans notre cas) "*/
                        $('.alert').alert('close'); /* Faire disparaitre le bandeau d'alerte*/
                  }); 
            </script>
	</div>
	<%} %>

	<% if(banniere == 1) {%>
	<div class="container">
		<br />
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">&times;</button>

			<p>La VM est d�j� dans l'�tat que vous souhaitez.</p>


		</div>
		<!-- Fin du bandeau d'alerte -->

		<script> 
                  $('#myButton').click(function(){ /* Apr�s le clic sur l'�l�ment dont l'id est "myButton" (le bouton dans notre cas) "*/
                        $('.alert').alert('close'); /* Faire disparaitre le bandeau d'alerte*/
                  }); 
            </script>
	</div>

	<%} %>
	<%
		ArrayList<VirtualMachine> listeVm = (ArrayList<VirtualMachine>) session.getAttribute("listeVm");
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		ArrayList<fr.eseo.cc3.dao.bean.VirtualMachine> listeTemplate = (ArrayList<fr.eseo.cc3.dao.bean.VirtualMachine>) request.getAttribute("listeTemplate");
		
	%>

	<jsp:include page="../Commun/navBar.jsp">
		<jsp:param name="servletOut" value="AccueilEtudiant" />
	</jsp:include>



	<div id="wrapper">
		<div class="overlay"></div>



		<%@include file="Composants/sideBarEtu.jsp"%>

		<div class="container" style="margin-bottom: 30px">
			<h1>Liste VMs</h1>
		</div>

		<!-- VMs -->
		<div id="AllVm">
			<div class="container" id="listeVm">
				<div>

					<form>
						<button type="button" class="btn btn-primary btn-lg"
							data-toggle="modal" data-target="#myModal"
							style="margin-right: 10px">Cr�er une Vm</button>
					</form>
					<form>
						<button type="button" class="btn btn-primary btn-lg"
							data-toggle="modal" data-target="#myModal2"
							style="margin-right: 10px">S'inscrire</button>
					</form>

					<form id="on" action="ChangementEtatListeVm" method="post">
						<input type=submit class="btn btn-success btn-lg"
							title="Allumer toutes les Vms !" aria-hidden="true"
							value="Allumer"> <input type=hidden name=etat value="1">
					</form>

					<form action="ChangementEtatListeVm" method="post"
						style="margin-left: 10px">
						<input type=submit class="btn btn-warning btn-lg"
							title="Eteindre toutes les Vms !" aria-hidden="true"
							value="Eteindre"> <input type=hidden name=etat value="0">
					</form>

					<%@include file="Composants/creationVmEtu.jsp"%>
					<%@include file="Composants/inscriptionCours.jsp"%>
				</div>
			</div>

			<div class="container">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Etat</th>
							<th>Nombre CPU</th>
							<th>M�moire Maximum</th>
							<th>Actions</th>

						</tr>
					</thead>
					<tbody>

						<%
								String etat;
								for (VirtualMachine vm : listeVm) {
									switch (vm.getPowerState()) {
										case ("POWERED_OFF") :
											etat = "danger";
											break;
										case ("POWERED_ON") :
											etat = "success";
											break;
										case ("SUSPENDED") :
											etat = "warning";
											break;
										default :
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
							<td><button type="button" class="btn btn-<%= etat %>">
									<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
								</button></td>
							<td><%=vm.getCpu().getCount()%></td>
							<td><%=vm.getMemorySizeMIB().getSize_MiB() + " MiB"%></td>
							<td><jsp:include page="Composants/actionEtu.jsp">
									<jsp:param name="vmId" value="<%=vm.getVm() %>" />
									<jsp:param name="vmName" value="<%=vm.getName() %>" />
								</jsp:include></td>
						</tr>
						<%
								}
							%>
					</tbody>
				</table>

				<br></br>

				<h2>Liste Template</h2>

				<div id="container">
					<%if(listeTemplate.size() > 0) {%>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<%for(int v=0; v<listeTemplate.size(); v++){%>
							<tr>
								<td><%=listeTemplate.get(v).getNomVm()%></td>
								<td><jsp:include
										page="Composants/actionTemplateEtudiant.jsp">
										<jsp:param name="vmId"
											value="<%=listeTemplate.get(v).getRefVm() %>" />
										<jsp:param name="vmName"
											value="<%=listeTemplate.get(v).getNomVm() %>" />
									</jsp:include></td>
							</tr>
							<%} %>
						</tbody>
					</table>
					<%}else{ %>
					<h3>Pas de template disponible</h3>
					<%} %>
				</div>

			</div>
		</div>
	</div>
</body>
</html>



