<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>

<%@page import = "fr.eseo.cc3.dao.bean.Utilisateur" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Oups</title>
</head>
</head>
<body>

<%="La vm n'a pas put etre supprimer" %>

<%
switch(((Utilisateur) session.getAttribute("utilisateur")).getNomUtilisateur()) {
		case("admin"):%>
			<a href="AccueilAdmin">retour</a>
			<%
			break;
		case("etudiant"):%>
			<a href="AccueilEtu">retour</a>
			<%
			break;
		case("professeur"):%>
		<a href="AccueilReferent">retour</a>
			<%
			break;
		}
%>

</body>
</html>