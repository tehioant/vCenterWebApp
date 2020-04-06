<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--  IMPORT des ressources  -->
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eseo.cc3.model.*"%>
<%@page import="fr.eseo.cc3.model.vm.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="fr.eseo.cc3.dao.bean.Utilisateur"%>
<%@ page import="java.util.*, java.util.Date"%>

<!--  Redirection en cas d'erreur  -->
<%@ page errorPage="../pagesErreur/ErrorCompilation.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../Commun/head.html"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Administrateur/virtualmachine.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}css/Administrateur/Composants/sideBarAdmin.css" />


<title>VM</title>
</head>

<body>


	<%
		VirtualMachine virtualmachine = (VirtualMachine) request.getAttribute("virtualMachine");
	%>



	<div id="wrapper">
		<div class="overlay"></div>

		<jsp:include page="../Commun/navBar.jsp">
			<jsp:param name="servletOut" value="AccueilAdmin" />
		</jsp:include>
		<%@include file="Composants/sideBarAdmin.jsp"%>



		<section>
			<div class="container">

				<h1>Détails VM</h1>

			</div>

		</section>

		<!-- VirtualMachine details  -->
		<div class="container">

			<jsp:include page="../Commun/boutonConsole.jsp">
				<jsp:param name="vmId" value="<%=virtualmachine.getName()%>" />
			</jsp:include>

			<table class="table table-bordered">
				<thead>
					<tr class="info">
						<th>Nom VM</th>
						<th>ID</th>
						<th>CDroms</th>
						<th>Mémoire</th>
						<th>Disque</th>
						<th>Sata adapters</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=virtualmachine.getName()%></td>
						<td><%=request.getAttribute("name")%></td>
						<td>start_connected : <%=virtualmachine.getCdroms().getStart_connected().toString()%>
							<br> Backing :: device_access_type : <%=virtualmachine.getCdroms().getBacking_device_access_type()%>
							type : <%=virtualmachine.getCdroms().getBacking_type()%> <br>
							allow_guest_control : <%=virtualmachine.getCdroms().getAllow_guest_control()%>
							<br> label : <%=virtualmachine.getCdroms().getLabel()%> <br>
							state : <%=virtualmachine.getCdroms().getState()%> <br> type
							: <%=virtualmachine.getCdroms().getType()%> <br> sata :: bus
							: <%=virtualmachine.getCdroms().getSata_bus()%> unit : <%=virtualmachine.getCdroms().getSata_unit()%>
							<br> key : <%=virtualmachine.getCdroms().getKey()%></td>
						<td>size_MiB : <%=virtualmachine.getMemorySizeMIB().getSize_MiB()%>
							<br> hot_add_enabled : <%=virtualmachine.getMemorySizeMIB().getHot_add_enabled()%></td>
						<td>scsi :: bus : <%=virtualmachine.getDisk().getScsi_bus()%>
							unit : <%=virtualmachine.getDisk().getScsi_unit()%> <br>
							backing :: vmdk_file : <%=virtualmachine.getDisk().getBacking_vmdk_file()%>
							type : <%=virtualmachine.getDisk().getBacking_type()%> <br>
							label : <%=virtualmachine.getDisk().getLabel()%> <br> type :
							<%=virtualmachine.getDisk().getType()%> <br> capacity : <%=virtualmachine.getDisk().getCapacity()%></td>
						<td>scsi :: bus : <%=virtualmachine.getSata().getBus()%> bus
							: <%=virtualmachine.getSata().getUnit()%> <br>
							pci_slot_number : <%=virtualmachine.getSata().getPciSlot()%> <br>
							label : <%=virtualmachine.getSata().getLabel()%> <br> type :
							<%=virtualmachine.getSata().getType()%> <br> sharing : <%=virtualmachine.getSata().getSharing()%>
							<br> key : <%=virtualmachine.getSata().getKey()%></td>

					</tr>

				</tbody>

			</table>
			<table class="table table-bordered">
				<thead>
					<tr class="info">
						<th>Etat</th>
						<th>Nics</th>
						<th>Boot</th>
						<th>Guest OS</th>
						<th>Hardware</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=virtualmachine.getPowerState()%></td>
						<td>start_connected : <%=virtualmachine.getNics().getStart_connected()%>
							<br> pci_slot_number : <%=virtualmachine.getNics().getPciSlot()%>
							<br> Backing :: network_name : <%=virtualmachine.getNics().getBacking_network_name()%>
							network : <%=virtualmachine.getNics().getBacking_network()%> type
							: <%=virtualmachine.getNics().getBacking_type()%> <br>
							mac_address : <%=virtualmachine.getNics().getMac_address()%> <br>
							mac_type : <%=virtualmachine.getNics().getMac_type()%> <br>
							allow_guest_control : <%=virtualmachine.getNics().getState()%> <br>
							wake_on_lan_enabled : <%=virtualmachine.getNics()%> <br>
							label :: bus : <%=virtualmachine.getNics().getLabel()%> <br>
							state : <%=virtualmachine.getNics().getState()%> <br> type :
							<%=virtualmachine.getNics().getType()%> <br> key : <%=virtualmachine.getNics().getKey()%></td>
						<td>delay : <%=virtualmachine.getBoot().getDelay()%> <br>
							retry_delay : <%=virtualmachine.getBoot().getRetryDelay()%> <br>
							enter_setup_mode : <%=virtualmachine.getBoot().getEnterSetupMode()%>
							<br> type : <%=virtualmachine.getBoot().getType()%> <br>
							retry : <%=virtualmachine.getBoot().getRetry()%></td>
						<td><%=virtualmachine.getGuestOS()%></td>
						<td>upgrade_policy : <%=virtualmachine.getHardware().getUpgradePolicy()%>
							<br> upgrade_status : <%=virtualmachine.getHardware().getUpgradeStatus()%>
							<br> version : <%=virtualmachine.getHardware().getVersion()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>



