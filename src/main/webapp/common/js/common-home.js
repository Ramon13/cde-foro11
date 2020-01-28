

function addAmount( element ){
	var cartAmount = element.previousElementSibling;
	var amount = cartAmount.textContent;
	amount++;
	
	element.previousElementSibling.textContent = amount;
}

function subtractAmount( element ){
	var cartAmount = element.nextElementSibling;
	var amount = cartAmount.textContent;
	
	if(amount > 0){
		amount--;
	}
	
	element.nextElementSibling.textContent = amount;
}

function addCart( url, element ){

	let parentNode = element.parentNode.parentNode;
	
	let amountLbl = parentNode.querySelector("#td-amountLbl").querySelector("#amountLbl");

	let itemAmount = amountLbl.value;
	
	url += "&itemAmount=" + itemAmount;
	alert(url);
	$.ajax({url: url, success: function(result){
		alert("Item adicionado ao carrinho.");
		
		$.ajax({url: $('#countCartUrl').val(), success: function(result){
			let span = document.getElementById("cartAmount");
			span.textContent = result;
		}});
	}});
}

function addCartById( url){
	$.ajax({url: url, success: function(){
		alert("Itens readicionados ao carrinho com sucesso!")
		location.reload();
	}});
}

function loadTable( url ){
	$.ajax({url: url, success: function(result){
	    $("#itens-list").html(result);
	}});
}

function loadItensBySubitem( url ){
	$.ajax({url: url, success: function(result){
	    $("#itens-list").html(result);
	}});
}

function loadItensByType( url ){
	$.ajax({url: url, success: function(result){
	    $("#itens-list").html(result);
	}});
}

function loadItensByPage(url){
	$.ajax({url: url, success: function(result){
	    $("#itens-list").html(result);
	}});
}

function addCartAmount(element){
	let parentNode = element.parentNode.parentNode;
	
	let amountLblNode = parentNode.querySelector("#td-amountLbl");
	amountLblNode = amountLblNode.querySelector("#amountLbl");
	
	let currAmount = amountLblNode.value;
	
	amountLblNode.value = ++currAmount;
}

function minCartAmount(element){
	let parentNode = element.parentNode.parentNode.parentNode;

	let amountLblNode = parentNode.querySelector("#tr-amountLbl");
	amountLblNode = amountLblNode.querySelector("#td-amountLbl");
	amountLblNode = amountLblNode.querySelector("#amountLbl");
	
	let currAmount = amountLblNode.value;
	
	if(currAmount > 1){
		amountLblNode.value = --currAmount;
	}
}

function searchItens( url, searchInput ){
	
	url += "&itemSearch=" + searchInput.value;
	$.ajax({url: url, success: function(result){
	    $("#itens-list").html(result);
	}});
}

function loadOrderTable(url){
	$.ajax({url: url, success: function(result){
	    $("#main").html(result);
	}});
}

function loadOrdersByPage(url){
	$.ajax({url: url, success: function(result){
	    $("#main").html(result);
	}});
}

function changeImage(url){
	$("#main-img").attr('src', url);
}	

