<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="fr.eseo.cc3.dao.bean.Serveur"%>
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
      <meta charset="UTF-8">
      <title>Page de connexion</title>
	 
   </head>
   <header>
      <h1 style="text-align: center; margin-bottom: 100px;">VEclipse</h1>
   </header>
   <body>
	   
	      <div class="col-sm-4 col-sm-offset-4">
	      <form action="ServletLogin" method="POST" class="form login">
	         <div class="panel panel-info">
	            <div class="panel-heading">Veuillez vous identifier pour vous
	               connecter
	            </div>
	            <div class="panel-body">
	               <div class="input-group">
	                  <span class="input-group-addon" id="sizing-addon">Login</span> <input
	                     type="text" class="form-control" placeholder="Login" name="username"
	                     aria-describedby="sizing-addon" required>
	               </div>
	               <div class="input-group" style="margin-top: 10px;">
	                  <span class="input-group-addon" id="sizing-addon2">Mot de
	                  passe</span> <input type="password" class="form-control" name="password"
	                     placeholder="Mot de passe" aria-describedby="sizing-addon2" required>
	               </div>
	               <%
					ArrayList<Serveur> listeServeur = (ArrayList<Serveur>) request.getAttribute("serveurs");
				   %>
	               <div class="input-group" style="margin-top: 10px;">
	               <span class="input-group-addon"  id="sizing-addon3">Serveurs</span>
		               <select class=form-control name ="serveur" aria-describedby="sizing-addon3" required>
		               	  <%for (Serveur serveur : listeServeur) {%>
						  <option value= <%=serveur.getNumServeur()%> > <%=serveur.getNomServeur()%> </option>
						  <%}%>
		               	  
		               </select>
	               </div>
	            </div>
	            
	            <script src="css/Commun/Composants/dropDown.js"></script>
				
				${ErreurLogin}
				
	            <div class="panel-footer">
	               <input type=submit class="btn btn-info center-block"
	                  value="Se connecter">
	            </div>
	         </div>
	         </form>
	      </div>
	   
   </body>
</html>