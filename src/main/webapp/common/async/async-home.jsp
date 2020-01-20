<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<c:choose>
		<c:when test="${requestScope.itemList eq null }">
			Selecione uma categoria.
		</c:when>
		
		<c:otherwise>
			<table class="user-shopping">
				<c:forEach items="${requestScope.itemList }" var="item" varStatus="loop">
					
					<c:if test="${loop.index % 5 eq 0 }">
						<tr>
					</c:if>	
					
					<td>
						<div id="card">
							<c:url value="/common/item_market.action" var="itemMarket">
								<c:param name="itemId" value="${item.id }"/>
							</c:url>
							<div class='item-image-div'>
								<a href="${itemMarket }">
	
									<c:choose>
										<c:when test="${empty item.mainImage}">
											<img class="item-image" src="/cde_foro11/img/no-image.jpg">
										</c:when>
										<c:otherwise>
											<c:url var="loadImage" value="/resources/LoadImage">
									  			<c:param name="itemId" value="${item.id}"/>
									  			<c:param name="imageId" value="${item.mainImage}"/>
									  		</c:url>
											<img class="item-image"src="${loadImage }">
										</c:otherwise>
									</c:choose>
	
								</a>
							</div>
							
							<div class="item-description">
								<input type="hidden" value="${item.id }" id="itemId">
								<a href="${itemMarket }">
									<c:out value="${item.description }"></c:out>
								</a>
								<br>
								<c:if test="${item.currentAmount le 0 }">
									<span class="unavaliable-in-stock">Indisponível em estoque</span>
								</c:if>
							</div>
							
							<div id="opContainer">
								<c:url value="/common/add_cart.action" var="addCart">
									<c:param name="async" value="true"/>
									<c:param name="itemId" value="${item.id }"/>
								</c:url>
								
								<div id="itemAmount">
									<c:if test="${item.currentAmount gt 0 }">
										<div id="itemAmountContainer">
											<table>
												<tr id="tr-amountLbl">
													<td id="td-amountLbl" rowspan="2">
														<input id="amountLbl" type="text" value="1">	
													</td>
													<td>
														<button id="addBtn" onclick="addCartAmount(this)" >+</button>
													</td>
													<td rowspan="2">
														<button type="button" class="addCartBtn" onclick="addCart( '${addCart}', this )">
															<img class="cart-image" alt="" src="/cde_foro11/img/cart_icon.png">
															Adicionar ao Carrinho
														</button>
													</td>
												</tr>
												<tr>
													<td>
														<button id="minBtn" onclick="minCartAmount(this)">-</button>
													</td>
													
												</tr>
											</table>
										</div>
									</c:if>
								</div>
							</div>
							
						</div>
					</td>
					
				</c:forEach>
			</table>
			
		</c:otherwise>
		
	</c:choose>
	
	<div class="page-num">
		<c:forEach varStatus="status"  begin="1" end="${requestScope.numPages }">
		
				<c:url value="/common/list_item.action" var="listItem">
					<c:param name="async" value="true"/>
					<c:param name="pageNum" value="${status.count}" />
					<c:param name="typeId" value="${requestScope.typeId}" />
					
				</c:url>
				<input id="pageNum" type="button" value="${status.count }" 
					onclick="loadItensByPage( '${listItem}' )">
		</c:forEach>
	</div>	
