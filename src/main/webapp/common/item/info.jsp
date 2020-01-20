<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="blob" uri="/WEB-INF/blob-to-string.tld"%>


<%@include file="/jsp/common/header.jsp" %>
<body>
	<div id="content">
		
		<div id="img-gallery">
			<ul>
				<c:choose>
					<c:when test="${fn:length(item.images) gt 0 }">
						<li>
							<c:url value="/resources/LoadImage" var="loadImage">
								<c:param name="imageId" value="${item.mainImage }"></c:param>
							</c:url>
							<img src="${loadImage }" onclick="changeImage('${loadImage}')">
						</li>
						<c:forEach items="${item.images }" var="image">
							<c:if test="${image.id ne item.mainImage }">
								<li>
									<c:url value="/resources/LoadImage" var="loadImage">
										<c:param name="imageId" value="${image.id }"></c:param>
									</c:url>
									<img src="${loadImage }" onclick="changeImage('${loadImage}')">
								</li>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="main-image">
			<c:choose>
				<c:when test="${empty item.mainImage }">
					<img class="item-image" src="/cde_foro11/img/no-image.jpg">
				</c:when>
				<c:otherwise>
					<c:url value="/resources/LoadImage" var="loadImage">
						<c:param name="imageId" value="${item.mainImage }"></c:param>
					</c:url>
					<img id="main-img" src="${loadImage }">
				</c:otherwise>
			</c:choose>
		</div>
		<div id="item-info">
			<h1>${requestScope.item.description }</h1>
			
			<div id="opContainer">
			
				<c:if test="${requestScope.item.currentAmount gt 0 }">
					<c:url value="/common/add_cart.action" var="addCart">
						<c:param name="async" value="true"/>
						<c:param name="itemId" value="${item.id }"/>
					</c:url>
					
					<div id="itemAmount">
						<button id="addBtn" onclick="addCartAmount(this)">+</button>
						<label id="amountLbl">1</label>
						<button id="minBtn" onclick="minCartAmount(this)">-</button>
						<input type="button" class="addCartBtn" 
							value="Adicionar ao Carrinho" onclick="addCart( '${addCart}', this )" />
					</div>					
				</c:if>
			</div>
			
			<div class="item-desc">
				<p><blob:convert blobObject="${requestScope.item.specification }"></blob:convert></p>
			</div>
		</div>
	</div>		
</body>
	
<%@include file="/jsp/common/footer.jsp" %>

