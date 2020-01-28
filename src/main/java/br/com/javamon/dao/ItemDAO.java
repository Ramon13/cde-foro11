package br.com.javamon.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.FilterProperties.PROPERTIES;
import br.com.javamon.admin.domain.ItemFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.ItemType;
import br.com.javamon.exception.DAOException;
import br.com.javamon.util.SortProperty;

/**
 * 
 * @author ramon
 *
 */
public class ItemDAO extends DAOUtil<Item>{
	
	public ItemDAO() {
		super(Item.class);
	}

	public List<Item> listItem()throws DAOException{
		String hql = "from Item";
		return list(hql);
	}
	
	public List<Item> listItem(int firstResult, int maxResult) throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Item i ");
			setSQLQueryOrder(hql, new ItemFilterProperties());
			
			
			Query<Item> query = createQuery(hql.toString(), Item.class);
			setPaginationParameters(query, firstResult, maxResult);
			
			return query.list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<Item> listItem(PaginationProperties paginationProperties, FilterProperties filterProperties) throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Item i where ");
			
			boolean hasSearchKey = addSearchFilter(hql, filterProperties);
			if(hasSearchKey == true)
				hql.append(" and ");
			
			addPropertyFilters(hql, filterProperties);
			setSQLQueryOrder(hql, filterProperties);
			System.out.println(hql.toString());
			
			Query<Item> query = createQuery(hql.toString(), Item.class);
			setPaginationParameters(query, paginationProperties.getFirstValue(), paginationProperties.MAX_NUM_OF_ITENS);
			
			return query.list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public Long countNumItens(FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from Item i where ");
			
			boolean hasSearchKey = addSearchFilter(hql, filterProperties);
			if(hasSearchKey == true)
				hql.append(" and ");
			addPropertyFilters(hql, filterProperties);
			System.out.println("count " + hql.toString());
			Query<Long> query = createQuery(hql.toString(), Long.class);
			
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	protected boolean addSearchFilter(StringBuilder sb, FilterProperties filterProperties) throws DAOException {
		PROPERTIES property = filterProperties.getSearchProperties().getSearchType();
		boolean hasSearchKey = false;
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) ){
			sb.append(" ( ");
			String mapValue = filterProperties.getPropertiesOrderMap().get(property);
			sb.append( mapValue + " like " + "'%" + filterProperties.getSearchProperties().getSearchKey() + "%' ");
			sb.append(" ) ");
			
			if(property == PROPERTIES.DESCRIPTION){
				sb.append(" or ( ");
				sb.append(" specification like " + "'%" + filterProperties.getSearchProperties().getSearchKey() + "%' ");
				sb.append(" ) ");
			}
			hasSearchKey = true;
		}
		return hasSearchKey;
	} 
	
	@Deprecated
	public List<Item> filterItens(String pattern, int rowStart, int pageSize ) throws DAOException{
		String hql = "from Item i where (lower(i.description) like :pattern or lower(i.specification) like :pattern) and i.type.id != :typeId";
	
		Query<Item> query = createQuery(hql, Item.class);
		query.setParameter( "pattern", "%" + pattern + "%" );
		query.setParameter( "typeId", 1L );
		
		query.setFirstResult( rowStart );
		query.setMaxResults( pageSize ); 
		
		return query.list();
	}
	
	@Deprecated
	public Long countFilteredItens( String pattern ) throws DAOException{
		String hql = "select count (i.id) from Item i where lower(i.description) like :pattern ";
		Query<Long> query = createQuery(hql, Long.class);
		query.setParameter( "pattern", "%" + pattern + "%" );
		
		return (Long) query.uniqueResult();
	}
	
	@Deprecated
	public Long countItensBySubitemType(ItemType type) throws DAOException{
		String hql = "select count (i.id) from Item i where i.type =:type";
		Query<Long> query = createQuery(hql, Long.class);
		
		query.setParameter("type", type);
		
		return (Long) query.uniqueResult();
	}
	
	@Deprecated
	public List<Item> paginateItemByType(ItemType itemtype, int rowStart, int pageSize) throws DAOException{
		String hql = "from Item i where i.type = :itemType";
		Query<Item> query = createQuery( hql, Item.class );
		query.setParameter("itemType", itemtype);
		query.setFirstResult( rowStart );
		query.setMaxResults( pageSize ); 
		
		return query.list();
	}
	
	
	public Long countItens(SortProperty sortProperty) throws DAOException{
		String hql = "select count (i.id) from Item i where i.type != 1 order by i." + sortProperty.getValue();
		Query<Long> query = createQuery(hql, Long.class);
		
		return (Long) query.uniqueResult();
	}
	
	@Deprecated
	public List<Item> paginationList( 
			SortProperty sortProperty, String order, int rowStart, int pageSize ) throws DAOException{
		String hql = "from Item i where i.type.id != 1 order by i." + sortProperty.getValue() + " " + order;
		Query<Item> query = createQuery(hql, Item.class);
		query.setFirstResult( rowStart );
		query.setMaxResults( pageSize ); 
		
		return query.list();
	}
}
