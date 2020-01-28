<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="number" uri="/WEB-INF/number-tag.tld"%>
<!DOCTYPE>
<select class="js-example-basic-single" id="providerSlct" name="providerId" >

<option value="0">Selecione</option>
	<c:forEach var="provider" items="${providers}">
		<c:url value="/admin/EditProvider.action" var="editProviderPage">
			<c:param name="providerId" value="${provider.id }"/>
		</c:url>	
		<option value="${provider.id }" label="${editProviderPage}"> ${provider.description }</option>
	</c:forEach>
</select>