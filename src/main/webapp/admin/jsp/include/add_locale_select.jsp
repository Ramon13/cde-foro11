<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="number" uri="/WEB-INF/number-tag.tld"%>

<c:forEach items="${locales}" var="locale">
	<option value="${locale.id}">${locale.description}</option>
</c:forEach>