<%@page import="br.com.javamon.admin.domain.History"%>
<%@page import="br.com.javamon.admin.domain.ApplicationHistory"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>


<c:url var="listEntryItensURL" value="/admin/ListEntryItens.action" />

<%-- if itemId isnt empty list entries by item --%>
<c:if test="${not empty itemId }">
	
	<%-- the container used to put ajax result --%>
	<c:set scope="request" var="divTabId" value="tabs-2"/>
	<%-- the action used by search bar and pagination bar --%>
	<c:set scope="request" var="action" value="/admin/ListEntryItens.action"/>
		
	<%-- search bar configuration --%>
	<c:set var="searchValues" scope="request" value='<%=new String[]{"id", "date", "amount", "unity_value", "total"} %>'/>
	<c:set var="searchData" scope="request" value='<%=new String[]{"código", "data", "quantidade", "valor unitário", "total"} %>'/>
	<c:set var="searchType" scope="request" value="${itemFilterProperties.searchProperties.searchType}"/>
	<c:set var="searchKey" scope="request" value="${itemFilterProperties.searchProperties.searchKey}"/>
	<c:set scope="request" var="entitySearchName" value="itemId"/>
	<c:set scope="request" var="entitySearchId" value="${itemId}"/>
	<jsp:include page="/admin/jsp/include/update-search-bar.jsp"/>
		
	<jsp:include page="/admin/jsp/include/pagination-tab.jsp"/>
</c:if>

<div id="entries-table">
	<table>
		<thead>
			<tr>
				<c:choose>	
					<%-- table head when list entries by table --%>
					<c:when test="${not empty itemId }">
						<th>
							<c:set scope="request" var="columnName" value="Código"/>
							<c:set scope="request" var="property" value="id"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Data"/>
							<c:set scope="request" var="property" value="date"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Quantidade"/>
							<c:set scope="request" var="property" value="amount"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Valor Unitário"/>
							<c:set scope="request" var="property" value="unity_value"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Valor Total"/>
							<c:set scope="request" var="property" value="total"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
					</c:when>
					
					<%-- table head when list entries by document --%>
					<c:otherwise>
						<thead id="childEntryThead">
							<tr>
								<th>
									<label>Item</label>
								</th>
								<th>
									<label>Data</label>
								</th>
								<th>
									<label>Quantidade</label>
								</th>
								<th>
									<label>Valor Unitário</label>
								</th>
								<th>
									<label>Valor Total</label>
								</th>
							</tr>
						</thead>
					</c:otherwise>
				</c:choose>
				
			</tr>
		</thead>
		
		
			<c:forEach items="${entryItens}" var="entryItem">
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${not empty itemId }">
								<td>${entryItem.id}</td>
							</c:when>
							<c:otherwise>
								<td>${entryItem.item.description}</td>
							</c:otherwise>
						</c:choose>
						
						<td><date:format country="BR" language="pt">${entryItem.entry.date}</date:format></td>
						<td>${entryItem.amount}</td>
						<td>${entryItem.unityValue}</td>
						<td>${entryItem.total}</td>				
					</tr>
				</tbody>
			</c:forEach>
	</table>
</div>