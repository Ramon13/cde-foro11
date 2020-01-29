$(document).ready(function (){
	$("#load-circle").hide();
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

function resetLogin(url){
	
	var password = $("#password").val();
	var confirmPassword = $("#confirmPassword").val();
	
	if ( (password === confirmPassword) == false){
		$.toast({
		    heading: 'Erro',
		    text: "As senhas devem ser iguais",
		    showHideTransition: 'fade',
		    icon: 'error'
		});
	
	}else{
		$("#load-circle").show();
		
		url += "?user=" + $("#user").val() + "&password=" +$("#password").val();
		console.log(url);
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
	
	
}
