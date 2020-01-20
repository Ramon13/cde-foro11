$(document).ready(function (){
	
	ajaxRequestCountCart( $('#countCartUrl').val() );
});

function ajaxRequestCountCart( url ){
	$.ajax({url: url, success: function(result){
		let span = document.getElementById("cartAmount");
		span.textContent = result;
	}}); 
}

function checkPendingOrders( url ){
	alert(url);
	$.ajax({url: url, success: function(result){
		if(result > 0){
			$("#pendingOrder").html(result);
		}
	}});
}

function changeItemAmount(element){
	if(element.value == "10+"){
		let input = document.createElement = 
			'<input id="itemAmount" type="text" name="' + element.name + '" value="10">';
		element.parentElement.innerHTML = input;
		$("#itemAmount").focus();
	}
}

