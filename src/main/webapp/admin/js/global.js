 $(document).ready(function(){
	
	$('.js-example-basic-single').select2();
	$( "#tabs" ).tabs();
});

function callNextPage(url, containerId){
	url += "&pageAction=next";
	ajaxCall(url, containerId);
}
	
function callPreviousPage(url, containerId){
	url += "&pageAction=previous";
	ajaxCall(url, containerId);
}

function ajaxListItens(url){
	var elementId = "content";
	ajaxCall(url, elementId);
}

function ajaxListOrders(url){
	var element = "content";
	ajaxCall(url, element);
}

function ajaxCall(url, elementId){
	$.ajax({url: url, success: function(data, textStatus, xhr){
			if(xhr.status == 230){
				$.toast({
				    heading: 'Erro',
				    text: 'Erro.' + data + '.',
				    showHideTransition: 'fade',
				    icon: 'error'
				})
			}else{
				$("#" + elementId).html(data);
			}
		}});
}

function ajaxCallAppend(url, elementId){
	$.ajax({url: url, success: function(data, textStatus, xhr){
			if(xhr.status == 230){
				$.toast({
				    heading: 'Erro',
				    text: 'Erro.' + data + '.',
				    showHideTransition: 'fade',
				    icon: 'error'
				})
			}else{
				$("#" + elementId).append(data);
			}
		}});
}

function filterList(url, elementClass, tagName){
	let elementCheckArr = $(elementClass);
	for(let i = 0 ; i < elementCheckArr.length ; i++){
		if(elementCheckArr[i].checked == true){
			url += "&" + tagName + "=" + elementCheckArr[i].value;
		}
	}
	alert(url);
	ajaxListItens(url);
}


function changeAllChkBxs(element, chkBoxsDivClass){
	let usrChkBx = element;
	if(usrChkBx.checked == false){
		unselectFilterChkBx(chkBoxsDivClass);
	}else{
		selectFilterChkBx(chkBoxsDivClass);
	}
}

function unselectFilterChkBx(elementClass){
	let userCheckArr = $(elementClass);
	for(let i = 0 ; i < userCheckArr.length ; i++){
		userCheckArr[i].checked = false;
	}
}

function selectFilterChkBx(elementClass){
	let userCheckArr = $(elementClass);
	for(let i = 0 ; i < userCheckArr.length ; i++){
		userCheckArr[i].checked = true;
	}
}

var typingTimer;
var doneTypingInterval = 1000;

function searchItens(url, divTabId, entityName, entityId){
	clearTimeout(typingTimer);
	
	if($("#searchInput").val()){
		typingTimer = setTimeout(function(){
			if(entityName === undefined){
				entityName = "";
			}else
				if(entityId === undefined){
					entityId = "";
				}
			doneTyping(url, divTabId, entityName, entityId);
		}, doneTypingInterval);
	}
}

var searchType;
var searchInput;
function doneTyping(url, divTabId, entityName, entityId){
	searchType = $("#searchType").val();
	searchInput = $("#searchInput").val();
	
	url += "?itemSearch=" + searchInput + "&searchType=" + searchType + "&" + entityName + "=" + entityId;
	alert(url + " " + divTabId);
	ajaxCall(url, divTabId);
}

function orderProperty(url, order, containerId){
	url += "&order=" + order;
	alert(url);
	ajaxCall(url, containerId);
}


function updateSearchTypeList(newTypeList, currentSearchType, currentSearchKey){
	$("#searchType").html(newTypeList);
	$("#searchInput").val(currentSearchKey);
	
	var values = $("#searchType>option").map(function() { return $(this) }).get();
	for(var i = 0 ; i < values.length ; i++){
		var v = values[i].val();
		if(values[i].val().trim().toUpperCase() === currentSearchType){
			$("#searchType").val( v );
		}
	}
}

function showSuccessDialog(heading, text){
	$.toast({
	    heading: heading,
	    text: text,
	    allowToastClose: true,
	    icon: 'success'
	})
}

function showErrorDialog(heading, text){
	$.toast({
	    heading: heading,
	    text: text,
	    showHideTransition: 'fade',
	    icon: 'error'
	})
}

function simpleFormDialog(dialogFormId, confirmFunction){
	var dialog = $( "#" + dialogFormId ).dialog({
		autoOpen: false,
		height: 400,
		width: 350,
		modal: true,
		buttons: {
		  "Salvar": confirmFunction,
		  
		  "Cancelar": function() {
		    dialog.dialog( "close" );
		  }
		},
		close: function() {
		 $("#dialog-error-msg").html(" ");
		}
	 });
	return dialog;
}