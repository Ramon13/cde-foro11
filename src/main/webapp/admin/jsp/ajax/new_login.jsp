<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/admin/AddLogin.action" var="addLogin">
	<c:param name="save" value="true"/>
</c:url>

<div>
	<form id="loginForm" method="post" action="${addLogin}">
		<table id="addLoginTb">
			<tr>
				<th>Local</th>
			</tr>
			<tr>
				<td>
					<select id="localeSlct" name="localeId">
						<c:forEach items="${locales}" var="locale">
							<option value="${locale.id}">${locale.description}</option>
						</c:forEach>
					</select>
					<div id="addLocaleBtn"><span class="ui-icon ui-icon-plus"></span></div>
					<div id="editLocaleBtn"><span class="ui-icon ui-icon-pencil"></span></div>
					<div id="deleteLocaleBtn"><span class="ui-icon ui-icon-close"></span></div>
				</td>
			</tr>
			
			<tr>
				<th>Permissão</th>
			</tr>
			<tr>
				<td>
					<select name="permissionId">
						<c:forEach items="${permissions}" var="permission">
							<option value="${permission.id}">${permission.description}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>Usuário</th>
			</tr>
			<tr>
				<td>
					<input name="user" type="text" />
				</td>
			</tr>
			
			<tr>
				<th>Senha</th>
			</tr>
			<tr>
				<td>
					<input name="password" type="password" />
				</td>
			</tr>
			
			<tr>
				<th>Confirmar senha</th>
			</tr>
			<tr>
				<td>
					<input name="confirmPassword" type="password" />
				</td>
			</tr>
			
			<tr>
				<td>
					<button type="button" id="submitBtn" onclick="addLogin()">Criar</button>
				</td>
			</tr>
		</table>
	</form>
</div>


<div id="add-locale-dialog-form" title="Criar novo local">
</div>	

<div id="edit-locale-dialog-form" title="Editar local">
</div>	

<div id="delete-locale-dialog" title="Deletar Local">
 	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Esta ação irá deletar permanentemente este local e não poderá ser desfeita, deseja continuar?</p>
</div>


<c:url value="/admin/AddLocale.action" var="addLocale"/>
<c:url value="/admin/EditLocale.action" var="editLocale"/>
<c:url value="/admin/DeleteLocale.action" var="deleteLocale"/>

<script>
	var addLocaleDialog, editLocaleDialog, deleteLocaleDialog;
	
	$(document).ready(function(){
		//open provider add dialog
		$("#addLocaleBtn").on("click", function(){
			ajaxCall('${addLocale}', "add-locale-dialog-form");
			addLocaleDialog.dialog( "open" );

		});
		
		$("#editLocaleBtn").on("click", function(){
			var selectedVal = $('#localeSlct option:selected').val();
			var url = "${editLocale}" + "?edit=true&localeId=" + selectedVal;
			ajaxCall(url, "edit-locale-dialog-form");
			editLocaleDialog.dialog( "open" );
		});
		
		$("#deleteLocaleBtn").on("click", function(){
			deleteLocaleDialog.dialog( "open" );
		});
		
		addLocaleDialog = simpleFormDialog("add-locale-dialog-form", function(){ return addLocale('${addLocale}', addLocaleDialog);} );
		editLocaleDialog = simpleFormDialog("edit-locale-dialog-form", function(){ return editLocale('${editLocale}', editLocaleDialog);} );
		deleteLocaleDialog = simpleDialogConfirmation("delete-locale-dialog", function(){ return deleteLocale('${deleteLocale}', deleteLocaleDialog);} );
	});
</script>