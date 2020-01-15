<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/login/login.jsp" var="t"></c:url>

<script type="text/javascript">
	window.location.href = "${t}";
</script>
 