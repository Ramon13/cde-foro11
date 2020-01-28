package br.com.javamon.action.common.cart;

import java.util.Iterator;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.service.CartService;

public class DeleteCartItemAction extends Action{

	@Override
	public void process() throws Exception {
		String strItemId = getRequest().getParameter("itemId");
		
		if(strItemId != null) {
			Long itemId = Long.parseLong(strItemId);
			
			Login login = (Login) getRequest().getSession().getAttribute("login");
			Cart userCart = getServiceFactory().getService(CartService.class).load(login.getCart().getId());
			
			Iterator<OrderItem> userCartIterator = userCart.getCartItens().iterator();
			while(userCartIterator.hasNext()){
				OrderItem orderItem = userCartIterator.next();
				if(orderItem.getItem().getId().equals(itemId) ) {
					userCartIterator.remove();
					getServiceFactory().getService(CartService.class).update(userCart);
				}
			}
		
		}
		
		redirect("/common/see_cart.action");
	}

}
