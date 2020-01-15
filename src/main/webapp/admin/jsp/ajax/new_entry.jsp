<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="number" uri="/WEB-INF/number-tag.tld"%>

<script>
	$(document).ready(function() {
	    $('.js-example-basic-single').select2();
	});
</script>

<form>
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
									<option value="${provider.id }"> ${provider.description }</option>
								</c:forEach>
							</select> 
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		<div id="addEntriesDiv">
			<table id="addEntriesTable" hidden="hidden">
				<thead>
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
				</thead>
				
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
						<input class="amount" type="number" onkeyup="calcTotal(this)" autocomplete="off"  name="amount" 
							value="<number:format country="BR" 
								language="pt">${requestScope.entry.amount}</number:format>" />
					</td>
					
					<td>
						<input class="untValue" onkeyup="calcTotal(this)" type="number" autocomplete="off" name="unitaryValue" 
							value="<number:format country="BR" 
								language="pt"> ${requestScope.entry.unityValue }</number:format>" />			
					</td>
					
					<td>
						<input class="total" disabled="disabled" type="text" name="total" autocomplete="off"
							value="<number:format country="BR" 
								language="pt">${requestScope.entry.total }</number:format>" />
					</td>
				</tr>
				 
			</table>
					
		</div>
		
		<div id="addEntryOptions">
			<c:url value="/admin/batch_entries.action" var="batchEntries">
				<c:param name="async">true</c:param>
			</c:url>
			<input type="hidden" name="save" value="true"/>
			<button type="button" class="new-entry-btn" onclick="addNewItemRow()">Adicionar Item</button>
		    <input id="submitEntry" type="submit" value="Finalizar">
		</div>	
	</div>
	
</form>


 <c:url value="/admin/add_provider.action" var="addProvider"/>
 <c:url value="/admin/edit_provider.action" var="editProvider"/>
 <c:url value="/admin/delete_provider.action" var="deleteProvider"/>
 
	
	<input type="hidden" value="${addProvider }" id="addProviderURL">
	<input type="hidden" value="${editProvider }" id="editProviderURL">
	<input type="hidden" value="${deleteProvider }" id="deleteProviderURL">


<div id="dialog-form" title="Criar novo fornecedor">
	  <p class="validateTips">Todos os campos são de preenchimento obrigatório.</p>
</div>	

<div id="edit-provider-dialog" title="Editar fornecedor">
	 <div id="dialog-confirm" title="Deletar fornecedor">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Esta ação irá deletar permanentemente este fornecedor e não poderá ser desfeita, deseja continuar?</p>
</div>
</div>	


