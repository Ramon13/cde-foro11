package br.com.javamon.service;

import java.io.Serializable;
import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.OrderFilterProperties;
import br.com.javamon.admin.domain.OrderPaginate;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.dao.OrderDAO;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.Login;
import br.com.javamon.entity.Order;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.ConvertException;
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
	
	public Order load(Serializable id) throws ServiceException{
		try {
			return getDaoFactory()
					.getDAO(OrderDAO.class)
					.load(id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
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
	
	@Deprecated
	public OrderPaginate listOrders(Login login, String strNumPage) throws ServiceException, ConvertException{
		OrderPaginate orderPaginate = new OrderPaginate();
		int rowStart = getRowStart(strNumPage);
		int numPages = numPages(getNumOrdersByUser(login));
		
		try {
			List<Order> orderList = getDaoFactory()
					.getDAO(OrderDAO.class)
					.paginateOrderByUser(login, rowStart, (int) MAX_ITENS);
			orderPaginate.setOrderList(orderList);
			orderPaginate.setNumPages(numPages);
			
			return orderPaginate;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
	}
	
	private final int MAX_ITENS = 50;
	@Deprecated
	private int getRowStart( String strNumPage) throws ConvertException{
		if(strNumPage == null )
			strNumPage = "1";
		
		Integer numPage = StringConvert.stringToInteger( strNumPage );
		int rowFinish = numPage * (int) MAX_ITENS;
		int rowStart = rowFinish - (int) MAX_ITENS;
		
		return rowStart;
	}
	
	@Deprecated
	public int numPages( Long numItens ) {
		return (int) Math.ceil(numItens / MAX_ITENS);
	}
	
	@Deprecated
	private Long getNumOrdersByUser(Login login) throws ServiceException{
		try {
			return getDaoFactory()
					.getDAO(OrderDAO.class)
					.countOrderByLogin(login);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public List<Order> listOrdersByLocale(Locale locale) throws ServiceException{
		try {
			return getDaoFactory()
					.getDAO(OrderDAO.class)
					.listOrderByLocale(locale);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public Order loadOrder(Long orderId) throws ServiceException{
		try {
			return getDaoFactory()
					.getDAO(OrderDAO.class)
					.load(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long getPendingOrdersAmount() throws ServiceException{
		return getOrderDAO().getPendingOrdersAmount();
	}
}
