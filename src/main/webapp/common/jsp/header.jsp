<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Início</title>
		<link rel="stylesheet" href="../css/jquery-ui.css">
	 	<link rel="stylesheet" href="../css/jquery-ui.structure.css">
	  	<link rel="stylesheet" href="../css/jquery-ui.theme.css">
	  	<link rel="stylesheet" href="../css/jquery.toast.css">	
	  	<link rel="stylesheet" href="../css/select2.css">  	
		<link rel="stylesheet" type='text/css' href="css/common-home.css">
	</head>
	
<div id="menu">
	
	<%@include file="update_cart_amount.jsp" %>
	
	<ul>
		<li>
			<c:url var="logoff" value="/logoff.action"/>
			<a href="${logoff}">Sair</a>
		</li>
		
		<li>
			<c:url value="/common/see_cart.action" var="seeCart"></c:url>
			<a href="${seeCart }">
				<img class="cart-image" alt="" src="/cde_foro11/img/cart_icon.png">
				<span id="cartAmount"></span>
			</a>
			
		</li>
		
		<li>
			<c:url var="order" value="/common/see_order.action"/>
			<a href="${order}">Pedidos</a>
		</li>
		
		<li>
			<c:url var="home" value="/common/list_item.action"/>
			<a href="${home}">Início</a>
		</li>
	
	</ul>
</div>
