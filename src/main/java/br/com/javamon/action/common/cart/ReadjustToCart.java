package br.com.javamon.action.common.cart;

import br.com.javamon.action.Action;

public class ReadjustToCart extends Action {

	@Override
	public void process() throws Exception {
		AddCartAction addCartAction = new AddCartAction();
		
		String[] itemList = getRequest().getParameterValues("item");
		String[] amountList = getRequest().getParameterValues("itemAmount");
		
		for(int i = 0 ; i < itemList.length ; i++){
			addCartAction.addCart(getRequest().getSession(), itemList[i], amountList[i]);
		}
		
		redirect("/common/jsp/cart.jsp");
	}

}
