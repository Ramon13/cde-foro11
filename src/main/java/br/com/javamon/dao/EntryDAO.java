package br.com.javamon.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Entry;
import br.com.javamon.exception.DAOException;

public class EntryDAO extends DAOUtil<Entry> {

	public EntryDAO(){
		super(Entry.class);
	}
	
	public List<Entry> list() throws DAOException{
		String hql = "from Entry";
		return list(hql);
	}
	
	public List<Entry> listEntriesByItem(Serializable itemId, 
			FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from Entry e where e.item.id = :itemId" );
		
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) )
			hql.append(" and ");
		
		addSearchFilter(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<Entry> query = createQuery(hql.toString(), Entry.class);
		query.setParameter("itemId", itemId);
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public List<Entry> listEntries(FilterProperties filterProperties,
			PaginationProperties paginationProps) throws DAOException{
		
		StringBuilder hql = new StringBuilder();
		hql.append("from Entry e where ");
		
		boolean hasSeachFilter = addSearchFilter(hql, filterProperties);
		if(hasSeachFilter)
			hql.append(" and ");
		
		addPropertyFilters(hql, filterProperties);
		setSQLQueryOrder(hql, filterProperties);
		System.out.println(hql.toString());
		
		Query<Entry> query = createQuery(hql.toString(), Entry.class);
		setPaginationParameters(query, paginationProps.getFirstValue(), paginationProps.MAX_NUM_OF_ITENS);
		return query.list();
	}
	
	public Long countNumEntriesByItem(Long itemId, FilterProperties filterProperties)throws DAOException{
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from Entry e where e.item.id = :itemId ");
			
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
	
	public Long countNumEntries(FilterProperties filterProperties)throws DAOException{
		try {
			
			StringBuilder hql = new StringBuilder();
			hql.append("select count(*) from Entry e where ");
			
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
	
	public List<Entry> listEntriesByDocument(String doc) throws DAOException{
		StringBuilder hql = new StringBuilder("from Entry e where e.document.number = :documentNumber");
		Query<Entry> query = createQuery(hql.toString(), Entry.class);
		
		query.setParameter("documentNumber", doc);
		return query.list();
	}
}
