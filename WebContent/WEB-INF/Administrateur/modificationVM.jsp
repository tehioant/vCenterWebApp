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
	String vmId = (String) request.getAttribute("vmId");
	System.out.println(vmId);
	System.out.println("Dans le jsp");
	%>
	<jsp:include page="../Commun/navBar.jsp">
    	<jsp:param name="servletOut" value="AccueilAdmin" />
    </jsp:include>
	<div id="wrapper">
	<div class="overlay"></div>
         <%@include file="Composants/sideBarAdmin.jsp"%>
    </div>
			<div class="col-sm-4 col-sm-offset-4">
		<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">Modification VM</h3>
			  </div>
			  <div class="panel-body">
			   <form action="ModificationVM" method="post">
						<div class="form-group ">
							<label for="memory">Mémoire RAM</label> <select class="form-control"
								id="memory" name="memory">
								<option value="0">Pas de changements</option>
								<option value="1024">1024</option>
								<option value="2048">2048</option>
								<option value="4096">4096</option>
								<option value="8192">8192</option>
							</select>
						</div>
						<div class="form-group">
							<label for="cpu">Nombre de cpu</label> <select
								class="form-control" id="cpu" name="cpu">
								<option value="0">Pas de changements</option>
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</div>
						<input type=hidden name=vmId value=<%=vmId%>>
						<button class="btn btn-primary center-block" type="submit">Submit</button>
					
			</form>
			  </div>
			</div>
			</div>
			
			
					
						
		
	
</body>
</html>



