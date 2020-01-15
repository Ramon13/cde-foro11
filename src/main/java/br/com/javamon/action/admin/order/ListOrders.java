package br.com.javamon.action.admin.order;

import java.util.List;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.OrderFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Order;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.LoginService;
import br.com.javamon.service.OrderService;
import br.com.javamon.service.OrderService.ORDER_TYPE;
import br.com.javamon.validation.RequestParameterValidation;

public class ListOrders extends AdminAction<OrderFilterProperties>{

	public ListOrders() {
		super(OrderFilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		OrderService orderService = getServiceFactory().getService(OrderService.class);
		
		updateSearchListStats(getRequest(), getSessionFilterProperties("orderFilterProperties"));
		FilterProperties filterProperties = updateFilterListStats(getRequest(), getSessionFilterProperties("orderFilterProperties"));
		setOrderType(filterProperties);
		
		Long numOfOrders = orderService.countOrders(filterProperties);
		PaginationProperties paginationProps = updatePaginationStats(getRequest(), numOfOrders);
		
		List<Order> orders = orderService.listOrders(paginationProps, filterProperties);
	
		getRequest().setAttribute("orders", orders);
		getRequest().setAttribute("numOfItens", numOfOrders);
		getRequest().setAttribute("logins", getServiceFactory().getService(LoginService.class).list());
		getRequest().setAttribute("locales", getServiceFactory().getService(LocaleService.class).listLocales());
		
		foward("/admin/jsp/ajax/orders.jsp");
	}

	private void setOrderType(FilterProperties filterProperties) throws ValidatorException{
		String orderTypeParam = getRequest().getParameter("orderType");
		OrderFilterProperties ofp = (OrderFilterProperties) filterProperties;
		
		if(RequestParameterValidation.validateStringParam(orderTypeParam, 32)){
			for(ORDER_TYPE orderType : ORDER_TYPE.values()){
				if(orderTypeParam.equalsIgnoreCase(orderType.toString()) &&
						filterProperties instanceof OrderFilterProperties)
					ofp.setOrderType(orderType);
			}
		}
	}
}
