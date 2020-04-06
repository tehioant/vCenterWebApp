<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.model.*"%>
<%@page import="fr.eseo.cc3.model.vm.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="fr.eseo.cc3.dao.bean.Utilisateur"%>
<%@ page import="java.util.*, java.util.Date"%>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="../Commun/head.html"%>

<title>VirtualMachine Etudiant</title>
</head>

<body>
	<%
		VirtualMachine virtualmachine = (VirtualMachine) request.getAttribute("virtualMachine");
	%>
	<div id="wrapper">
	<div class="overlay"></div>
		<jsp:include page="../Commun/navBar.jsp">
			<jsp:param name="servletOut" value="AccueilReferent" />
		</jsp:include>
		<%@include file="Composants/sideBarRef.jsp"%>

		<section>
			<div class="container">
				<h1>Liste VMs</h1>
			</div>

		</section>

		<div id="vmdetails" class="content"
			style="display: block">
			<!-- VirtualMachine details  -->
			<div class="container">

				<jsp:include page="../Commun/boutonConsole.jsp">
					<jsp:param name="vmId" value="<%=virtualmachine.getName()%>" />
				</jsp:include>

					<table class="table table-bordered">
						<thead>
							<tr class="info">
								<th>Nom VM</th>
								<th>ID</th>
								<th>Mémoire</th>
								<th>Disque</th>
								<th>Etat</th>
								<th>Nics</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><%=virtualmachine.getName()%></td>
								<td><%=request.getAttribute("name")%></td>
								<td>Taille (Mib) : <%=virtualmachine.getMemorySizeMIB().getSize_MiB()%>
									<br> hot_add_enabled : <%=virtualmachine.getMemorySizeMIB().getHot_add_enabled()%></td>
								<td>label : <%=virtualmachine.getDisk().getLabel()%> <br>
									capacity : <%=virtualmachine.getDisk().getCapacity()%></td>
								<td><%=virtualmachine.getPowerState()%></td>
								<td>Adresse mac : <%=virtualmachine.getNics().getMac_address()%></td>
							</tr>

						</tbody>

					</table>
			</div>
		</div>
	</div>



</body>
</html>



