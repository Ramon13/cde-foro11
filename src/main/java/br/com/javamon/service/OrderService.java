package br.com.javamon.service;

import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.OrderFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.dao.OrderDAO;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.Order;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class OrderService extends Service{

	public static enum ORDER_TYPE{
		PENDING,
		RELEASED,
		FINALIZED,
		CANCELED_BY_ADMIN,
		CANCELED_BY_USER
	}
	
	public List<Order> listOrders(PaginationProperties paginationProps, FilterProperties filterProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderDAO.class).listOrders(paginationProps, filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countNumOfOrders(PaginationProperties paginationProps, OrderFilterProperties ofp)throws ServiceException{
		return countOrders(ofp);
	}
	
	public Long countOrders(FilterProperties filterProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderDAO.class).countOrders(filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<OrderItem> listOrdersByLocale(Locale locale, Item item) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderDAO.class).listOrdersByLocale(locale, item);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Integer> distinctOrderYears() throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderDAO.class).distinctOrdersYear();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
