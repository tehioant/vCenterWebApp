<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   	<meta charset="ISO-8859-1">
      <%@include file="../Commun/head.html"%>
      <link rel="stylesheet" type="text/css"
         href="${pageContext.request.contextPath}/css/Administrateur/accueilAdmin.css" />
      <script
         src="${pageContext.request.contextPath}/javascript/Administrateur/changementOnglet.js"></script>
      <title>Accueil</title>
</head>
<body>

<%String vmId=request.getParameter("vmId");
	String vmName=request.getParameter("vmName"); 
%>

	<jsp:include page="../Commun/navBar.jsp">
            <jsp:param name="servletOut" value="AccueilAdmin" />
         </jsp:include>

		<div class="col-sm-4 col-sm-offset-4">
		<div class="panel panel-success">
		  <div class="panel-heading">
		    <h3 class="panel-title">Création du Snapshot : <%=vmName%></h3> 
		  </div>
		  <form  action="CreateSnapshot" method="post" id="connect">
		  <div class="panel-body">
		   
		    	<div class="form-group">
		    		<div class="input-group" style="margin-bottom:10px">
		    		<span class="input-group-addon" id="sizing-addon2">Nom de votre export</span>	
		    		<input id="input-nomExport" name="nomExport" type="text" class="form-control" data-show-preview="false" value="<%=vmName%>" >
		    		</div>
		    		<div class="input-group">
		    		<span class="input-group-addon" id="sizing-addon2">Description de votre export</span>
		    		<input id="input-nomExport" name="description" type="text" class="form-control" data-show-preview="false" value="" >
		    		</div>
		    		<input id="input-nomExport" name="vmName" type="hidden" class="btn" data-show-preview="false" value="<%=vmName%>" >	
		    		<input id="input-nomExport" name="vmId" type="hidden" class="btn" data-show-preview="false" value="<%=vmId%>" >	
                  </div>  
          
		  </div>
		   <div class="panel-footer"> <button type="submit" class="btn btn-success center-block">Soumettre</button></div>
          </form>
		</div>
		</div>
		
</body>
</html>