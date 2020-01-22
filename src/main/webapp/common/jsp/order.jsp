<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/common/see_order.action" var="seeItem">
	<c:param name="async" value="true"/>
</c:url>

<%@include file="header.jsp" %>		
	
	<body onload="loadOrderTable( '${seeItem}' )">
		
		<div id="content">
			
			<div id="main">
			
			</div>		
		
		</div>
		
	</body>
	
<%@include file="footer.jsp" %>