package br.com.javamon.action.common.cart;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;
import br.com.javamon.service.CartService;

public class SeeCartAction extends Action {

	@Override
	public void process() throws Exception {
//		Login userLogin = (Login) getSession().getAttribute("login");
//		Iterator<OrderItem> iterator = userLogin.getCart().getCartItens().iterator();
//		
//		for(OrderItem orderItem : iter) {
//			orderItem.setItem( serviceFactory.getService(ItemService.class).loadItem(orderItem.getItemId()) );
//		}
//		
		Login userLogin = (Login) getRequest().getSession().getAttribute("login");
		Cart userCart = getServiceFactory().getService(CartService.class).load(userLogin.getCart().getId());
		getRequest().setAttribute("cart", userCart);
		foward("/common/jsp/cart.jsp");
	}

}
