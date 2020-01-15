package br.com.javamon.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.ItemFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.admin.domain.FilterProperties.PROPERTIES;
import br.com.javamon.entity.Item;
import br.com.javamon.exception.DAOException;

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
}
