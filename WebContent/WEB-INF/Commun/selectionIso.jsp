<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.model.*"%>
<%@page import="fr.eseo.cc3.model.vm.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="fr.eseo.cc3.dao.DAOFactory"%>
<%@page import="fr.eseo.cc3.dao.bean.VirtualMachine"%>
<%@ page import="java.util.*, java.util.Date"%>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Administrateur/virtualmachine.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}css/Administrateur/Composants/sideBarAdmin.css" />
<%@include file="../Commun/head.html"%>

<title>Modification VM</title>
</head>
<body>

	<%
		ArrayList<String> listeIso = (ArrayList<String>) request.getAttribute("listeIso");
		String nomVm = request.getParameter("nomVm");
		String action = request.getParameter("action");
	%>
	<jsp:include page="../Commun/navBar.jsp">
		<jsp:param name="servletOut" value="AccueilAdmin" />
	</jsp:include>
	<div class="col-sm-4 col-sm-offset-4">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Selectionner un Iso</h3>
			</div>
			<div class="panel-body">
				<form action="MonterDemonterIso" method="post">
					<div class="form-group ">
						<label for="nomIso">Mémoire RAM</label> 
						<select class="form-control" id="nomIso" name="nomIso">
							<%for(int i=0; i<listeIso.size();i++){ %>
								<option value="<%=listeIso.get(i) %>"><%=listeIso.get(i) %> </option>
							<%} %>
						</select>
					</div>
					<input type=hidden name="nomVm" value=<%=nomVm%>>
					<input type=hidden name = "action" value=<%=action %>>
					<button class="btn btn-primary center-block" type="submit">Submit</button>

				</form>
			</div>
		</div>
	</div>






</body>
</html>



