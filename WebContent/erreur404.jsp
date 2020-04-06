<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/Commun/head.html"%>
</head>

<header>
	<h1 style="text-align: center; margin-bottom: 100px;">VEclipse</h1>
</header>

<body>
	<div class="col-sm-4 col-sm-offset-4">
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">Erreur 404</h3>
			</div>
			<div class="panel-body">Dommage. Essaye encore.</div>
			<div class="panel-footer">
				<a href="javascript:history.go(-1)"> <input type="button"
					class="btn btn-warning center-block"
					value="Revenir à la page précédente" /></a>
			</div>
		</div>
	</div>
</body>
</html>

