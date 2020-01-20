package br.com.javamon.action.common.cart;

import java.io.PrintWriter;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;

public class CountCartAmount extends Action {

	@Override
	public void process() throws Exception {
		Login userLogin = (Login) getRequest().getSession().getAttribute("login");
		Cart userCart = userLogin.getCart();		
		try(PrintWriter pw = new PrintWriter(getResponse().getOutputStream())){
			pw.println(userCart.getCartItens().size());
		}
	}

}
