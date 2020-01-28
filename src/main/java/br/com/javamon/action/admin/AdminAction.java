package br.com.javamon.action.admin;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import br.com.javamon.action.Action;
import br.com.javamon.action.admin.filter.FilterList;
import br.com.javamon.action.admin.pagination.PaginationAction;
import br.com.javamon.action.admin.search.SearchAction;
import br.com.javamon.admin.domain.ApplicationHistory;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.History;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.exception.FilterException;
import br.com.javamon.exception.PaginationException;
import br.com.javamon.exception.SearchException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.PaginationService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validation.RequestParameterValidation;

public abstract class AdminAction <T extends FilterProperties> extends Action{
	
	private Class<T> clazz;
	
	public AdminAction(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void process() throws Exception {
//			ApplicationHistory ah = (ApplicationHistory) getRequest().getSession().getAttribute("history");
//		
//			String[] splitedURI = getRequest().getRequestURI().toString().split("/admin/*");
//			
//			History history = new History("/admin/" + splitedURI[1], getRequest().getParameterMap());
//			ah.add(history);

			try {
				processAction();
			} catch (ValidatorException e) {
				getResponse().setStatus(230);
				getResponse().getWriter().print(new String(e.getMessage().getBytes(), StandardCharsets.ISO_8859_1));
			}
	}

	protected abstract void processAction() throws Exception;
	
	public PaginationProperties updatePaginationStats(HttpServletRequest request, Long numOfItens) throws PaginationException{
		try {
			PaginationProperties paginationProps = new PaginationAction()
					.updatePaginationStats(request, ServiceFactory.getInstance().getService(PaginationService.class));
			
			paginationProps.setNumOfItens(numOfItens);
			putPropertiesInSession(paginationProps);
			return paginationProps;
		} catch (ServiceException e) {
			throw new PaginationException(e);
		}
	}
	
	public FilterProperties updateFilterListStats(HttpServletRequest request, FilterProperties filterProperties) throws FilterException{
		FilterList filterItemListAction = new FilterList();
		return filterItemListAction.updateFilterListStats(filterProperties, request);
	}
	
	public void updateSearchListStats(HttpServletRequest request, FilterProperties filterProperties) throws SearchException{
		SearchAction searchAction = new SearchAction();
		searchAction.updateSearchStats(request, filterProperties);
	}
	
	private void putPropertiesInSession(PaginationProperties paginationProps) throws PaginationException{
		putInSession("paginationProperties", paginationProps);
	}	
	
	protected void resetFilter(String filterName) throws ValidatorException{
		String resetFilters = getRequest().getParameter("resetFilters");
		
		if(!RequestParameterValidation.isEmpty(resetFilters) && 
				!RequestParameterValidation.isEmpty(resetFilters) && Boolean.parseBoolean(resetFilters) == true){
			getRequest().getSession().setAttribute(filterName, null);
		}
	}
	
	protected FilterProperties getSessionFilterProperties(String filterName) throws FilterException{
		try {
			resetFilter(filterName);
			FilterProperties filterProperties = clazz.newInstance();
			Object obj = getRequest().getSession().getAttribute(filterName);
			if(obj != null)
				filterProperties = (FilterProperties) obj;
			else
				getRequest().getSession().setAttribute(filterName, filterProperties);
			return filterProperties;
		} catch (ValidatorException | InstantiationException | IllegalAccessException e) {
			throw new FilterException(e);
		}
	}
}
