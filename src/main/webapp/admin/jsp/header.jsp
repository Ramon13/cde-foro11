<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <c:url var="listItensURL" value="/admin/ListItens.action" />


<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 
		<link rel="stylesheet" href="../../css/jquery-ui.css">
	 	<link rel="stylesheet" href="../../css/jquery-ui.structure.css">
	  	<link rel="stylesheet" href="../../css/jquery-ui.theme.css">
	  	<link rel="stylesheet" href="../../css/jquery.toast.css">	
	  	<link rel="stylesheet" href="../../css/select2.css">  	
	  	
	  	<link rel="stylesheet" href="../css/home.css">
	  	<link rel="stylesheet" href="../css/list-itens.css">
	  	<link rel="stylesheet" href="../css/orders.css">
	  	<link rel="stylesheet" href="../css/global.css">
	  	<link rel="stylesheet" href="../css/header.css">
	  	<link rel="stylesheet" href="../css/item.css">
	  	<link rel="stylesheet" href="../css/login.css">	
	  	<link rel="stylesheet" href="../css/entries.css">	
	  	
		<title>Controle de Estoque - Foro da 11ª</title>
		
	</head>
	
	<header>
		
		<div id="top-menu">
		
			<div id="menu-title"><h1>Controle De Estoque - Foro Da 11ª</h1></div>
			<div id="menu-search">
				<input type="text" id="searchInput" placeholder="Pesquisar item" >
				<select name="searchType" id="searchType">			
				</select>
			</div>
			<div id="user-profile"> 
				<label>${login.user}</label>
			</div>
		</div>
	</header>
	