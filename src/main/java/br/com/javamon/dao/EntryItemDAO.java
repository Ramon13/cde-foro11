package br.com.javamon.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.EntryItem;
import br.com.javamon.exception.DAOException;

public class EntryItemDAO extends DAOUtil<EntryItem>{

	protected EntryItemDAO() {
		super(EntryItem.class);
	}


	public List<EntryItem> listEntryItensByItem(Serializable itemId, 
			FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from EntryItem ei where ei.item.id = :itemId" );
		
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) )
			hql.append(" and ");
		
		addSearchFilter(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<EntryItem> query = createQuery(hql.toString(), EntryItem.class);
		query.setParameter("itemId", itemId);
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public List<EntryItem> listEntryItensByEntry(Serializable entryId, 
			FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from EntryItem ei where ei.entry.id = :entryId" );
		
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) )
			hql.append(" and ");
		
		addSearchFilter(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<EntryItem> query = createQuery(hql.toString(), EntryItem.class);
		query.setParameter("entryId", entryId);
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public List<EntryItem> listEntryItens(FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from EntryItem ei where ");
		
		boolean hasSeachFilter = addSearchFilter(hql, filterProperties);
		if(hasSeachFilter)
			hql.append(" and ");
		
		addPropertyFilters(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<EntryItem> query = createQuery(hql.toString(), EntryItem.class);
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public Long countNumEntryItensByItem(Long itemId, FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from EntryItem ei where ei.item.id = :itemId ");
			
			if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) )
				hql.append(" and ");
			addSearchFilter(hql, filterProperties);
			setSQLQueryOrder(hql, filterProperties);
			System.out.println("count " + hql.toString());
			
			Query<Long> query = createQuery(hql.toString(), Long.class);
			query.setParameter("itemId", itemId);
			
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public Long countNumEntryItensByEntry(Long entryId, FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from EntryItem ei where ei.entry.id = :entryId ");
			
			if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) )
				hql.append(" and ");
			addSearchFilter(hql, filterProperties);
			setSQLQueryOrder(hql, filterProperties);
			System.out.println("count " + hql.toString());
			
			Query<Long> query = createQuery(hql.toString(), Long.class);
			query.setParameter("entryId", entryId);
			
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public Long countNumEntryItens(FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from EntryItem ei where ");
			
			boolean hasSeachFilter = addSearchFilter(hql, filterProperties);
			if(hasSeachFilter)
				hql.append(" and ");
			
			addPropertyFilters(hql, filterProperties);
			System.out.println("count " + hql.toString());
			
			Query<Long> query = createQuery(hql.toString(), Long.class);
			return query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
