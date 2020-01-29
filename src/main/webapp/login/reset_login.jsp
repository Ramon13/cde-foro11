<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="login.css">
		<link rel="stylesheet" href="../css/jquery.toast.css">
		<title>Login</title>
	</head>

	<body>
		<div id="content">
		
		<c:url var="login" value="/auth/ResetLogin.action" scope="page"/>
		
		<table class="login_table">
			
			<tr><td>Usuário: </td></tr>
			<tr><td> <input id="user" type="text" name="user" maxlength="16"/> </td></tr>
			
			<tr><td>Nova Senha: </td></tr>
			<tr><td> <input id="password" type="password" name="password" maxlength="16"/> </td></tr>
			
			<tr><td>Confirmar Senha: </td></tr>
			<tr><td> <input id="confirmPassword" type=	"password" name="confirmPassword" maxlength="16"/> </td></tr>
			
			<tr>	
				<td> <button id="loginBtn" type="button" onclick="resetLogin('${login}')"> Entrar </button> </td>
			</tr>
			<tr>
				<td> <img id="load-circle" alt="" src="../img/load_circle.gif"> </td>
				 
			</tr>
		</table>
	
		</div>
	</body>	
	
	<script type="text/javascript" src="../js/jquery-3.3.1.js" ></script>
	<script type="text/javascript" src="login.js" ></script>
	<script type="text/javascript" src="../js/jquery.toast.js" ></script>
	
	<script>
		$(document).ready(function(){
			$(document).on("keypress",function (e){
				if(e.which == 13){
					resetLogin('${login}');
				}
			});
		});
	</script>
</html>

