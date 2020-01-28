package br.com.javamon.action.common.order;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.service.OrderItemService;

public class SeeItemOrderAction extends Action{

	@Override
	public void process() throws Exception {
		String orderId = getRequest().getParameter("orderId");
		
		if(!StringUtils.isBlank(orderId)) {			
			List<OrderItem> orderItens = getServiceFactory().getService(OrderItemService.class).listOrderItens(orderId);
			
			getRequest().setAttribute("orderItens", orderItens);
			getRequest().setAttribute("orderId", orderId);
			foward("/common/jsp/order_item.jsp");
		}
	}

}
