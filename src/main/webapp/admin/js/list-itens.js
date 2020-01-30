
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


function exportTableToExcel(filename = 'stock-resume'){
	console.log("excel");
	$("#itens-table").find(".dropdown").remove();
	
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById("itens-table");
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
    
    location.reload();
}