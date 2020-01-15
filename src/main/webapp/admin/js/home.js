$(document).ready(function (){
	$( "#menu" ).menu();
});

function callMenu(url, divTabId){
	ajaxCall(url, divTabId);
}

function logoff(url){
	window.location.href = url;
}

function returnPage(url, divTabId){
	ajaxCall(url, divTabId);
}