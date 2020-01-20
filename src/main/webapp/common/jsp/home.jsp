<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/common/list_item.action" var="listItem">
	<c:param name="async" value="true"/>
</c:url>

<%@include file="header.jsp" %>
	
<body onload="loadTable( '${listItem}' )">
	<div id="main">
		
		<input type="text" id="searchInput" placeholder="Insira um item" 
			onkeyup="searchItens('${listItem}', this)">
			
		<div class="div-subitem-menu">	
		
			<c:url value="/common/list_item.action" var="loadItens">
				<c:param name="async"> true </c:param>
			</c:url>	
			<a onclick="loadItensByType( '${loadItens}' ); return false;" href="#">	Todos os itens </a>
			
			<c:forEach items="${requestScope.typeList }" var="type">
				<c:if test="${type.id ne 1 }">
					<c:url value="/common/list_item.action" var="loadItens">
						<c:param name="typeId"> ${type.id } </c:param>
						<c:param name="async"> true </c:param>
						
					</c:url>
					
					<a onclick="loadItensByType( '${loadItens}' ); return false;" href="#">	 ${type.description } </a>
					
				</c:if>
			</c:forEach>
	
		</div>
		
		<div class="itens-list" id="itens-list">
			
		</div>
	</div>		
				
</body>
	
<%@include file="footer.jsp" %>