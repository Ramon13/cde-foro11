package br.com.javamon.dao;

import java.io.Serializable;

import br.com.javamon.entity.Cart;
import br.com.javamon.exception.DAOException;

public class CartDAO extends DAO<Cart>{

	protected CartDAO() {
		super(Cart.class);
	}

	public Cart update(Cart cart) throws DAOException{
		getSession().update(cart);
		return null;
	}
	
	public Cart saveAndReturn(Cart cart) throws DAOException{
		Serializable id = getSession().save(cart);
		return load(id);
	}
}
