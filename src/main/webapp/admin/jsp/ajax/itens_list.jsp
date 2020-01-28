<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>

<c:url var="listItensURL" value="/admin/ListItens.action"/>
<c:url var="loadItemPath" value="/admin/LoadItem.action"/>
<c:url var="changeFilterDate" value="/admin/ChangeFilterDate.action" />

<c:set scope="request" var="divTabId" value="content"/>
<c:set scope="request" var="action" value="/admin/ListItens.action"/>

<c:set var="searchValues" scope="request" value='<%=new String[]{"id", "description", "unity_type", "subitem", "type"} %>'/>
<c:set var="searchData" scope="request" value='<%=new String[]{"código", "descrição", "unidade", "subitem", "categoria"} %>'/>
<c:set var="searchType" scope="request" value="${itemFilterProperties.searchProperties.searchType}"/>
<c:set var="searchKey" scope="request" value="${itemFilterProperties.searchProperties.searchKey}"/>
<jsp:include page="/admin/jsp/include/update-search-bar.jsp"/>


<jsp:include page="/admin/jsp/include/pagination-tab.jsp"/>
<div class="list-type-img">
	<div id="grid" onclick="changeItensLayout(this, '${listItensURL}', '${divTabId }')"
		<c:if test="${listLayout eq 'grid' }">class="selected-list-type-img"</c:if> >
		<img id="grid-type-img" src="../img/icons/grid-list.png">
	</div>
	<div id="list" onclick="changeItensLayout(this, '${listItensURL}', '${divTabId }')"
		 <c:if test="${listLayout eq 'list' }">class="selected-list-type-img"</c:if> >
		<img id="list-type-img" src="../img/icons/list-icon.png"> 
	</div> 
</div>

