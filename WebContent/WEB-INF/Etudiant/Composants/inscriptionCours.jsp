<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import = "fr.eseo.cc3.dao.bean.Cour" %>
<%@page import="java.util.ArrayList"%>


<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp" %>


			<%ArrayList<Cour> listeCours =(ArrayList<Cour>) request.getAttribute("listeCours");
			%>
	
				<!-- Trigger the modal with a button -->
				
				
				<!-- Modal -->
				<div id="myModal2" class="modal fade" role="dialog">
				  <div class="modal-dialog">
				    <!-- Modal content-->
				    <div class="modal-content project-details-popup">
				      
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				      
				     <div class="modal-body">
				      <form action ="InsriptionCours" method="post">
						<div class="panel panel-default" style="margin-top:30px">
				  			<div class="panel-heading" style="background-image:linear-gradient(to bottom,#337ab7 0,#337ab7 100%);background-color:#337ab7;color:#f5f5f5">Inscription � un cours</div>
				        <div class="panel-body">
							<div class="row">
								<div class="col-md-5">
									<div class="form-group ">
										<label for="sel2">Veuiller choisir un cours</label>
									    <select class="form-control" id="sel2" name ="nomCours" data-live-search="true" required>
									    	<%
									    		for(int i =0 ; i<listeCours.size() ; i++){
									    			if(!listeCours.get(i).isInscrit()){
									    		%>
									    			<option data-tokens="<%=listeCours.get(i).getNomCour()%>"><%=listeCours.get(i).getNomCour() %></option>
									    		<%
									    			}}
									    	%>
									    </select>
									</div>
								</div>
								
							</div>
							<div class="row">
								<div class="col-md-4 col-md-offset-4 ">
									<button class="btn btn-primary center-block" type="submit" >S'inscrire</button>
								</div>
							</div>
							</div>
						</div>
						</form>
				      </div>
				      <div class="modal-footer">  
				      </div>
				    </div>
				
				  </div>
				</div>
				  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
				<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
								  



