<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>

<c:url var="paginationPath" value="${action}">
	<c:param name="itemId" value="${itemId}"/>
	<c:param name="orderType" value="${orderType}"/>
</c:url>

 <div id="table-header-div">			
	<table>	
 	<tr>
 		<td>
 			<span>
 				<c:out value="${paginationProperties.firstValue + 1}"/>
 				-
 				<c:out value="${paginationProperties.lastValue}"/>
 				de	
 				<c:out value="${numOfItens}"/>
 			</span>
 		</td>
 		<td>
 			<button onclick="callPreviousPage('${paginationPath}', '${divTabId}')">
 				<span>&#60</span>
 			</button>
 			
 			<button onclick="callNextPage('${paginationPath}', '${divTabId}')">
 				<span>&#62</span>
 			</button>
 		</td>
 	</tr>
 </table>
 
</div>