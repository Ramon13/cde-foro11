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