<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>

<c:url value="/admin/ListLogins.action" var="listLogins"/>
<c:url value="/admin/ListItens.action" var="listItens"/>

<c:set scope="request" var="action" value="/admin/ListLogins.action"/>
<c:set scope="request" var="divTabId" value="content"/>

<c:set var="searchValues" scope="request" value='<%=new String[]{"id", "login", "locale", "permission"} %>'/>
<c:set var="searchData" scope="request" value='<%=new String[]{"código", "usuário", "local", "permissão"} %>'/>
<c:set var="searchType" scope="request" value="${loginFilterProperties.searchProperties.searchType}"/>
<c:set var="searchKey" scope="request" value="${loginFilterProperties.searchProperties.searchKey}"/>
<jsp:include page="/admin/jsp/include/update-search-bar.jsp"/>


<jsp:include page="/admin/jsp/include/pagination-tab.jsp"/>

<div id="logins-table">
	<table>
		<tr>
			<th>
				<c:set scope="request" var="columnName" value="Código"></c:set>
				<c:set scope="request" var="property" value="id"></c:set>
				<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
			</th>
			<th>
				<c:set scope="request" var="columnName" value="Usuário"></c:set>
				<c:set scope="request" var="property" value="login"></c:set>
				<jsp:include page="/admin/jsp/include/filter-option-order.jsp"/>
			</th>
			<th>
				<c:set scope="request" var="columnName" value="Local"/>
				<c:set scope="request" var="propertyOrder" value="locale"/>
				<c:set scope="request" var="propertyList" value="${locales}"/>
				<c:set scope="request" var="property" value="locale"></c:set>
				<c:set scope="request" var="propertyName" value="locale"/>
				<c:set scope="request" var="tagName" value="locale"/>
				<c:set scope="request" var="propertyIdList" value="${loginFilterProperties.localeIds}"/>
				<c:set scope="request" var="checkBoxClass" value="locale-checkboxes"/>
				
				<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
			</th>
			<th>
				<c:set scope="request" var="columnName" value="Permissão"/>
				<c:set scope="request" var="propertyOrder" value="permission"/>
				<c:set scope="request" var="propertyList" value="${permissions}"/>
				<c:set scope="request" var="propertyName" value="permission"/>
				<c:set scope="request" var="property" value="permission"/>
				<c:set scope="request" var="tagName" value="permission"/>
				<c:set scope="request" var="propertyIdList" value="${loginFilterProperties.permissionIds}"/>
				<c:set scope="request" var="checkBoxClass" value="permission-checkboxes"/>
				
				<jsp:include page="/admin/jsp/include/filter-option-filters.jsp"/>
			</th>
			
			<th class="col-20">Re-adicionar pedidos ao carrinho</th> 
		</tr>	
			<c:forEach items="${logins}" var="login">
				<tr>
					<td class="col-15">
						<c:out value="${login.id }"/>
					</td>
					<td class="col-25">
						<c:out value="${login.user }"/>
					</td>
					<td class="col-25" >
						<c:out value="${login.locale.description }"/>
					</td>
					<td class="col-25">
						<c:out value="${login.permission.description }"/>
					</td>
					<td>
						<c:url var="loginChangePermission" value="/admin/login_change_permission.action">
							<c:param name="userId" value="${login.id }"></c:param>
						</c:url>
						<input onchange="changeReadjustToCartChkbox(this, '${loginChangePermission }')" type="checkbox"
							<c:if test="${login.readjustToCart eq true }"> checked= 'checked'</c:if>
						>
					</td>
					<td class="col-25">
						<c:url value="/admin/delete_login.action" var="deleteLogin">
							<c:param name="loginId" value="${login.id }"/>
						</c:url>
						<a href="#" onclick="deleteLogin('${deleteLogin}')"  class="delete-login">
							<img src="/cde_foro11/img/icons/delete_16.png">
						</a>
					</td>
				</tr>
			</c:forEach>
	</table>
</div>