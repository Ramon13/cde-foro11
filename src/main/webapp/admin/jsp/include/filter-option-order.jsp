<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>

<c:url var="filterPath" value="${action}">
	<c:param name="property" value="${property}"/>
	<c:param name="itemId" value="${itemId}"/>
	<c:param name="${entityName}" value="${entityId}"/>
	<c:param name="orderType" value="${orderType}"/>
</c:url>
 <label>
 	${columnName}
 </label>
 <div class="dropdown">
	<img src="../img/png/down-arrow.png" class="dropbtn"/>
	<div class="dropdown-content">
		<span id="cod-order-asc" onclick="orderProperty('${filterPath}', 'asc', '${divTabId}')">
			Classificar em ordem crescente
		</span>
		<span id="cod-order-desc" onclick="orderProperty('${filterPath}', 'desc', '${divTabId}')">
				Classificar em ordem decrescente
		</span>
	</div>
</div>