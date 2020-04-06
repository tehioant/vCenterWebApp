<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="fr.eseo.cc3.dao.bean.Cour"%>
<%@page import="fr.eseo.cc3.dao.DAOFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.dao.bean.Utilisateur"%>

<%

//ArrayList<Cour> cours = (ArrayList<Cour>) session.getAttribute("cours");
ArrayList<String> listeOs = (ArrayList<String>) request.getAttribute("listeOs");


%>

<!-- Trigger the modal with a button -->
<!--  CREER UNE VM -->
<button type="button" class="btn btn-primary btn-lg"
data-toggle="modal" data-target="#myModal">Créer une Vm</button>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
	<div class="modal-content project-details-popup">

		<button type="button" class="close" data-dismiss="modal">&times;</button>

		<div class="modal-body">
			<form action="AjoutVM">
				<div class="panel panel-default" style="margin-top: 30px">
					<div class="panel-heading"
						style="background-image: linear-gradient(to bottom, #337ab7 0, #337ab7 100%); background-color: #337ab7; color: #f5f5f5">Informations
						VM</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-5">
								<div class="form-group ">
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
									<label for="datastore">Datastore*</label> <select
										class="form-control" id="datastore" name="datastore" required>
										<option value="datastore-32">datastore-32</option>
									</select>
								</div>
							</div>
							<div class="col-md-5">
								<div class="form-group ">
									<label for="folder">Folder*</label> <select
										class="form-control" id="folder" name="folder" required>
										<option value="group-v22">group-v22</option>
									</select>
								</div>
							</div>
							<div class="col-md-5 col-md-push-2">
								<div class="form-group">
									<label for="resourcepool">Resource Pool*</label> <select
										class="form-control" id="resourcepool"
										name="resourcepool" required>
										<option value="resgroup-27">resgroup-27</option>
									</select>
								</div>
							</div>
							<div class="col-md-5 col-md-push-2">
								<div class="form-group">
									<label for="cours">Cours*</label> <select
										class="form-control" id="cours" name="cours" required>
										<%-- <%
											for (Cour cour : cours) {
										%> --%>
								<%-- 		<option value="<%=cour.getNomCour()%>"><%=cour.getNomCour()%></option> --%>
										<%-- <%
											}
										%> --%>
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
			<div class="modal-footer"></div>
		</div>

	</div>
</div>
<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script
	src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
