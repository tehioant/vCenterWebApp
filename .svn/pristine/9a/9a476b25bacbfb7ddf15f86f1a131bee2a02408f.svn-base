<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="fr.eseo.cc3.dao.bean.Utilisateur"%>
<%@page import="fr.eseo.cc3.dao.bean.Cour"%>
<%@page import="fr.eseo.cc3.dao.DAOFactory"%>
<%@page import="java.util.ArrayList"%>

<%
	Utilisateur users = (Utilisateur) session.getAttribute("utilisateur");
	ArrayList<Cour> listeCours = (ArrayList<Cour>) session.getAttribute("listeCours");
	ArrayList<Utilisateur> listeUser = (ArrayList<Utilisateur>) session.getAttribute("listeUser");
	ArrayList<String> listeOs = (ArrayList<String>) request.getAttribute("listeOs");
%>

<!-- Trigger the modal with a button -->


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
<div class="modal-dialog">
   <!-- Modal content-->
   <div class="modal-content project-details-popup">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <div class="modal-body">
         <form action="AjoutVM" method="post">
					<div class="panel panel-default" style="margin-top: 30px">
						<div class="panel-heading"
							style="background-image: linear-gradient(to bottom, #337ab7 0, #337ab7 100%); background-color: #337ab7; color: #f5f5f5">Informations
							VM</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-5">
									<div class="form-group">
										<label for="name">Nom*</label> <input type="text" id="name"
											name="name" required>
									</div>
								</div>
								<div class="col-md-5 col-md-push-2">
									<div class="form-group">
										<label for="config">Configuration*</label> <select
											class="form-control" id="config" name="config" required>
											<option value="low">Low (1Cpu 1Go)</option>
											<option value="min">Minimale (1Cpu 4Go)</option>
											<option value="med">Medium (1Cpu 4Go)</option>
											<option value="high">High (2Cpu 4Go)</option>
											<option value="ultra">Ultra (2Cpu 8Go)</option>
										</select>
									</div>
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label for="os">OS*</label> <select class="form-control"
											id="os" name="os" required>
											<% for (int i=0 ; i<listeOs.size(); i++){ %>
												<option value="<%=listeOs.get(i)%>"><%=listeOs.get(i)%></option>
											<%} %>
										</select>
									</div>
								</div>
								<div class="col-md-5 col-md-push-2">
									<div class="form-group">
										<label for="cours">Cours*</label> <select class="form-control"
											id="cours" name="cours" required>
											<%
												for (Cour cour : listeCours) {
											%>
											<option value="<%=cour.getNomCour()%>"><%=cour.getNomCour()%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label for="utilisateur">Utilisateur*</label> <select
											class="form-control" id="utilisateur" name="utilisateur" required>
											<%
												for (Utilisateur user : listeUser) {
											%>
											<option value="<%=user.getNom()%>"><%=user.getNom()%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
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
			
			<!-- <div class="modal-footer"></div> -->
		</div>
		
	</div>
</div>
