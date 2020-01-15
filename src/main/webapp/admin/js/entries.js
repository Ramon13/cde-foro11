$(document).ready(function(){
	
});

function callEntryItem(url, container){
	var className = $('#' + container).attr('class');

	if(className === "show"){
		ajaxCall(url, container);
		$('#' + container).attr("class", "hide");
	}
	else{
		$('#' + container).html("");
		$('#' + container).attr("class", "show");
	}
}

function loadEntryItens(url, entryId){
	var iconTriangle = $("#itemRowTriangle" + entryId);
	var tableRow = $("#tableRow" + entryId);
	
	if(iconTriangle.attr('class') === "ui-icon ui-icon-triangle-1-e"){
		iconTriangle.attr("class", "ui-icon ui-icon-triangle-1-s");
		
		
		ajaxCall(url, tableRow.attr("id"));
		tableRow.parent().parent().show();
		return;
	}
	
	iconTriangle.attr("class", "ui-icon ui-icon-triangle-1-e");
	tableRow.html(" ");
	tableRow.parent().parent().hide();
}

function callNewEntry(url, divTabId){
	ajaxCall(url, divTabId);
}

function addNewItemRow(){
	$("#addEntriesTable").show();
	var $clonedRow = $(".trEntryMain").clone();
	$clonedRow.attr("class", "trEntry");
	$clonedRow.find(".selectMain").attr("class", "js-example-basic-single");
	
	$("#addEntriesTable tr:last").after($clonedRow);
	
	$clonedRow.show();
	$('.js-example-basic-single').select2();
}

function calcTotal(input){
	var $input = $(input);
	var $tableRow = $input.parent().parent();
	
	var amount =  $tableRow.find(".amount").val();
	var untValue =  $tableRow.find(".untValue").val();
	
	var sum = Number(amount) * Number(untValue);
	$tableRow.find(".total").val(sum);
}