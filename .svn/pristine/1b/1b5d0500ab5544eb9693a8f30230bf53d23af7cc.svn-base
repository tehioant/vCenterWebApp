<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Etudiant/Composants/actionEtu.css" />

<%String vmId=request.getParameter("vmId");
String vmName=request.getParameter("vmName");
%>

<div class="dropdown">
	<button class="btn btn-default dropdown-toggle" type="button"id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"aria-expanded="true">
		Actions <span class="caret"></span>
	</button>
	<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		<li><a href="ChangementEtatVm?vmId=<%=vmId%>&etat=1">Allumer</a></li>
		<li><a href="ChangementEtatVm?vmId=<%=vmId%>&etat=0">Eteindre</a></li>
		<li class="disabled">
			<a href="ConnectNetworkVm?vmId=<%=vmId%>">Connexion à un réseau</a>
		</li>
		<li><a href="SupprimerVm?vmId=<%=vmId%>" onclick="return confirm('Etes-vous sûr ?');">Supprimer</a></li>
		<div class="dropdown-divider"></div>
		<li><a href="ListIso?nomVm=<%=vmName%>&vmId=<%=vmId%>&action=1">Monter iso</a></li>
		<li><a href="ListIso?nomVm=<%=vmName%>&vmId=<%=vmId%>&action=0">Démonter iso</a></li>
	</ul>
</div>