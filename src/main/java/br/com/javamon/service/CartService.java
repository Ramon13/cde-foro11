package br.com.javamon.service;

import java.io.Serializable;

import br.com.javamon.dao.CartDAO;
import br.com.javamon.entity.Cart;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class CartService extends Service {

	public Cart load(Serializable id) throws ServiceException{
		try {
			return getDaoFactory().getDAO(CartDAO.class).load(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public Cart update(Cart cart) throws ServiceException{
		try {
			return getDaoFactory().getDAO(CartDAO.class).update(cart);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public Cart save(Cart cart) throws ServiceException{
		try {
			return getDaoFactory().getDAO(CartDAO.class).saveAndReturn(cart);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
