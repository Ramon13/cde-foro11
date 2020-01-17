<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="number" uri="/WEB-INF/number-tag.tld"%>

<c:url value="/admin/AddProvider.action" var="addProviderPage"/>
<c:url value="/admin/AddProvider.action" var="saveProvider">
	<c:param name="save" value="true"/>
</c:url>
<c:url value="/admin/EditProvider.action" var="editProvider">
	<c:param name="save" value="true"/>
</c:url>
<c:url value="/admin/DeleteProvider.action" var="deleteProvider"/>

<c:url value="/admin/AddEntries.action" var="addEntries"/>
<form id="entriesForm" action="${addEntries }">
	<div>
		<h2>Adicionar Entradas</h2>
		
		<table id="addEntryTable">
			<tr>
				<td>
					<label for="date"> Data</label>
				</td>
				
				<td>
					<label for="documentNumber"> Nº do documento</label>
				</td>
				
				<td>
					<label for="providerCol"> Fornecedor</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="date" name="date" value="${currentDate}"/>
				</td>
				<td>
					<input type="text" name="documentNumber" value="${requestScope.entry.document.number }">	
				</td>
				<td>
					<div id="block-container">
						<div id="providerList" class="options" >
							<select class="js-example-basic-single" id="providerSlct" name="providerId" >

								<option value="0">Selecione</option>
								<c:forEach var="provider" items="${providers}">
									<c:url value="/admin/EditProvider.action" var="editProviderPage">
										<c:param name="providerId" value="${provider.id }"/>
									</c:url>	
									<option value="${provider.id }" label="${editProviderPage}"> ${provider.description }</option>
								</c:forEach>
							</select>
						</div>
						<div id="addProviderBtn"><span class="ui-icon ui-icon-plus"></span></div>
						<div id="editProviderBtn"><span class="ui-icon ui-icon-pencil"></span></div>
						<div id="deleteProviderBtn"><span class="ui-icon ui-icon-close"></span></div>
					</div>
				</td>
			</tr>
		</table>
		
		<div id="addEntriesDiv">
			<table id="addEntriesTable" hidden="hidden">
				
				<tr>
					<td>
						<label for="item"> Item:</label>
					</td>
					<td>
						<label for="amount"> Quantidade</label>
					</td>
					<td>
						<label for="untValue"> Valor Unitário</label>
					</td>
					<td>
						<label for="total"> Total (R$)</label>
					</td>
				</tr>
				
				
				
			</table>
			
			<input id="totalTable" hidden="hidden" disabled="disabled" type="text" name="total"/>
			
		</div>
		
		<div id="addEntryOptions">
			<c:url value="/admin/batch_entries.action" var="batchEntries">
				<c:param name="async">true</c:param>
			</c:url>
			<input type="hidden" name="save" value="true"/>
			<button type="button" class="new-entry-btn" onclick="addNewItemRow()">Adicionar Item</button>
			
		    <button id="submitEntry" type="button" onclick="addEntries()">Finalizar</button>
		</div>	
	</div>
	
</form>

<table>
	<tr class="trEntryMain" hidden="hidden">
		<td>
			<div class="options">
				<select class="selectMain" name="itemId" >
					<option value="0">Selecione</option>
					
					<c:forEach var="item" items="${items}">	
						<option value="${item.id }"> ${item.description }</option>
					</c:forEach>
				</select> 
			</div>
		</td>
		
		<td>
			<input class="amount" type="number" onkeyup="calcTotal(this)" name="amount"/>
		</td>
		
		<td>
			<input class="untValue" onkeyup="calcTotal(this)" type="number" name="unitaryValue"/>			
			</td>
			
			<td>
				<input class="total" disabled="disabled" type="text" name="total"/>
			</td>
	</tr>
</table>

<div id="add-provider-dialog-form" title="Criar novo fornecedor">
	  <p class="validateTips">Todos os campos são de preenchimento obrigatório.</p>
</div>	

<div id="edit-provider-dialog-form" title="Editar fornecedor">
	
</div>	

<div id="delete-provider-dialog" title="Deletar fornecedor">
 	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Esta ação irá deletar permanentemente este fornecedor e não poderá ser desfeita, deseja continuar?</p>
</div>


<script>
var addProviderDialog, editProviderDialog, deleteProviderDialog;
	$(document).ready(function(){
		$('.js-example-basic-single').select2();
		
		//open provider add dialog
		$("#addProviderBtn").on("click", function(){
			addProviderDialog.dialog( "open" );
			ajaxCall('${addProviderPage}', "add-provider-dialog-form");
		});

		//open provider edit dialog
		$("#editProviderBtn").on("click", function(){
			var selectedVal = $('#providerList option:selected').val();
			if (selectedVal == 0){
				showInfoToast("Fornecedor inválido", "Selecione um fornecedor válido");
				return;
			}
			
			var url = $('#providerList option:selected').attr('label');
			editProviderDialog.dialog( "open" );
			ajaxCall( url, "edit-provider-dialog-form");
		});
		
		$("#deleteProviderBtn").on("click", function(){
			var selectedVal = $('#providerList option:selected').val();
			if (selectedVal == 0){
				showInfoToast("Fornecedor inválido", "Selecione um fornecedor válido");
				return;
			}
			
			deleteProviderDialog.dialog( "open" );
		});
		
		
		//provider add dialog
		addProviderDialog = simpleFormDialog("add-provider-dialog-form", function(){ return addProvider('${saveProvider}', addProviderDialog);} );
		editProviderDialog = simpleFormDialog("edit-provider-dialog-form", function(){ return editProvider('${editProvider}', editProviderDialog);} );
		deleteProviderDialog = simpleDialogConfirmation("delete-provider-dialog", function(){ return deleteProvider('${deleteProvider}', deleteProviderDialog);} );
		
	});
</script>