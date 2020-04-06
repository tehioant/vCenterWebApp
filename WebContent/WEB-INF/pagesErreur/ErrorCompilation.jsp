<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
	<html>
	   <head>
	      <!-- Latest compiled and minified CSS -->
	      <link rel="stylesheet"
	         href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	         integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	         crossorigin="anonymous">
	      <!-- Optional theme -->
	      <link rel="stylesheet"
	         href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	         integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	         crossorigin="anonymous">
	      <!-- Latest compiled and minified JavaScript -->
	      <script
	         src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	         integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	         crossorigin="anonymous"></script>
	   </head>
	   
	   <header>
      <h1 style="text-align: center; margin-bottom: 100px;">VEclipse</h1>
   </header>
	
	<body>
		<div class="col-sm-4 col-sm-offset-4">
		      <div class="panel panel-warning">
				  <div class="panel-heading">
				    <h3 class="panel-title">Erreur</h3>
				  </div>
				  <div class="panel-body">
				    Erreur de connexion. Regardez votre console.
				  </div>
				  <div class="panel-footer">
				  <a href="Accueil"><input type="button" class="btn btn-warning center-block"
		                  value="Revenir sur la page de connexion."/></a>
		            </div>
				</div>
			</div>
		               
					
	
		<%
			exception.printStackTrace();
		%>
	
	</body>
</html>
	
