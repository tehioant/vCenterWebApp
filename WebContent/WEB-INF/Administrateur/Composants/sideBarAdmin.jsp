
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Administrateur/Composants/sideBarAdmin.css" />
<script
	src="${pageContext.request.contextPath}/javascript/Commun/sideBar.js"></script>
<script> $(document).ready(sideBar); </script>

<!-- Sidebar -->
<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper"
	role="navigation">
	<ul class="nav sidebar-nav">
		<li class="sidebar-brand"><a href="#"> vEclipse </a></li>
		<li><a href="AccueilAdmin">Accueil</a></li>

		<!-- <li><a href="ListeCours">Liste des VMs</a></li>   -->
		<li><a href="ListeVM">Liste des VMs</a></li>
		<li><a href="ListeServeurs">Liste des serveurs</a></li>


	</ul>
</nav>


<div id="page-content-wrapper">
	<button type="button" class="hamburger is-closed"
		data-toggle="offcanvas">
		<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
			class="hamb-bottom"></span>
	</button>

</div>