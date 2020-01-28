package br.com.javamon.action.admin.search;

import javax.servlet.http.HttpServletRequest;

import br.com.javamon.action.Action;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.FilterProperties.PROPERTIES;
import br.com.javamon.exception.SearchException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validation.RequestParameterValidation;

public class SearchAction extends Action{

	private HttpServletRequest request;
	private FilterProperties filterProps;
	
	@Override
	public void process() throws Exception {
	}

	public void updateSearchStats(HttpServletRequest request, FilterProperties filterProperties) throws SearchException{
		this.filterProps = filterProperties;
		this.request = request;
		
		try {
			setSearchKey();
			setSearchType();
		} catch (ValidatorException e) {
			throw new SearchException(e);
		}
	}

	private void setSearchKey() throws ValidatorException{
		String searchKey = this.request.getParameter("itemSearch");
		
		if(!RequestParameterValidation.validateStringParam(searchKey, 32)){
			this.filterProps.getSearchProperties().setSearchKey(searchKey);
			request.getSession().setAttribute("filterProperties", filterProps);
			request.getSession().setAttribute("paginationProperties", null);
		}
			
	}
	
	private void setSearchType() throws ValidatorException{
		String searchType = this.request.getParameter("searchType");
		
		if(!RequestParameterValidation.validateStringParam(searchType, 32)){
			for(PROPERTIES property : PROPERTIES.values()){
				if(searchType.equalsIgnoreCase(property.toString())){
					filterProps.getSearchProperties().setSearchType(property);
				}
			}
		}
		
	}
}
