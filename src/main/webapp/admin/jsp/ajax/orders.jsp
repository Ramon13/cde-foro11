<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>


<c:url var="listOrdersURL" value="/admin/ListOrders.action" />
<c:url var="listItensURL" value="/admin/ListItens.action" />

<c:set scope="request" var="action" value="/admin/ListOrders.action"/>
<c:set scope="request" var="divTabId" value="content"/>


<c:set var="searchValues" scope="request" value='<%=new String[]{"id", "date", "login", "locale"} %>'/>
<c:set var="searchData" scope="request" value='<%=new String[]{"código", "data", "usuário", "local"} %>'/>
<c:set var="searchType" scope="request" value="${orderFilterProperties.searchProperties.searchType}"/>
<c:set var="searchKey" scope="request" value="${orderFilterProperties.searchProperties.searchKey}"/>
<jsp:include page="/admin/jsp/include/update-search-bar.jsp"/>


<c:set scope="page" var="finalizedOrderEnum" value="FINALIZED"></c:set>
<c:set scope="request" var="orderType" value="${orderFilterProperties.orderType}"/>
<jsp:include page="/admin/jsp/include/pagination-tab.jsp"/>


<div id="orders-table">
	<table>
		<thead>
			<tr>	
				<th></th>	
				<th>
					<c:set scope="request" var="columnName" value="Código"></c:set>
					<c:set scope="request" var="property" value="id"></c:set>
					<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
				</th>
				<th>
					<c:set scope="request" var="columnName" value="Data"/>
					<c:set scope="request" var="property" value="date"/>
					<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
				</th>
				<th>
					<c:set scope="request" var="columnName" value="Usuário"/>
					<c:set scope="request" var="propertyOrder" value="login"/>
					<c:set scope="request" var="propertyList" value="${logins}"/>
					<c:set scope="request" var="propertyName" value="login"/>
					<c:set scope="request" var="tagName" value="login"/>
					<c:set scope="request" var="propertyIdList" value="${orderFilterProperties.loginIdList}"/>
					<c:set scope="request" var="checkBoxClass" value="login-checkboxes"/>
					
					<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
				</th>
				<th>
					<c:set scope="request" var="columnName" value="Local"/>
					<c:set scope="request" var="propertyOrder" value="locale"/>
					<c:set scope="request" var="propertyList" value="${locales}"/>
					<c:set scope="request" var="propertyName" value="locale"/>
					<c:set scope="request" var="tagName" value="locale"/>
					<c:set scope="request" var="propertyIdList" value="${orderFilterProperties.localeIdList}"/>
					<c:set scope="request" var="checkBoxClass" value="locale-checkboxes"/>
					
					<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
				</th>
				
				<c:if test="${orderFilterProperties.orderType eq finalizedOrderEnum }">
					<th>Finalizado em:</th>
				</c:if>
			</thead>	
		</tr>
		
		<c:forEach items="${orders }" var="order">
			
			<tbody>
				<tr>
					<td>
		 				<c:url var="listItemOrdersURL" value="/admin/ListItemOrders.action">
							<c:param name="orderId" value="${order.id }"/>
							<c:param name="resetFilters" value="true"/>
						</c:url>
						<div onclick="loadItemOrders('${listItemOrdersURL}', '${order.id}')" >
							<span id="itemRowTriangle${order.id}" class="ui-icon ui-icon-triangle-1-e"></span>
						</div>
					</td>
					<td>${order.id }</td>
					<td><date:format country="BR" language="pt">${order.date}</date:format></td>
					<td>${order.login.user }</td>
					<td>${order.login.locale.description }</td>
					
					<c:if test="${orderFilterProperties.orderType eq finalizedOrderEnum and not empty order.releaseDate }">
						<td><date:format country="BR" language="pt">${order.releaseDate }</date:format></td>
					</c:if>
				</tr>
				
				<tr hidden="hidden"><td colspan="6"><div id="tableRow${order.id }"></div></td></tr>
			</tbody>
		</c:forEach>
	</table>
</div>