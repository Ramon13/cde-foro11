<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>
 <%@taglib prefix="blob" uri="/WEB-INF/blob-to-string.tld"%>
 
 <c:url var="listItens" value="/admin/ListItens.action"></c:url>
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
 
  <c:url var="listEntryItensURL" value="/admin/ListEntryItens.action">
  	<c:param name="itemId" value="${item.id}"></c:param>
  </c:url>
  <c:url var="listItemOrdersURL" value="/admin/ListItemOrders.action">
  	<c:param name="itemId" value="${item.id}"></c:param>
  	<c:param name="resetFilters" value="true"></c:param>
  	
  </c:url>

 
 <h2 class="item-title">${item.description }</h2>
<div id="tabs">
  <ul>
    <li><a href="#tabs-1"><span class="ui-icon ui-icon-gear"></span>Geral</a></li>
    <li><a id="a-entries" onclick="callListEntries('${listEntryItensURL}')" href="#tabs-2">Entradas</a></li>
    <li><a onclick="callListItemOrders('${listItemOrdersURL}')" href="#tabs-3">Saídas</a></li>
  </ul>
  
  
  <div id="tabs-1">	
			<table id="itemInfoTb">
					<tr>
						<td><h3 class="tab-h3">Imagens</h3></td>
					</tr>
					<tr>
						<td>
							<c:forEach items="${item.images }" var="image">
						  		<c:url var="loadImage" value="/resources/LoadImage">
						  			<c:param name="itemId" value="${item.id}"/>
						  			<c:param name="imageId" value="${image.id}"/>
						  		</c:url>
						  		<img id="itemImage100px" alt="" src="${loadImage}">
						  	</c:forEach>
						</td>
					</tr>
					<tr>
						<td><h3 class="tab-h3">Informações</h3></td>
					</tr>
					<tr>
						<td>
							<label for="untType">Unidade</label>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<select id="unityType" name="unityType" >
									<c:forEach var="unityType" items="${unityTypes}">
										<option value="${unityType.id }"
											<c:if test="${unityType.id eq item.unityType.id }">
												selected = "selected";
											</c:if>
										> ${unityType.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<label for="subitem"> Subitem</label>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<select id="subitem" name="subitemId">
									<c:forEach var="subitem" items="${subitens}">
										<option value="${subitem.id }"
											<c:if test="${subitem.id eq item.subitem.id }">
												selected = "selected";
											</c:if>
										> ${subitem.description }</option>
									</c:forEach>
								</select>	
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<label for="type"> Categoria</label>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div>
									<select id="type" name="typeId">
										<c:forEach var="type" items="${types}">
											<option value="${type.id }"
												<c:if test="${type.id eq item.type.id }">
													selected = "selected";
												</c:if>
											> ${type.description }</option>
										</c:forEach>
									</select>
								</div>
							</div> 	
						</td>
					</tr>
					
					<tr>
						<td>
							<label for="desc"> Descrição</label>
						</td>
					</tr>
					<tr>
						<td>
							<input id="desc" type="text" name="description" value="${item.description }"/>
						</td>
					</tr>
				
					<tr>
						<td>
							<label for="specification"> Especificações</label>
						</td>
					</tr>
					<tr>
						<td>
							<textarea id="specification" name="specification" rows="7" cols="100"><blob:convert blobObject="${item.specification }"></blob:convert></textarea>
						</td>
					</tr>
				</table>
	</div>
	<div id="tabs-2">
	
	</div>
	<div id="tabs-3">
	
	</div>
</div>