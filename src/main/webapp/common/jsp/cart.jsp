<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Carrinho</title>
		
	</head>

	<%@include file="update_cart_amount.jsp" %>
	<body>
	
		<header>
			<c:import url="header.jsp"></c:import>
		</header>
		
		<div id="content">
			<h1>Carrinho</h1>
			
			<c:choose>
				<c:when test="${fn:length(cart.cartItens) eq 0 }">
					<label>Seu carrinho está vazio.</label>
				</c:when>
				
				<c:otherwise>
							
					<c:url var="saveOrder" value="/common/save_order.action"/>
					
					<form method="GET" action="${saveOrder }">
						<table class="cart-table">
							<tr>
								<td></td>
								<td></td>
								<td>Quantidade</td>		
							</tr>
							<c:forEach items="${cart.cartItens }" var="cartItem">
								<tr>
									<td>
										<c:choose>
											<c:when test="${fn:length(cartItem.item.images) ne 0}">
												<c:url var="loadImage" value="/resources/LoadImage">
										  			<c:param name="itemId" value="${cartItem.item.id}"/>
										  			<c:param name="imageId" value="${cartItem.item.mainImage}"/>
									  			</c:url>
												<img class="cart-item-image"src="${loadImage }">
											</c:when>
											<c:otherwise>
												<img class="cart-item-image" src="/cde_foro11/img/no-image.jpg">
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<label>
											<c:out value="${cartItem.item.description }"></c:out>
										</label>
									</td>
									<td>
										<c:url value="/common/edit_amount.action" var="editAmount"/>
										<div id="itemAmountDiv">
											<c:choose>
												<c:when test="${cartItem.amount lt 10 }">
													<select id="itemAmount" name="itemAmount:${cartItem.item.id }" onchange="changeItemAmount(this)">
														<c:forEach begin="1" end="9" varStatus="status">
															<option value="${status.count }" 
																<c:if test="${cartItem.amount eq status.count }">
																	selected='selected'
																</c:if>
															> ${status.count } </option>
														</c:forEach>
															<option value="10+" > 10+ </option>
													</select>	
												</c:when>
												<c:otherwise>
													<input id="itemAmount" type="text" name="itemAmount:${cartItem.item.id }" value="${cartItem.amount }">
												</c:otherwise>
											</c:choose>
											
										</div>
									</td>
									<td>
										<c:url value="/common/delete_cart_item.action" var="deleteItem">
											<c:param name="itemId" value="${cartItem.item.id }"></c:param>
										</c:url>
										<a href="${deleteItem }">
											<img src="/cde_foro11/img/icons/delete_16.png">
										</a>
									</td>
								</tr>
							</c:forEach>
		
						</table>
							
						<div class="finish-order-div">
							<input type="submit" value="Finalizar Pedido">
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>		
	</body>
	
	<%@include file="footer.jsp" %>		
</html>