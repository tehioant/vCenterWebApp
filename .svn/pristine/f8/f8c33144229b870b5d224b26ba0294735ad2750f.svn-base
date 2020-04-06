<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="fr.eseo.cc3.dao.bean.Utilisateur"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Commun/Composants/navBar.css" />
<!-- Il est nécessaire de passer en paramètre le servlet cible du bouton accueil, lors de l'inclusion du jsp -->

<div class="container">
      
      
      <nav class="navbar navbar-default" style="background-color: #d9edf7">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active"><a href="<%=request.getParameter("servletOut")%>">Accueil <span class="sr-only">(current)</span></a></li>
		        <li><a href="#">Votre login : <%=((Utilisateur) session.getAttribute("utilisateur")).getNomUtilisateur()%></a></li>
		        
		      </ul>
		      <!-- <form class="navbar-form navbar-left">
		        <div class="form-group">
		          <input type="text" class="form-control" placeholder="Search">
		        </div>
		        <button type="submit" class="btn btn-default">Submit</button>
		      </form> -->
		      <ul class="nav nav-pills navbar-right">
		        <li><a href="Logout">Deconnexion</a></li>
		        
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
	  </nav>
      
      
      </div>