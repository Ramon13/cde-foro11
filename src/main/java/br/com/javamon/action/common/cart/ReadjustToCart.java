package br.com.javamon.action.common.cart;

import br.com.javamon.action.Action;

public class ReadjustToCart extends Action {

	@Override
	protected void process() throws Exception {
		AddCartAction addCartAction = new AddCartAction();
		
		String[] itemList = getRequest().getParameterValues("item");
		String[] amountList = getRequest().getParameterValues("itemAmount");
		
		for(int i = 0 ; i < itemList.length ; i++){
			addCartAction.addCart(getSession(), itemList[i], amountList[i]);
		}
		
		redirect("/cde_foro11/jsp/common/cart.jsp");
	}

}
