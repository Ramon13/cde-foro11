$(document).ready(function (){
	$( "#menu" ).menu();
	
	$(document).on("click", "#menu-home", function(){
		ajaxListItens();
	});
});

function ajaxListItens(){
	url = listItensPath;
	var element = $("#content");
	ajaxCall(url, element);
}

function ajaxCall(url, element){
	$.ajax({url: url, success: function(result){
		$(element).html(result);
	}});
}