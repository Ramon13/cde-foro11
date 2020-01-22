package br.com.javamon.action.common.cart;

import javax.servlet.http.HttpSession;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.Login;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.service.CartService;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.OrderItemService;

public class AddCartAction extends Action {

	@Override
	public void process() throws Exception {
		String strItemId = getRequest().getParameter("itemId");
		String strItemAmount = getRequest().getParameter("itemAmount");
		
		addCart(getRequest().getSession(), strItemId, strItemAmount);
	}

	protected void addCart(HttpSession session, String strItemId, String strItemAmount) throws ServiceException{
		if(strItemId != null && strItemAmount != null) {
			long itemId = Long.parseLong(strItemId);
			long itemAmount = Long.parseLong(strItemAmount);
			Login userLogin = (Login) session.getAttribute("login");
			Cart userCart = getServiceFactory().getService(CartService.class).load(userLogin.getCart().getId());
			
			boolean newItem = true;
			for(OrderItem orderItem : userCart.getCartItens()) {
				if(orderItem.getItem().getId() == itemId) {
					Long amount = orderItem.getAmount();
					amount += itemAmount;
					orderItem.setAmount(amount);
					newItem = false;
				}
			}
			
			if(newItem) {
				Item item = getServiceFactory().getService(ItemService.class).load(itemId);
				OrderItem orderItem = new OrderItem();
				
				orderItem.setItem(item);
				orderItem.setAmount(itemAmount);
				getServiceFactory().getService(OrderItemService.class).save(orderItem);
				
				userCart.getCartItens().add(orderItem);
			}
			
			getServiceFactory().getService(CartService.class).update(userCart);
		}
	}
}
