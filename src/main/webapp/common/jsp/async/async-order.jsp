<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld" %>


<table class="order-table">
	<tr>
			<td>Código</td>
			<td>Data</td>
			<td>Usuário</td>
			<td>Status</td>
		</tr>
	<c:forEach items="${requestScope.orderList}" var="order">

		<tr>
			<td>
				<label>
					<c:out value="${order.id }"></c:out>
				</label>
			</td>
			<td>
				<label>
					<date:format country="BR" language="pt">${order.date }</date:format>
				</label>
			</td>
			<td>
				<label>
					<c:out value="${order.login.user }"></c:out>
				</label>						</td>
			<td>
				<!-- 80 = P & 70 = F -->
				<c:choose>
						<c:when test="${order.status eq 80 }">
							<label class="status-p">Pendente</label>
						</c:when>
						
						<c:when test="${order.status eq 70 }">
							<label class="status-f">Finalizado</label>
						</c:when>
						
						<c:when test="${order.status eq 82 }">
							<label class="status-r">Autorizado</label>
						</c:when>
						
						<c:when test="${order.status eq 85 }">
							<label class="status-c">Cancelado pelo usuário</label>
						</c:when>
						
						
						<c:otherwise>
							<label class="status-c">Cancelado pelo administrador</label>
						</c:otherwise>
				</c:choose>
			</td>
			
			<td class="col-see-itens">
				<c:url value="/common/see_item_order.action" var="seeItemOrder">
					<c:param name="orderId" value="${order.id }"></c:param>
					<c:param name="loginId" value="${order.login.id }"></c:param>
				</c:url>
				 <a href="${seeItemOrder }">
				 	<img alt="Ver" src="/cde_foro11/img/icons/search_16.png">
				 </a> 
			</td>
			
		</tr>
	</c:forEach>

</table>

<div class="page-num">
	<c:forEach varStatus="status" begin="1" end="${requestScope.numPages }">
			
			<c:url value="/common/see_order.action" var="seeOrder">
				<c:param name="async" value="true" />
				<c:param name="pageNum" value="${status.count }"/>
			</c:url>
			
			<input id="pageNum" type="button" value="${status.count }" 
				onclick="loadOrdersByPage ('${seeOrder}' )">
	</c:forEach>
</div>
