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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Etudiant/virtualmachineEtu.css" />

<title>VM</title>
</head>

<body>
	<%
		VirtualMachine virtualmachine = (VirtualMachine) request.getAttribute("virtualMachine");
	%>

	<jsp:include page="../Commun/navBar.jsp">
		<jsp:param name="servletOut" value="AccueilEtudiant" />
	</jsp:include>
	<div id="wrapper">
		<div class="overlay"></div>




		<%@include file="Composants/sideBarEtu.jsp"%>

		<section>
			<div class="container">
				<h1>Détails VM</h1>
			</div>

		</section>

		<div class="container">
			<jsp:include page="../Commun/boutonConsole.jsp">
				<jsp:param name="vmName" value="<%=virtualmachine.getName()%>" />
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
						<td>size_MiB : <%=virtualmachine.getMemorySizeMIB().getSize_MiB()%>
							<br> hot_add_enabled : <%=virtualmachine.getMemorySizeMIB().getHot_add_enabled()%>
						</td>
						<td>label : <%=virtualmachine.getDisk().getLabel()%> <br>
							capacity : <%=virtualmachine.getDisk().getCapacity()%>
						</td>
						<td><%=virtualmachine.getPowerState()%></td>
						<td>mac_address : <%=virtualmachine.getNics().getMac_address()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>



