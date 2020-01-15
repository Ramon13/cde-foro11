function loadItemOrders(url, orderId){
	var iconTriangle = $("#itemRowTriangle" + orderId);
	var tableRow = $("#tableRow" + orderId);
	
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