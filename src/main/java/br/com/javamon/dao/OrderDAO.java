package br.com.javamon.dao;

import java.util.List;

import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.OrderFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.Order;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.exception.DAOException;
import br.com.javamon.service.OrderService.ORDER_TYPE;

public class OrderDAO extends DAOUtil<Order>{

	protected OrderDAO() {
		super(Order.class);
	}
	
	public List<Order> list() throws DAOException{
		String hql = "from Order";
		return createQuery(hql, Order.class).list();
	}
	
	public List<OrderItem> listOrdersByLocale(Locale locale, Item item) throws DAOException{
		String hql = "from OrderItem oi where oi.order.login.locale = :locale and oi.item = :item and oi.order.status = :status";
		Query<OrderItem> query = createQuery(hql, OrderItem.class);
		query.setParameter("locale", locale);
		query.setParameter("item", item);
		query.setParameter("status", 'F');

		return query.list();
	}
	
	public List<OrderItem> listOrdersByItem(Item item) throws DAOException{
		String hql = "from OrderItem oi where oi.item = :item and oi.order.status = :status";
		Query<OrderItem> query = createQuery(hql, OrderItem.class);
		query.setParameter("item", item);
		query.setParameter("status", 'F');

		return query.list();
	}

	public List<Order> listOrders(PaginationProperties paginationProps, FilterProperties filterProperties) throws DAOException{
		StringBuilder hql = new StringBuilder();
		hql.append("from Order o where o.status = :status and ");
		
		boolean hasSearchKey = addSearchFilter(hql, filterProperties);
		if(hasSearchKey == true)
			hql.append(" and ");
	
		addPropertyFilters(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		Query<Order> query = createQuery(hql.toString(), Order.class);
		
		if(filterProperties instanceof OrderFilterProperties){
			OrderFilterProperties ofp = (OrderFilterProperties) filterProperties;
			setStatus(query, ofp.getOrderType());
		}
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		
		return query.list();
	}
	
	public Long countOrders(FilterProperties filterProperties) throws DAOException{
		StringBuilder hql = new StringBuilder("select count (*) from Order o where o.status = :status and ");
		
		boolean hasSearchKey = addSearchFilter(hql, filterProperties);
		if(hasSearchKey == true)
			hql.append(" and ");
		addPropertyFilters(hql, filterProperties);
		System.out.println(hql.toString());
		Query<Long> query = createQuery(hql.toString(), Long.class);
		if(filterProperties instanceof OrderFilterProperties){
			OrderFilterProperties ofp = (OrderFilterProperties) filterProperties;
			setStatus(query, ofp.getOrderType());
		}
		return query.uniqueResult();
	}
	
	private <T> void setStatus(Query<T> query, ORDER_TYPE orderType) throws DAOException{
		if(orderType == ORDER_TYPE.FINALIZED)
			query.setParameter("status", 'F');
		else if(orderType == ORDER_TYPE.PENDING)
			query.setParameter("status", 'P');
		else if(orderType == ORDER_TYPE.RELEASED)
			query.setParameter("status", 'R');
		else if(orderType == ORDER_TYPE.CANCELED_BY_ADMIN)
			query.setParameter("status", 'C');
		else if(orderType == ORDER_TYPE.CANCELED_BY_USER)
			query.setParameter("status", 'U');
	}	
}
