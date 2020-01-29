<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>

	<div id="menu-bar">
		<ul id="menu">

			<c:set var="divTabId" value="content" />
			<li>
				<c:url var="listItensPath" value="/admin/ListItens.action">
					<c:param name="resetFilters" value="true" />
				</c:url>
				<div onclick="callMenu('${listItensPath}', '${divTabId}')">
					<span class="ui-icon ui-icon-home"></span>Início
				</div></li>
			<li class="ui-state-disabled">
				<div>
					<span class="ui-icon ui-icon-print"></span>Print...
				</div>
			</li>

			<li>
				<div>
					<img class="blueBallHome" src="/cde-foro11/admin/img/png/blue_ball.png" hidden="hidden">				
					Pedidos
				</div>
				<ul>
					<li>
						<c:url var="listOrdersPath"
							value="/admin/ListOrders.action">
							<c:param name="resetFilters" value="true" />
							<c:param name="orderType" value="pending" />
						</c:url>
						<div onclick="callMenu('${listOrdersPath}', '${divTabId}')">
							<img class="blueBallHome" src="/cde-foro11/admin/img/png/blue_ball.png" hidden="hidden">		
							Pendentes
						</div>
					</li>
					<li>
						<c:url var="listOrdersPath"
							value="/admin/ListOrders.action">
							<c:param name="resetFilters" value="true" />
							<c:param name="orderType" value="released" />
						</c:url>
						<div onclick="callMenu('${listOrdersPath}', '${divTabId}')">
							<span></span>Autorizados
						</div>
					</li>
					<li>
						<c:url var="listOrdersPath"
							value="/admin/ListOrders.action">
							<c:param name="resetFilters" value="true" />
							<c:param name="orderType" value="finalized" />
						</c:url>
						<div onclick="callMenu('${listOrdersPath}', '${divTabId}')">
							<span></span>Finalizados
						</div></li>
					<li>
						<c:url var="listOrdersPath"
							value="/admin/ListOrders.action">
							<c:param name="resetFilters" value="true" />
							<c:param name="orderType" value="canceled_by_admin" />
						</c:url>
						<div onclick="callMenu('${listOrdersPath}', '${divTabId}')">
							<span></span>Cancelados Pelo Administrador
						</div>
					</li>
					<li>
						<c:url var="listOrdersPath"
							value="/admin/ListOrders.action">
							<c:param name="resetFilters" value="true" />
							<c:param name="orderType" value="canceled_by_user" />
						</c:url>
						<div onclick="callMenu('${listOrdersPath}', '${divTabId}')">
							<span></span>Cancelados Pelo Usuário
						</div>
					</li>
				</ul>
			</li>
			<li>
				<c:url var="listLoginsPath" value="/admin/ListLogins.action">
					<c:param name="resetFilters" value="true" />
				</c:url>
				<div onclick="callMenu('${listLoginsPath}', '${divTabId}')">
					<span class="ui-icon ui-icon-person"></span>Usuários do sistema
				</div>
			</li>
			<li>
				<c:url var="listEntriesPath"
					value="/admin/ListEntries.action">
					<c:param name="resetFilters" value="true" />
				</c:url>
				<div onclick="callMenu('${listEntriesPath}', '${divTabId}')">
					<span class="ui-icon ui-icon-circle-arrow-s"></span>Entradas
				</div>
			</li>
			<li>
				<c:url var="logoffAction" value="/auth/Logoff.action" />
				<div onclick="logoff('${logoffAction}')">
					<span class="ui-icon ui-icon-circle-arrow-s"></span>Sair
				</div>
			</li>

		</ul>

	</div>

	<div id="pageOptions">
		<c:url value="/admin/ReturnPage.action" var="returnPage" />
		<a href="#" onclick="returnPage('${returnPage}', 'content')">
			<img id="backArrowPage" src="../img/png/back-arrow.png">
		</a>
	</div>
	<div id="content"></div>


<%@include file="footer.jsp"%>

<script>
	$(document).ready(function() {
		callMenu('${listItensPath}', '${divTabId}');
	});
</script>
