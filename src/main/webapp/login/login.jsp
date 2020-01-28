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
		
		<c:url var="login" value="/auth/Login.action" scope="page"/>
		<input type="hidden" id="loginActionURL" value="${pageScope.login }">
		
		<table class="login_table">
		
			<tr><td>Usuário: </td></tr>
			<tr><td> <input id="userInput" type="text" name="user" maxlength="16"/> </td></tr>
			
			<tr><td>Senha: </td></tr>
			<tr><td> <input id="passwordInput" type="password" name="password" maxlength="16"/> </td></tr>
			
			<tr>	
				<td> <button id="loginBtn" type="button" onclick="login()"> Entrar </button> </td>
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
</html>

