package br.com.javamon.service;

import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.dao.OrderItemDAO;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class OrderItemService extends Service{

	public void save(OrderItem oi) throws ServiceException{
		try{
			getDaoFactory()
			.getDAO(OrderItemDAO.class)
			.save(oi);
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}
	
	public List<OrderItem> listOrderItemByItem(Long itemId, 
			FilterProperties filterProperties, 
			PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderItemDAO.class)
					.listOrderItemByItem(itemId, filterProperties, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<OrderItem> listOrderItemByOrder(Long orderId, 
			FilterProperties filterProperties, 
			PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderItemDAO.class)
					.listOrderItemByOrder(orderId, filterProperties, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countOrderItensByItem(Long itemId, FilterProperties filterProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderItemDAO.class).countNumOrderItemByItem(itemId, filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countOrderItensByOrder(Long orderId, FilterProperties filterProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(OrderItemDAO.class).countNumOrderItemByOrder(orderId, filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
