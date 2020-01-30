package br.com.javamon.action.common.order;

import java.util.HashSet;
import java.util.Map;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.service.CartService;
import br.com.javamon.service.OrderService;

public class SaveOrderAction extends Action{

	@Override
	public void process() throws Exception {
		
		Login login = (Login) getRequest().getSession().getAttribute("login");
		Cart userCart = login.getCart();
		setUpdatedAmounts(userCart);
		getServiceFactory().getService(OrderService.class).saveNewOrder(login);
		clearCart(userCart);
		
		redirect("/common/list_item.action");
	}

	private void clearCart(Cart userCart) throws ServiceException{
		userCart.setCartItens(new HashSet<>());
		getServiceFactory().getService(CartService.class).update(userCart);
	}
	
	private void setUpdatedAmounts(Cart userCart) {
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		
		for(String key : parameterMap.keySet()) {
			String[] splitedKey = key.split(":");
			if(splitedKey[0].equals("itemAmount")) {
				for(OrderItem orderItem : userCart.getCartItens()) {
					if(orderItem.getItem().getId().equals(Long.parseLong(splitedKey[1]))) {
						orderItem.setAmount(Long.parseLong(getRequest().getParameter(key)));
					}
				}
			}
		
		}
	}
}
