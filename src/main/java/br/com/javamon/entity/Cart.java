package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class Cart {

	private Long id;
	private Set<OrderItem> cartItens = new HashSet<OrderItem>();
	private Login login;
	
	public Set<OrderItem> getCartItens() {
		return cartItens;
	}

	public void setCartItens(Set<OrderItem> cartItens) {
		this.cartItens = cartItens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
}
