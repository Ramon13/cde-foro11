<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:url var="listEntriesURL" value="/admin/ListEntries.action" />
<c:url var="addEntryURL" value="/admin/AddEntry.action"/>
<c:set scope="request" var="action" value="/admin/ListEntries.action"></c:set>
<c:set scope="request" var="divTabId" value="content"/>
<c:set scope="request" var="itemId" value="${itemId }"/>

<script>
	$("#searchInput").val("${filterProperties.searchProperties.searchKey}");
	
	$(document).ready(function(){
	$( function() {
	    $( "#tabs" ).tabs({
	      beforeLoad: function( event, ui ) {
	        ui.jqXHR.fail(function() {
	          ui.panel.html(
	            "Couldn't load this tab. We'll try to fix this as soon as possible. ");
	        });
	      }
	    });
	});	 
});
</script>
 
<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><span class="ui-icon ui-icon-gear"></span>Entradas</a></li>
		<li><a id="a-entries" onclick="callNewEntry('${addEntryURL}', 'tabs-2')" href="#tabs-2">Nova Entrada</a></li>
	</ul>
	
	<div id="tabs-1">
		<c:set var="searchValues" scope="request" value='<%=new String[]{"id", "date", "provider", "document_number"} %>'/>
		<c:set var="searchData" scope="request" value='<%=new String[]{"código", "data", "fornecedor", "nº documento"} %>'/>
		<c:set var="searchType" scope="request" value="${entryFilterProperties.searchProperties.searchType}"/>
		<c:set var="searchKey" scope="request" value="${entryFilterProperties.searchProperties.searchKey}"/>
		<jsp:include page="/admin/jsp/include/update-search-bar.jsp"/>
		
		
		<c:set scope="request" var="path" value="/admin/ListEntries.action"/>
		<jsp:include page="/admin/jsp/include/pagination-tab.jsp"/>
		
		<div id="entries-table">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>
							<c:set scope="request" var="columnName" value="Código"/>
							<c:set scope="request" var="property" value="id"/>
							<c:set scope="request" var="divTabId" value="${divTabId}"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Data"/>
							<c:set scope="request" var="property" value="date"/>
							<c:set scope="request" var="divTabId" value="${divTabId}"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Fornecedor"></c:set>
							<c:set scope="request" var="propertyOrder" value="provider"></c:set>
							<c:set scope="request" var="property" value="provider"></c:set>
							<c:set scope="request" var="propertyList" value="${providerList}"></c:set>
							<c:set scope="request" var="propertyName" value="provider"></c:set>
							<c:set scope="request" var="tagName" value="provider"></c:set>
							<c:set scope="request" var="propertyIdList" value="${entryFilterProperties.providerIds}"></c:set>
							<c:set scope="request" var="checkBoxClass" value="provider-checkboxes"></c:set>
							
							<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
						</th>
						<th>
							<c:set scope="request" var="columnName" value="Nº documento"/>
							<c:set scope="request" var="property" value="document"/>
							<c:set scope="request" var="divTabId" value="${divTabId}"/>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
					</tr>
				</thead>
					
				<c:forEach items="${entries}" var="entry">
					<tbody>
						<tr>
							<td>
				 				<c:url var="listEntryItensURL" value="/admin/ListEntryItens.action">
									<c:param name="entryId" value="${entry.id}"/>
								</c:url>
								<div onclick="loadEntryItens('${listEntryItensURL}', '${entry.id}')" >
									<span id="itemRowTriangle${entry.id}" class="ui-icon ui-icon-triangle-1-e"></span>
								</div>
							</td>
							<td>${entry.id}</td>
							<td><date:format country="BR" language="pt">${entry.date}</date:format></td>
							<td>${entry.provider.description}</td>
							<td>${entry.document.number}</td>				
						</tr>
						
						<tr hidden="hidden"><td colspan="5"><div id="tableRow${entry.id }"></div></td></tr>
		
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<div id="tabs-2">
	
	</div>
</div>

