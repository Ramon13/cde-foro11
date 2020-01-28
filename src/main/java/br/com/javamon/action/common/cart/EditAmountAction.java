package br.com.javamon.action.common.cart;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;
import br.com.javamon.entity.OrderItem;

public class EditAmountAction extends Action{

	@Override
	public void process() throws Exception {
		String strItemId = getRequest().getParameter("itemId");
		String newAmount = getRequest().getParameter("itemAmount");
		
		if(newAmount != null && strItemId != null) {
			Long amount = Long.parseLong(newAmount);
			Long itemId = Long.parseLong(strItemId);
			
			Login login = (Login) getRequest().getSession().getAttribute("login");
			Cart userCart = login.getCart();
			
			for(OrderItem orderItem : userCart.getCartItens()) {
				if(orderItem.getItemId() == itemId) {
					orderItem.setAmount(amount);
					break;
				}
			}	
		}
		
	}

}
