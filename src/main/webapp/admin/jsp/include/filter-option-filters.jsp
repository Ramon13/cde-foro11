<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>

<c:url var="filterPath" value="${action}">
	<c:param name="property" value="${property}"/>
	<c:param name="itemId" value="${itemId}"/>
	<c:param name="${entityName}" value="${entityId}"/>
	<c:param name="orderType" value="${orderType}"/>
	<c:param name="tagName" value="${tagName}"/>
</c:url>

<label>${columnName}</label>

<div class="dropdown">
	<img src="../img/png/down-arrow.png" class="dropbtn"/>
	<div class="dropdown-content">
		<span onclick="orderProperty('${filterPath}', 'asc', '${divTabId}')">
			Classificar em ordem crescente
		</span>
		<span onclick="orderProperty('${filterPath}', 'desc', '${divTabId}')">
			Classificar em ordem decrescente
		</span>
		
		<br><br>
		
		<label>Filtros</label> <br>
		<div id="filter-menu" class="vertical-menu">
		
			<c:set scope="page" var="allOptions" value="true"/>
		    <c:forEach items="${propertyList}" var="property">	
		    	<a>
		    		<input type="checkbox" class="${checkBoxClass}" 
		    			name="${propertyName }" value="${property.id }"
		    				
		    				<c:set scope="page" var="optionChecked" value="false"/>
		    				<c:forEach items="${propertyIdList}" var="propertyId">
			    				<c:if test="${property.id eq propertyId}">
			    					checked='checked';
			    					<c:set scope="page" var="optionChecked" value="true"/>
			    				</c:if>
		    				</c:forEach>
		    				
		    				<c:if test="${optionChecked eq false }">
			    				<c:set scope="page" var="allOptions" value="false"/>
			    			</c:if>
		    			> ${property.description } <br>
		    	</a>
		    </c:forEach>
	    </div>
	    
	    <button class="filter-btn" onclick="filterList('${filterPath}', '.${checkBoxClass}', '${tagName}')">Filtrar</button>
	    <br>
	    Tudo <input onchange="changeAllChkBxs(this, '.${checkBoxClass}')" type="checkbox"
					<c:if test="${allOptions eq true }">
						checked='checked'
					</c:if>
				>
	</div>
</div>