<c:choose>
	
	<c:when test="${fn:length(itens) eq 0}">
		<div><span class="ui-icon ui-icon-search"></span>Sem resultados para a pesquisa</div>
	</c:when>
	
	<c:when test="${listLayout eq 'list' }">
		 <div id="table-body-div">
			 <table id="itens-table">
			 	<!-- CABECALHO DA TABELA - INICIO -->
			<thead>
				<tr>
					<th colspan="6"></th>
					<th colspan="${fn:length(locales)}">
						<label>
							<span>Consumo por seção -</span>
							<span>${lastYear }</span>
						</label>
					</th>
					<th></th>
					<th colspan="${fn:length(locales)}">
						<label>
							<span>Consumo de:</span>
							<date:format country="BR" language="pt">${startDate}</date:format> 
							<span>até:</span> 
							<date:format country="BR" language="pt">${currentDate }</date:format>
						</label>
					</th>
				</tr>
				 	<tr id="second-table-head">
				 		<th></th>
						<th>
							<c:set scope="request" var="columnName" value="Código"></c:set>
							<c:set scope="request" var="property" value="id"></c:set>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						
						<th>
							<c:set scope="request" var="columnName" value="Descrição"></c:set>
							<c:set scope="request" var="property" value="description"></c:set>
							<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
						</th>
						
						<th>
							<c:set scope="request" var="columnName" value="Unidade"></c:set>
							<c:set scope="request" var="propertyOrder" value="unityType"></c:set>
							<c:set scope="request" var="propertyList" value="${unityTypeList}"></c:set>
							<c:set scope="request" var="propertyName" value="unityType"></c:set>
							<c:set scope="request" var="tagName" value="unity_type"></c:set>
							<c:set scope="request" var="propertyIdList" value="${itemFilterProperties.unityTypeIdList}"></c:set>
							<c:set scope="request" var="checkBoxClass" value="unity-type-checkboxes"></c:set>
							
							<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
						</th>
						
						<th>
							<c:set scope="request" var="columnName" value="Subitem"></c:set>
							<c:set scope="request" var="propertyOrder" value="subitem"></c:set>
							<c:set scope="request" var="propertyList" value="${subitemList}"></c:set>
							<c:set scope="request" var="propertyName" value="subitem"></c:set>
							<c:set scope="request" var="tagName" value="subitem"></c:set>
							<c:set scope="request" var="propertyIdList" value="${itemFilterProperties.subitemIdList}"></c:set>
							<c:set scope="request" var="checkBoxClass" value="subitem-checkboxes"></c:set>
							
							<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
						</th>
					
						<th>
							<c:set scope="request" var="columnName" value="Categoria"></c:set>
							<c:set scope="request" var="propertyOrder" value="type"></c:set>
							<c:set scope="request" var="propertyList" value="${itemTypeList}"></c:set>
							<c:set scope="request" var="propertyName" value="type"></c:set>
							<c:set scope="request" var="tagName" value="type"></c:set>
							<c:set scope="request" var="propertyIdList" value="${itemFilterProperties.itemTypeIdList}"></c:set>
							<c:set scope="request" var="checkBoxClass" value="type-checkboxes"></c:set>
							
							<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
						</th>
						
				 	
						<c:forEach var="locale" items="${requestScope.locales }">
							<th>
								<div class="verticalTableHeader">${locale.description }</div>
							</th>
						</c:forEach>
							
						<th>
							<label >Estoque Anterior</label>
							<br>
							<input class="inputDate" type="date" value="${startDate }" 
								onkeypress="changeFilterDate('${changeFilterDate}', 'startDate', this, event, 'content')"/>
						</th>
						
						<c:forEach var="locale" items="${requestScope.locales }">
							<th>
									<div class="verticalTableHeader">${locale.description }</div>
							</th>
						</c:forEach>
						
						<th>
							<!-- 
							<input type="button" name="amount" value="Estoque Atual" onclick="sortByProperty('${listItem}', this)">
							-->
							<label>Estoque Atual</label>
							<br>
							<input class="inputDate" type="date" value="${currentDate }"
								onkeypress="changeFilterDate('${changeFilterDate}', 'currentDate', this, event, 'content')"/>
						</th>
						
					</tr>
				</thead>
			 	<c:forEach items="${itens }" var="item" varStatus="loop">
			 		<tbody>
			 			<tr >
			 			<td>
			 				<c:url value="/admin/LoadItemCharts.action" var="loadItemCharts">
			 					<c:param name="itemId" value="${item.id }"/>
			 				</c:url>
							<div onclick="showGraphs('${loadItemCharts}', ${item.id})" >
								<span id="itemRowTriangle${item.id}" class="ui-icon ui-icon-triangle-1-e"></span>
							</div>
						</td>
				 		<td>${item.id }</td>
						<td>${item.description }</td>
						<td>${item.unityType.description }</td>
						<td>${item.subitem.description }</td>
						<td>${item.type.description }</td>
				 	
					 	<!-- SAÍDA POR ITEM/LOCAL DO ANO ANTERIOR -->
						<c:forEach var="locale" items="${requestScope.locales }">
							<c:set var="localeAmount" value="0" scope="page"></c:set>
				
							<c:forEach var="orderItem" items="${item.orderItens }">
								<c:if test="${orderItem.order.status eq 70
									and orderItem.order.date.year eq lastYear}">
									
									<c:if test="${locale.id eq orderItem.order.login.locale.id}">
										<c:set var="localeAmount" value="${localeAmount + orderItem.amount}" scope="page"></c:set>
									</c:if>
								</c:if> 
							</c:forEach>
									
							<td class="col-7">
								<c:out value="${localeAmount }"></c:out> 
							</td>
						</c:forEach>
						
						<!-- QUANTIDADE EM ESTOQUE ATÉ A DATA INICIAL-->
						<td class="col-7">
							<c:set var="entryAmount" value="0" scope="page"></c:set>
							<c:set var="orderAmount" value="0" scope="page"></c:set>
							<c:set var="currentAmount" value="0" scope="page"></c:set>
							
							<c:forEach items="${item.entryItens }" var="entryItem">
								<c:if test="${entryItem.entry.date le startDate }">
									<c:set var="entryAmount" value="${entryAmount + entryItem.amount }" scope="page"></c:set>
								</c:if>
							</c:forEach>
							<c:forEach items="${item.orderItens }" var="orderItem">
								<c:if test="${orderItem.order.date le startDate and orderItem.order.status eq 70}">
									<c:set var="orderAmount" value="${orderAmount + orderItem.amount }" scope="page"></c:set>
								</c:if>
							</c:forEach>
							<c:out value="${entryAmount - orderAmount }"/>
						</td>
						
						<!-- QUANTIDADE DE SAÍDAS ENTRE A DATA INICIAL E A DATA FINAL POR LOCAL-->
						<c:forEach var="locale" items="${requestScope.locales }">
							
							<c:set var="localeAmount" value="0" scope="page"></c:set>
							
							<c:forEach var="orderItem" items="${item.orderItens }">
				
								<c:if test="${orderItem.order.status eq 82 or orderItem.order.status eq 70
									and orderItem.order.date ge startDate
									and orderItem.order.date le currentDate }">
								
									<c:if test="${locale.id eq orderItem.order.login.locale.id}">
										<c:set var="localeAmount" value="${localeAmount + orderItem.amount}" scope="page"></c:set>
									</c:if>
								
								</c:if> 
								
							</c:forEach>
									
							<td class="col-7">
								<c:out value="${localeAmount }"></c:out> 
							</td>
						</c:forEach>
							
						<!-- QUANTIDADE EM ESTOQUE ATÉ A DATA FINAL -->
						<td>
							<c:set var="entryAmount" value="0" scope="page"></c:set>
							<c:set var="orderAmount" value="0" scope="page"></c:set>
							<c:set var="currentAmount" value="0" scope="page"></c:set>
							
							<c:forEach items="${item.entryItens }" var="entryItem">
								<c:if test="${entryItem.entry.date le currentDate }">
									<c:set var="entryAmount" value="${entryAmount + entryItem.amount }" scope="page"></c:set>
								</c:if>
							</c:forEach>
							<c:forEach items="${item.orderItens }" var="orderItem">
								<c:if test="${orderItem.order.date le currentDate 
									and orderItem.order.status eq 70 or orderItem.order.status eq 82 }">
									<c:set var="orderAmount" value="${orderAmount + orderItem.amount }" scope="page"></c:set>
								</c:if>
							</c:forEach>
							<c:out value="${entryAmount - orderAmount }"/>
						</td>
					
					
						<td>
							<div onclick="callItemInfo('${loadItemPath }', '${item.id}')" >
								<img src="../img/icons/search_16.png">
							</div>
						</td>	
					</tr>
					
					<tr hidden="hidden"><td colspan="25"><div id="tableRow${item.id }"></div></td></tr>
			 		</tbody>
				 	
				</c:forEach>
				
			</table>
			
			<div id="numOfItems">
				<select id="numItems" name="numItems">
					<c:set value="${paginationProperties.maxNumOfItems }" var="maxNumItems"/>
					<c:out value="${maxNumItems }"></c:out>
					<c:forEach begin="50" step="50" end="100" var="value">
						<option value="${value}" <c:if test="${value eq maxNumItems }"> selected='selected' </c:if> >${value}</option>
					</c:forEach>
					<option value="0" <c:if test="${maxNumItems > 100 }"> selected='selected'</c:if>>Todos</option>
				</select>
			</div>
		</div>
	</c:when>
	
	<c:otherwise>
		<table class="user-shopping">
			<c:forEach items="${itens}" var="item" varStatus="loop">
				<c:if test="${loop.index % 5 eq 0 }">
					<tr>
				</c:if>	
				<td>
					<div id="card">
						<div class='item-image-div'>
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
						</div>
						<div class="item-description">
							<input type="hidden" value="${item.id }" id="itemId">
							<a href="#">
								<c:out value="${item.description }"></c:out>
							</a>
							<br>
							<c:if test="${item.currentAmount le 0 }">
								<span class="unavaliable-in-stock">Indisponível em estoque</span>
							</c:if>
						</div>
					</div>
				</td>
			</c:forEach>
		</table>	
	</c:otherwise>
</c:choose>

<c:url value="/admin/ChangeNumItems.action" var="changeNumItems"/>
<script>
	$(document).ready(function(){
		$("#numItems").on("change", function(){
			var selectedVal = $(this).children("option:selected").val();
			var url = '${changeNumItems}?numItems=' + selectedVal;
			ajaxCall(url, "content");
		});
	});
</script>
