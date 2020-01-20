package br.com.javamon.action.common.cart;

import java.util.Iterator;

import br.com.javamon.action.Action;
import entity.Cart;
import entity.Login;
import entity.OrderItem;
import service.CartService;

public class DeleteCartItemAction extends Action{

	@Override
	protected void process() throws Exception {
		String strItemId = getRequest().getParameter("itemId");
		
		if(strItemId != null) {
			Long itemId = Long.parseLong(strItemId);
			
			Login login = (Login) getSession().getAttribute("login");
			Cart userCart = login.getCart();
			
			Iterator<OrderItem> userCartIterator = userCart.getCartItens().iterator();
			while(userCartIterator.hasNext()){
				OrderItem orderItem = userCartIterator.next();
				if(orderItem.getItem().getId().equals(itemId) ) {
					userCartIterator.remove();
					serviceFactory.getService(CartService.class).update(userCart);
				}
			}
		
		}
		
		redirect("see_cart.action");
	}

}
