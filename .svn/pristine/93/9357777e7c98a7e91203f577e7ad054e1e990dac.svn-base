<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.vmware.vim25.VirtualMachineTicket"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../Commun/head.html"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Commun/wmks-all.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Commun/console.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.16/jquery-ui.min.js"></script>


<title>Console</title>

</head>
<body>
	<%
		VirtualMachineTicket ticket = (VirtualMachineTicket) request.getAttribute("ticket");
		boolean erreur = (boolean) request.getAttribute("erreur");
		
		if(!erreur){
	%>
	

	<div id="wmksContainer" style="position: absolute; width: 100%; height: 100%">
	<!-- <select id="selectLanguage" class="selectpicker">
		<option value="fr-FR">French</option>
		<option value="en-US">English</option>
		<option value="ja-JP_106/109">Japanese</option>
		<option value="de-DE">German</option>
		<option value="it-IT">Italian</option>
		<option value="es-ES">Spanish</option>
		<option value="pt-PT">Portuguese</option>
		<option value="fr-CH">Swiss-French</option>
		<option value="de-CH">Swiss-German</option>
	</select>  -->
		<div class="container">
			<div class="alert alert-warning alert-dismissible" role="alert" id="alerte">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Warning!</strong> La console est déconnectée, votre machine virtuelle est peut-être éteinte
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript"src="${pageContext.request.contextPath}/javascript/Commun/wmks.js" ></script>
	<script type="text/javascript"src="${pageContext.request.contextPath}/javascript/Commun/console.js" ></script>
	<script>
		var wmks = WMKS.createWMKS("wmksContainer",{});
		wmks.register(WMKS.CONST.Events.CONNECTION_STATE_CHANGE, function(event,data){
			if(data.state == WMKS.CONST.ConnectionState.CONNECTED){
			console.log("connection state change : connected");
			} else if(data.state == WMKS.CONST.ConnectionState.DISCONNECTED){
				show(wmks);
				console.log("connection state change : disconnected");
			}
		});
		
		connexion("<%=ticket.getHost()%>","<%=ticket.getTicket()%>");
		window.onbeforeunload =function (){deco(wmks);}
		/*$('#selectLanguage').change(changeLanguage(wmks))*/
		
	</script>
	
	<%}else{ %>
		<div class="container">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Erreur!</strong> La console est inaccessible.<br>
			  Veuillez fermez cette page, puis démarrer votre machine.
			</div>
		</div>
	<%} %>
	


</body>
</html>