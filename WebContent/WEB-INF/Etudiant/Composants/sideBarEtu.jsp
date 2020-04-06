<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<script
	src="${pageContext.request.contextPath}/javascript/Commun/sideBar.js"></script>
<script> $(document).ready(sideBar); </script>

<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper"
	role="navigation">
	<ul class="nav sidebar-nav">
		<li class="sidebar-brand"><a href="#"> VEclipse </a></li>
		<li><a href="AccueilEtudiant">Accueil</a></li>
		<li><a href="#">Liste des Professeurs</a></li>
		<li><a href="#">Equipe</a></li>


	</ul>
</nav>



<div id="page-content-wrapper">
	<button type="button" class="hamburger is-closed"
		data-toggle="offcanvas">
		<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
			class="hamb-bottom"></span>
	</button>

</div>