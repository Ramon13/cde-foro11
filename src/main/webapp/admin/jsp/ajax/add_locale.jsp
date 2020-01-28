<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		
<div>
	<table  id="dialogTable">
		<tr>
			<td><p class="validateTips">Todos os campos são de preenchimento obrigatório.</p></td>
		</tr>
		
		<tr>
			<c:choose>
				<c:when test="${ not empty localeDescription}">
					<td>
						Editar Local
					</td>	
				</c:when>
				<c:otherwise>
					<td>
						Novo Local
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td>
				<input id="localeDescription" type="text" value="${localeDescription}"/>
			</td>
		</tr>
		
	</table>
</div>