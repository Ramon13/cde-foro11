$(document).ready(function (){
	$("#load-circle").hide();
	$(document).on("keypress",function (e){
		if(e.which == 13){
			login($("#loginActionURL").val());
		}
	});
});

function login(url){
	$("#load-circle").show();
	
	url += "?user=" + $("#userInput").val() + "&password=" +$("#passwordInput").val();
	$.ajax({url: url, success: function(data, textStatus, xhr){
		if(xhr.status == 230){
			$("#load-circle").hide();
			
			$.toast({
			    heading: 'Erro',
			    text: 'Erro.' + data + '.',
			    showHideTransition: 'fade',
			    icon: 'error'
			});
		}else{
			$(location).attr("href", url);
		}
	}});
}
