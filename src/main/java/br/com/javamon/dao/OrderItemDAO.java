package br.com.javamon.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.DAOException;

public class OrderItemDAO extends DAOUtil<OrderItem> {

	protected OrderItemDAO() {
		super(OrderItem.class);
	}

	public List<OrderItem> listOrderItemByItem(Long itemId, 
			FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from OrderItem oi where oi.item.id = :itemId and oi.order.status = :status ");
		
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()))
			hql.append(" and ");
		addSearchFilter(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<OrderItem> query = createQuery(hql.toString(), OrderItem.class);
		query.setParameter("itemId", itemId);
		query.setParameter("status", 'F');
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public List<OrderItem> listOrderItemByOrder(Long orderId, 
			FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from OrderItem oi where oi.order.id = :orderId ");
		
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()))
			hql.append(" and ");
		addSearchFilter(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<OrderItem> query = createQuery(hql.toString(), OrderItem.class);
		query.setParameter("orderId", orderId);
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public Long countNumOrderItemByItem(Long itemId, FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from OrderItem oi where oi.item.id = :itemId and oi.order.status = :status ");
			
			if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()))
				hql.append(" and ");
				addSearchFilter(hql, filterProperties);
			System.out.println("count " + hql.toString());
			
			Query<Long> query = createQuery(hql.toString(), Long.class);
			query.setParameter("itemId", itemId);
			query.setParameter("status", 'F');
			
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public Long countNumOrderItemByOrder(Long orderId, FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from OrderItem oi where oi.order.id = :orderId ");
			
			if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()))
				hql.append(" and ");
				addSearchFilter(hql, filterProperties);
			System.out.println("count " + hql.toString());
			
			Query<Long> query = createQuery(hql.toString(), Long.class);
			query.setParameter("orderId", orderId);
			
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}
