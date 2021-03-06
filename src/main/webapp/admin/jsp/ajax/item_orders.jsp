<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>


<c:url var="listItemOrdersURL" value="/admin/ListItemOrders.action"/>

<%-- the action used by search bar and pagination bar --%>
<c:set scope="request" var="action" value="/admin/ListItemOrders.action"/>

<c:choose>
	<%--if itemId isnt empty configure search bar and pagination bar to list order by item--%>
	<c:when test="${not empty itemId }">
		
		<c:set scope="request" var="entitySearchName" value="itemId"></c:set>
		<c:set scope="request" var="entitySearchId" value="${itemId }"></c:set>
		<c:set scope="request" var="entityName" value="itemId"></c:set>
		<c:set scope="request" var="entityId" value="${itemId }"></c:set>
		
		<c:set scope="request" var="divTabId" value="tabs-3"></c:set>
		
		<c:set var="searchValues" scope="request" value='<%=new String[]{"order", "date", "amount", "locale"} %>'/>
		<c:set var="searchData" scope="request" value='<%=new String[]{"código", "data", "quantidade", "local"} %>'/>
		
		<c:set var="searchType" scope="request" value="${orderFilterProperties.searchProperties.searchType}"/>
		<c:set var="searchKey" scope="request" value="${orderFilterProperties.searchProperties.searchKey}"/>
		<jsp:include page="/admin/jsp/include/update-search-bar.jsp"/>
	</c:when>
	
	<%--else itemId isnt empty list orders by item --%>
	<c:otherwise>
		<c:set scope="request" var="entityName" value="orderId"></c:set>
		<c:set scope="request" var="entityId" value="${orderId }"></c:set>
		<c:set scope="request" var="divTabId" value="content"></c:set>
		
		<c:set var="searchValues" scope="request" value='<%=new String[]{"order", "date", "amount", "locale"} %>'/>
		<c:set var="searchData" scope="request" value='<%=new String[]{"código", "data", "quantidade", "local"} %>'/>
	</c:otherwise>
</c:choose>



<c:if test="${entityName eq 'itemId' }">
	
	<jsp:include page="/admin/jsp/include/pagination-tab.jsp"/>

 </c:if>
<div id="entries-table">
	<table>
		<thead>
			<tr>
				<c:if test="${entityName eq 'orderId' }">
					<th></th>
				</c:if>
				
				<c:if test="${entityName eq 'itemId' }">
					<th></th>
					<th>
						<c:set scope="request" var="columnName" value="Padido"/>
						<c:set scope="request" var="property" value="id"/>
						<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
					</th>
					<th>
						<c:set scope="request" var="columnName" value="Date"/>
						<c:set scope="request" var="property" value="date"/>
						<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
					</th>
					<th>
						<c:set scope="request" var="columnName" value="Local"/>
						<c:set scope="request" var="property" value="locale"/>
						<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
					</th>
					<th>
						<c:set scope="request" var="columnName" value="Quantidade"/>
						<c:set scope="request" var="property" value="amount"/>
						<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
					</th>
				</c:if>
				
				<c:if test="${entityName eq 'orderId' }">
					
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th colspan="12">Consumo (Ultimos 12 meses:)</th>
						</tr>
					<thead id="childItemOrderThead">
						<tr>
							<th></th>
							<th><label>Descrição</label></th>
							<th><label>Quantidade</label></th>
							<th><label>Estoque Atual</label></th>
							<th>
								<label>
									(${orderItens[1].order.login.locale.description }) Consumo de:
									 <date:format country="BR" language="pt">${startDate}</date:format> 
									até: <date:format country="BR" language="pt">${currentDate }</date:format>
								</label>
							</th>
							
							<%
									
									for(int i = 0; i < 12 ; i++){	
										 LocalDate ld = LocalDate.now().minusMonths(i);
								%>
									<th>
										<div class="verticalTableHeader">
											<%= 
												ld.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt-BR"))
											%>
											-
											<%=
												ld.getYear()
											%>
										</div>
									</th>
								<%
									}
								%>
						</tr>
					</thead>
				</c:if>
			</tr>
		</thead>	
		
		
			<c:forEach items="${orderItens}" var="orderItem">
				<tbody>
					<tr>
					<c:if test="${entityName eq 'itemId' }">
						<td>
			 				<c:url var="listItemOrdersURL" value="/admin/ListItemOrders.action">
								<c:param name="orderId" value="${orderItem.order.id }"/>
								<c:param name="itemOrder" value="true"/>
							</c:url>
							<div onclick="loadItemOrders('${listItemOrdersURL}', '${orderItem.id}')" >
								<span id="itemRowTriangle${orderItem.id}" class="ui-icon ui-icon-triangle-1-e"></span>
							</div>
						</td>
						<td>${orderItem.order.id }</td>
						<td><date:format country="BR" language="pt">${orderItem.order.date}</date:format></td>
						<td>${orderItem.order.login.locale.description}</td>
						<td>${orderItem.amount}</td>
					</c:if>
					
					<c:if test="${entityName eq 'orderId' }">
						<td>
							<c:url var="loadImage" value="/resources/LoadImage">
					  			<c:param name="itemId" value="${orderItem.item.id}"/>
					  			<c:param name="imageId" value="${orderItem.item.mainImage}"/>
					  		</c:url>
					  		<img id="itemImage50px" alt="" src="${loadImage}">
						</td>
						<td>${orderItem.item.description }</td>
						<td>${orderItem.amount}</td>
						<td>
							<label><c:out value="${orderItem.item.currentAmount }"></c:out></label>
						</td>
						<td>
							<c:set var="localeAmount" value="0" scope="page"></c:set>
							
							<c:forEach var="orderByLocale" items="${ordersByLocale }">
								<c:forEach var="localeOrderItem" items="${orderByLocale.orderItens }">
									<c:if test="${localeOrderItem.item.id eq orderItem.item.id }">
											
											<c:if test="${orderByLocale.status eq 70 
												and orderByLocale.date ge startDate
												and orderByLocale.date le currentDate }">
												<c:set var="localeAmount" value="${localeAmount + localeOrderItem.amount}" scope="page"></c:set>
											
											</c:if>
									</c:if>
								</c:forEach>
								
							</c:forEach>
							
							<c:out value="${localeAmount }"></c:out> 
						</td>
						
						<%
									
									for(int i = 0; i < 12 ; i++){	
										 LocalDate ld = LocalDate.now().minusMonths(i);
										 pageContext.setAttribute("ld", ld);
						%>				
									<td>
										<c:set var="localeAmount" value="0" scope="page"></c:set>
										<c:forEach var="orderByLocale" items="${ordersByLocale }">
											<c:if test="${orderByLocale.date.month eq ld.month
															and orderByLocale.date.year eq ld.year
															and orderByLocale.status eq 70}">
																
												<c:forEach var="localeOrderItem" items="${orderByLocale.orderItens }">
													<c:if test="${localeOrderItem.item.id eq orderItem.item.id }">
															
														<c:set var="localeAmount" value="${localeAmount + localeOrderItem.amount}" scope="page"></c:set>
														
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach>
									
										<c:out value="${localeAmount }"></c:out> 
									
									</td>
								<%
									}
								%>
					</c:if>
					</tr>
				
					<tr hidden="hidden"><td colspan="5"><div id="tableRow${orderItem.id }"></div></td></tr>
				
				</tbody>
			</c:forEach>
		</tbody>
	</table>
</div>