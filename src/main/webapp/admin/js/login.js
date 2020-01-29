function addLocale(url, dialog){
	url += "?save=true&localeDescription=" + $("#localeDescription").val();
	$.ajax({url: url, success: function(data, textStatus, xhr){
		if(xhr.status == 200){
			showSuccessDialog("Sucesso", "Novo local criado.");
			dialog.dialog("close");
			$("#localeSlct").html(data);
		}else if (xhr.status == 230){
			showErrorDialog("Erro", data);
		}
	}});
}


function editLocale(url, dialog){
	var selectedVal = $('#localeSlct option:selected').val();
	
	url += "?save=true";
	url += "&newDescription=" + $("#localeDescription").val();
	url += "&localeId=" + selectedVal;
	console.log(url);
	$.ajax({url: url, success: function(data, textStatus, xhr){
		if(xhr.status == 200){
			showSuccessDialog("Sucesso", "Local editado com sucesso.");
			dialog.dialog("close");
			$("#localeSlct").html(data);
			$("#edit-locale-dialog-form").html(" ");
		}else if (xhr.status == 230){
			showErrorDialog("Erro", data);
		}
	}});
}

function deleteLocale(url, dialog){
	var selectedVal = $('#localeSlct option:selected').val();
	
	url += "?delete=true";
	url += "&localeId=" + selectedVal;
	
	console.log(url);
	$.ajax({url: url, success: function(data, textStatus, xhr){
		if(xhr.status == 200){
			showSuccessDialog("Sucesso", "Local deletado com sucesso.");
			dialog.dialog("close");
			$("#localeSlct").html(data);
		}else if (xhr.status == 230){
			showErrorDialog("Erro", data);
		}
	}});
}


function addLogin(){
    var form = $("#loginForm");
    var url = form.attr('action');
    console.log(url);
    
    $.ajax({
           type: "post",
           url: url,
           data: form.serialize(), // serializes the form's elements.
           success: function(data, textStatus, xhr)
           {
	    	   if(xhr.status == 200){
	    		   showSuccessDialog("Sucesso", "Usuário cadastrado com sucesso.");
	    		   ajaxCall("/cde-foro11/admin/ListLogins.action?resetFilters=true", "content");
	    	   }
	       		else if (xhr.status == 230)
	       			showErrorDialog("Erro", data);
           }
         });
}

function changeLoginProperty(url){
	console.log(url);
	ajaxCall(url, undefined);
}

function resetPassword(url){
	$.ajax({url: url, success: function(data, textStatus, xhr){
		if(xhr.status == 200){
			
			$("#temp-pass-dialog").html(data);
			tempPassDialog.dialog( "open" );
		}else if (xhr.status == 230){
			showErrorDialog("Erro", data);
		}
	}});
}