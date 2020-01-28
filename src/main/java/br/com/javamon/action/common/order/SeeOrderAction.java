package br.com.javamon.action.common.order;

import br.com.javamon.action.Action;
import br.com.javamon.admin.domain.OrderPaginate;
import br.com.javamon.entity.Login;
import br.com.javamon.service.OrderService;

public class SeeOrderAction extends Action{

	@Override
	public void process() throws Exception {
		Login login = (Login) getRequest().getSession().getAttribute("login");
		String strAsync = getRequest().getParameter("async");
		String strPageNum = getRequest().getParameter("pageNum");
		
		if(login != null && strAsync != null) {
			OrderPaginate orderPaginate = getServiceFactory().getService(OrderService.class).listOrders(login, strPageNum);
			getRequest().setAttribute("orderList", orderPaginate.getOrderList());
			getRequest().setAttribute("numPages", orderPaginate.getNumPages());
			foward("/common/jsp/async/async-order.jsp");
		
		}else {
			redirect("/common/jsp/order.jsp");
		}
	}
}