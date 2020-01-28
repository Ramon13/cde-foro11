package br.com.javamon.action.admin.filter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import br.com.javamon.action.Action;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.FilterProperties.PROPERTIES;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.exception.FilterException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validation.RequestParameterValidation;

public class FilterList extends Action {

	private FilterProperties filterProperties;
	private HttpServletRequest request;
	
	@Override
	public void process() throws Exception {
		
	}
	
	public FilterProperties updateFilterListStats(FilterProperties filterProperties, HttpServletRequest request) throws FilterException{
		this.filterProperties = filterProperties;
		this.request = request;
	
		try {
			updateProperty();
			updateOrder();
			updateFilterProperties();
		} catch (ValidatorException e) {
			throw new FilterException(e.getMessage());
		}
		
		return this.filterProperties;
	}

	private void updateProperty() throws ValidatorException{
		String paramProperty = this.request.getParameter("property");
		
		if(!RequestParameterValidation.validateStringParam(paramProperty, 32)){
			
			Set<PROPERTIES> properties = filterProperties.getPropertiesOrderMap().keySet();
			for(FilterProperties.PROPERTIES property : properties){
				if(paramProperty.equalsIgnoreCase(property.toString())){
					filterProperties.setProperty(property);
				}
			}
		}
	}
	
	private void updateOrder() throws ValidatorException{
		String paramOrder = request.getParameter("order");
		
		if(!RequestParameterValidation.validateStringParam(paramOrder, 5)){
			if(paramOrder.equalsIgnoreCase(FilterProperties.ORDER.ASC.toString()))
				filterProperties.setOrder(FilterProperties.ORDER.ASC);
			else
				filterProperties.setOrder(FilterProperties.ORDER.DESC);
		}
	}
	
	private void updateFilterProperties() throws ValidatorException{	
		String tagName = this.request.getParameter("tagName");
		String[] ids = this.request.getParameterValues(tagName);
		
		if(!RequestParameterValidation.validateStringParam(tagName, 32) && ids != null){
			
			Map<PROPERTIES, List<Long>> idListMap = filterProperties.getIdListMap();
			
			for(PROPERTIES property : idListMap.keySet()){
				
				if(tagName.equalsIgnoreCase(property.toString())){
					idListMap.get(property).clear();
					List<Long> filterParameterIds = StringConvert.stringArrToLongList(ids);
					for(Long l : filterParameterIds){
						idListMap.get(property).add(l);
					}					
				}
			}
		}
	}	
}
