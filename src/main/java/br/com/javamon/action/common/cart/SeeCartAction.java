package br.com.javamon.action.common.cart;

import java.util.Iterator;

import br.com.javamon.action.Action;
import domain.Cart;
import entity.Login;
import entity.OrderItem;
import service.ItemService;

public class SeeCartAction extends Action {

	@Override
	protected void process() throws Exception {
//		Login userLogin = (Login) getSession().getAttribute("login");
//		Iterator<OrderItem> iterator = userLogin.getCart().getCartItens().iterator();
//		
//		for(OrderItem orderItem : iter) {
//			orderItem.setItem( serviceFactory.getService(ItemService.class).loadItem(orderItem.getItemId()) );
//		}
//		
		
		foward("/jsp/common/cart.jsp");
	}

}
