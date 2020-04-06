<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../Commun/head.html" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Administrateur/user.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/Administrateur/Composants/sideBarAdmin.css" />
<title>Utilisateur</title>
</head>
<body>
	<jsp:include page="../Commun/navBar.jsp">
						<jsp:param name="servletOut" value="AccueilAdmin" />
					</jsp:include>
			<div id="wrapper">
		        <div class="overlay"></div>
			
					 
		    
					<%@include file="Composants/sideBarAdmin.jsp" %>
					
						<%=request.getAttribute("totalRam") %>
						<%=request.getAttribute("totalCapacity") %>
			</div>




</body>
</html>