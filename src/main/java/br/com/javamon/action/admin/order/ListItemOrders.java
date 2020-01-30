package br.com.javamon.action.admin.order;

import java.util.List;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.OrderItemFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Order;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.service.OrderItemService;
import br.com.javamon.validation.RequestParameterValidation;

public class ListItemOrders extends AdminAction<OrderItemFilterProperties>{

	private OrderItemService orderItemSvc;
	
	public ListItemOrders() {
		super(OrderItemFilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		String itemId = getRequest().getParameter("itemId");
		String orderId = getRequest().getParameter("orderId");
		
		this.orderItemSvc = getServiceFactory().getService(OrderItemService.class);
		
		updateSearchListStats(getRequest(),	getSessionFilterProperties("orderItemFilterProperties"));
		
		FilterProperties filterProperties = updateFilterListStats(getRequest(), getSessionFilterProperties("orderItemFilterProperties"));
		
		if(RequestParameterValidation.validateLongParam(itemId, 8))
			listOrderItemByItem(Long.parseLong(itemId), filterProperties);
		else
			if(RequestParameterValidation.validateLongParam(orderId, 8))
				listOrderItemByOrder(Long.parseLong(orderId), filterProperties);
		
		
		updateFilterListStats(getRequest(), filterProperties);
	}
	
	private void listOrderItemByItem(Long itemId, FilterProperties filterProperties) throws Exception{
		Long countOrders = this.orderItemSvc.countOrderItensByItem(itemId, filterProperties);
		PaginationProperties paginationProperties = updatePaginationStats(getRequest(), countOrders);
		
		List<OrderItem> orderItens = orderItemSvc.listOrderItemByItem(itemId, filterProperties, paginationProperties);
		
		getRequest().setAttribute("orderItens", orderItens);
		getRequest().setAttribute("itemId", itemId);
		getRequest().setAttribute("numOfItens", countOrders);
		
		foward("/admin/jsp/ajax/item_orders.jsp");
	}
	
	private void listOrderItemByOrder(Long orderId, FilterProperties filterProperties) throws Exception{
		Long countOrders = this.orderItemSvc.countOrderItensByItem(orderId, filterProperties);
		PaginationProperties paginationProperties = updatePaginationStats(getRequest(), countOrders);
		
		List<OrderItem> orderItens = orderItemSvc.listOrderItemByOrder(orderId, filterProperties, paginationProperties);
		List<Order> orderByLocale = orderItemSvc.listOrdersByLocale(orderItens.get(0).getOrder().getLogin().getLocale());
		
		getRequest().setAttribute("ordersByLocale", orderByLocale);
		getRequest().setAttribute("orderItens", orderItens);
		getRequest().setAttribute("orderId", orderId);
		getRequest().setAttribute("numOfItens", countOrders);
		getRequest().setAttribute("itemOrder", getRequest().getParameter("itemOrder"));
		
		foward("/admin/jsp/ajax/item_orders.jsp");
	}
}
