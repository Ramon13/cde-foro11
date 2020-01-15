package br.com.javamon.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.FilterProperties.PROPERTIES;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.exception.DAOException;

public abstract class DAOUtil<T> extends DAO<T> {

	protected DAOUtil(Class<T> clazz) {
		super(clazz);
	}

	protected void setPaginationParameters(Query<T> query, PaginationProperties paginationProperties) throws DAOException{
		query.setFirstResult(paginationProperties.getFirstValue());
		query.setMaxResults(paginationProperties.MAX_NUM_OF_ITENS);
	}
	
	public void addPropertyFilters(StringBuilder sb, FilterProperties filterProperties) throws DAOException{	
		Map<PROPERTIES, List<Long>> idListMap = filterProperties.getIdListMap();
		
		int count = 0;
		for(PROPERTIES property : idListMap.keySet()){
			if(idListMap.get(property).size() > 0)
				count++;
		}
		
		for(PROPERTIES property : idListMap.keySet()){
			List<Long> currIdList;
			if( (currIdList = idListMap.get(property)).size() > 0){
				addPropertyFilter(filterProperties, sb, currIdList, property);
				if(count > 1){
					sb.append(" and ");
					count--;
				}
			}
		}		
	}
	
	private void addPropertyFilter(FilterProperties filterProperties, 
			StringBuilder sb, List<Long> propertyIdList, FilterProperties.PROPERTIES property) throws DAOException{
		
		sb.append(" ( ");
		for(int i = 0 ; i < propertyIdList.size() ; i++){
			sb.append(filterProperties.getPropertiesIdMap()
					.get(property) + " like " + propertyIdList.get(i));
			
			if(i < propertyIdList.size() - 1)
				sb.append(" or ");
		}
		sb.append(" ) ");
	}
	
	protected boolean addSearchFilter(StringBuilder sb, FilterProperties filterProperties) throws DAOException{
		PROPERTIES property = filterProperties.getSearchProperties().getSearchType();
		boolean hasSearchKey = false;
		if(!StringUtils.isBlank(filterProperties.getSearchProperties().getSearchKey()) ){
			sb.append(" ( ");
			String mapValue = filterProperties.getPropertiesOrderMap().get(property);
			sb.append( mapValue + " like " + "'%" + filterProperties.getSearchProperties().getSearchKey() + "%' ");
			sb.append(" ) ");
			hasSearchKey = true;
		}
		return hasSearchKey;
	}
	
	protected void setSQLQueryOrder(StringBuilder sb, FilterProperties filterProperties) throws DAOException{
		Set<PROPERTIES> keySet = filterProperties.getPropertiesOrderMap().keySet();
		
		for(FilterProperties.PROPERTIES property :  keySet){
			if(property == filterProperties.getProperty()){
				sb.append(" ORDER BY " + filterProperties.getPropertiesOrderMap().get(property) + " " + filterProperties.getOrder());
			}
		}
	}
	
	protected void setPaginationParameters(Query<T> query, int firstResult, int maxResult) throws DAOException{
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
	}
}
