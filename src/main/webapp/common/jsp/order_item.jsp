<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="header.jsp" %>		

<body>
	
	<div id="content">
		<h1>Pedido</h1>
							
		<table class="cart-table">
			<tr>
				<td></td>
				<td></td>
				<td>Quantidade</td>
			</tr>
			<c:forEach items="${orderItens}" var="orderItem">
				<tr>
					<td>
						<c:choose>
							<c:when test="${fn:length(orderItem.item.images) ne 0}">
								<c:url value="/resources/LoadImage" var="loadImage">
									<c:param name="itemId" value="${orderItem.item.id}"/>
									<c:param name="imageId" value="${orderItem.item.mainImage}"/>
								</c:url>
								<img class="cart-item-image" src="${loadImage }">
							</c:when>
							<c:otherwise>
								<img class="cart-item-image" src="/cde_foro11/img/no-image.jpg">
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<label>
							<c:url value="/common/item_market.action" var="itemMarket">
								<c:param name="itemId" value="${orderItem.item.id }"/>
							</c:url>
							<a href="${itemMarket }">
								<c:out value="${orderItem.item.description }"></c:out>
							</a>
						</label>
					</td>
					<td>
						<label>
							<c:out value="${orderItem.amount}"></c:out>
						</label>
					</td>
					
					<c:if test="${orderItem.order.status eq 80 }">
						<td>
							<c:url value="/common/delete_order_item.action" var="deleteOrderItem">
								<c:param name="orderItemId" value="${orderItem.id}"></c:param>
								<c:param name="orderId" value="${orderItem.order.id}"></c:param>
								<c:param name="loginId" value="${orderItem.order.login.id}"></c:param>
							</c:url>
							<a href="${deleteOrderItem }">
								<img src="/cde_foro11/img/icons/delete_16.png">
							</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>

		</table>
		
		
		<c:if test="${sessionScope.login.readjustToCart eq true }">
			<c:url value="/common/readjustToCart.action" var="readjustToCart">
				<c:forEach items="${requestScope.orderItens}" var="orderItem">
					<c:param name="item" value="${orderItem.item.id }"/>
					<c:param name="itemAmount" value="${orderItem.amount }"/>
				</c:forEach>
			</c:url>
			
			<div class="order-options">
				<a onclick="addCartById('${readjustToCart}')" href="#" >Re-adicionar itens ao carrinho</a>
			</div>
		</c:if>
		
		<c:if test="${requestScope.orderItens[0].order.status eq 80}">
			<div class="order-options">
				<c:url var="cancelOrder" value="/common/cancel_order.action">
					<c:param name="orderId" value="${requestScope.orderItens[0].order.id }"></c:param>
				</c:url>
				
				<a	href="${cancelOrder }" >Cancelar Pedido</a>
			</div>
		</c:if>
	</div>		
</body>
	
	<%@include file="footer.jsp" %>