<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="fr.eseo.cc3.model.exception.ExceptionConnectionVSphere"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erreur alimentation vm</title>
</head>
<body>
	
	<%
			ExceptionConnectionVSphere erreur = (ExceptionConnectionVSphere) request.getAttribute("erreur");
		%>


	nous n'avons pas pu changer l'�tat de certain vm � cause du code d'erreur suivant : <%=erreur.getCodeErreur() %>
	
	<a href="AccueilAdmin">retour</a>
</body>
</html>