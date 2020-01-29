
function changeFilterDate(url, type, element, e, divTabId){
	if(e.keyCode == 13){
		url += "?" + type + "=" + element.value;
		ajaxCall(url, divTabId);
	}
}

function callItemInfo(url, itemId){
	url += "?itemId=" + itemId + "&resetFilters=true";
	ajaxListItens(url);
}

function showGraphs(url, itemId){
	
	var iconTriangle = $("#itemRowTriangle" + itemId);
	var tableRow = $("#tableRow" + itemId);
	var chartAppContainer = "chartAppContainer" + itemId;
	
	if(iconTriangle.attr('class') === "ui-icon ui-icon-triangle-1-e"){
		$("#itemRowTriangle" + itemId).attr("class", "ui-icon ui-icon-triangle-1-s");
		
		tableRow.html("<div id='" + chartAppContainer + "'></div>");
		
		if($("#chartContainer") != undefined){
			$("#chartContainer").attr("id", "chartContainerUnused");
			$("#chartContainer2").attr("id", "chartContainer2Unused");
		}
		
		ajaxCall(url, chartAppContainer);
		tableRow.parent().parent().show();
		return;
	}
	
	iconTriangle.attr("class", "ui-icon ui-icon-triangle-1-e");
	tableRow.html(" ");
	tableRow.parent().parent().hide();
}

function changeItensLayout(div, url, divTabId){
	$(".selected-list-type-img").attr("class", " ");
	
	var $this = $(div);
	
	url += "?listLayout=" + $this.attr("id");
	ajaxCall(url, divTabId)
}
