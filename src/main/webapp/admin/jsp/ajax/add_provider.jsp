<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		
<div>
	<form>
		<table id="dialogTable">
			<tr>
				<td>
					<c:choose>
						<c:when test="${empty requestScope.provider }">
							<label for="newUnityType">Novo Forneçedor:</label>
						</c:when>
						<c:otherwise>
							<label for="newUnityType">Editar Fornecedor:</label>
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="providerId" value="${provider.id }"/>
					<input id="newProvider" type="text" name="providerDesc"
						 value="${provider.description }">
				</td>
			</tr>
			<tr>
				<td>
					<label for="newCnpj">CNPJ</label>
				</td>
			</tr>
			<tr>
				<td>
					<input id="newCnpj" type="text" name="providerCnpj"
						 value="${provider.cnpj }">
				</td>
			</tr>
			<!--
			<tr>
			
				<td>
					  <input class="saveInput" type="submit" value="Salvar">	
				</td>
				<c:if test="${not empty requestScope.provider }">
					<c:url var="deleteProvider" value="/admin/delete_provider.action">
						<c:param name="providerId" value="${requestScope.provider.id }"/>
						<c:param name="itemId" value="${requestScope.itemId }"/>
					</c:url>
					<a href="${deleteProvider }" class="link-delete">Deletar este fornecedor</a>
				</c:if>
			</tr>
			-->
		</table>
	</form>
</div>