$(document).ready(function(){
	
});

function callListEntries(url){
	url += "&resetFilters=true";
	ajaxCall(url, "tabs-2");
}

function callListItemOrders(url){
	ajaxCall(url, "tabs-3");
}

function loadItemOrders(url, orderItemId){
	var iconTriangle = $("#itemRowTriangle" + orderItemId);
	var tableRow = $("#tableRow" + orderItemId);
	
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