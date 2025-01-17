<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Etudiant/Composants/actionEtu.css" />

<% 
	String vmId=request.getParameter("vmId"); 
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
			<a href="ConnectNetworkVm?vmId=<%=vmId%>&vmName=<%=vmName%>">Connexion � un r�seau</a>
		</li>
		 <div class="dropdown-divider"></div>
		<li><button type="button" class="btn"
					data-toggle="modal" data-target="#modalSnapshot">Cr�er un snapshot</button></li>
		<li><a href="RevertSnapshot?vmName=<%=vmName%>">RevertSnapshot</a></li>
		
		<div class="dropdown-divider"></div>
		<li><a href="ListIso?nomVm=<%=vmName%>&vmId=<%=vmId%>&action=1">Monter iso</a></li>
		<li><a href="ListIso?nomVm=<%=vmName%>&vmId=<%=vmId%>&action=0">D�monter iso</a></li>
		
		 <div class="dropdown-divider"></div>
		<li><a href="ExportOVA?vmId=<%=vmId%>">Export OVF</a></li>
		 <div class="dropdown-divider"></div>
		<li><a href="SupprimerVm?vmId=<%=vmId%>" onclick="return confirm('Etes-vous s�r de vouloir supprimer <%=vmName%> ?');">Supprimer</a></li>
	</ul>
</div>








<!-- Modal -->
<div id="modalSnapshot" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
	<div class="modal-content project-details-popup">

		<button type="button" class="close" data-dismiss="modal">&times;</button>

		<div class="modal-body">
			<form action="CreateSnapshot">
				<div class="panel panel-default" style="margin-top: 30px">
					<div class="panel-heading"
						style="background-image: linear-gradient(to bottom, #337ab7 0, #337ab7 100%); background-color: #337ab7; color: #f5f5f5">Informations
						VM</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-5">
								<div class="form-group">
									<label for="name">Nom</label> <input type="text" id="name"
										name="name">
								</div>
							</div>
							<div class="col-md-5">
								<div class="form-group">
									<label for="name">Description : </label> <input type="text" id="description"
										name="description">
								</div>
							</div>
							<input id="vmName" type="hidden" value=<%=vmName%> name="vmName" >
							</div>
							<div class="row">
								<div class="col-md-4 col-md-offset-4 ">
									<button class="btn btn-primary center-block" type="submit">Submit</button>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer"></div>
		</div>

	</div>
</div>









