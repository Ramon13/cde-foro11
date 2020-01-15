<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>

<c:url scope="request" var="searchPath" value="${action}" />

<script type="text/javascript">
	
	var html = "";
	<c:forEach items="${searchValues}" varStatus="loop" var="searchValue">
		html += "<option value='${searchValue}'>${searchData[loop.index]}</option>";
	</c:forEach>

	updateSearchTypeList(html, '${searchType}', '${searchKey}');
	
	$("#searchInput").on("keyup", function(){
		searchItens('${searchPath}', '${divTabId}', '${entitySearchName}', '${entitySearchId}');
	});
</script>