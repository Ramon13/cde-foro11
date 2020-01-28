package br.com.javamon.action.admin.pagination;

import javax.servlet.http.HttpServletRequest;

import br.com.javamon.action.Action;
import br.com.javamon.admin.domain.PageAction;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.exception.PaginationException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.PaginationService;
import br.com.javamon.validation.RequestParameterValidation;

/**
 *Controller class, a bridge between java EE and model services classes
 * This class provides methods to process data coming from request
 * @author ramon
 * @version 1.0
 */
public class PaginationAction extends Action{
	
	private PaginationProperties paginationProps;
	private HttpServletRequest request;
	
	@Override
	public void process() throws Exception {}
	
	/**
	 * Update pagination data. This method gets first and last values of pagination
	 * in user session, validate and update them.
	 * Increment or decrement values based on user action
	 * @param serviceFactory the service factory
	 * @param request 
	 * @throws ServiceException
	 */
	public PaginationProperties updatePaginationStats(HttpServletRequest request, PaginationService paginationSvc) throws PaginationException {
		this.request = request;
		
		resetFilter();
		this.paginationProps = getSessionPaginationProperties();
		updatePaginationData(paginationSvc);
		putPropertiesInSession();
		
		return paginationProps;
	}
	
	/**
	 * Get the values of the user session, validate and updates them.
	 * Update the first and last values based on page action performed by user
	 * @param request the HttpServletRequest object
	 * @param serviceFactory the factory of services. 
	 * @throws ServiceException
	 */
	private void updatePaginationData(PaginationService paginationSvc) throws PaginationException{
		try {
			String pageAction = request.getParameter("pageAction");
			
			if(!RequestParameterValidation.validateStringParam(pageAction, 32) && 
					!RequestParameterValidation.validateStringParam(pageAction, 32)){
			
				if(pageAction.equalsIgnoreCase(PageAction.NEXT.value))
					updateitemOrderToNextPage(paginationSvc);
				
				else
					if(pageAction.equalsIgnoreCase(PageAction.PREVIOUS.value))
						updateitemOrderToPreviousPage(paginationSvc);
				
					else
						throw new PaginationException("pagination error");
			}
		} catch (ValidatorException | ServiceException e) {
			throw new PaginationException(e);
		}
	}
	
	private void updateitemOrderToNextPage(PaginationService paginationSvc)throws ServiceException{
		paginationSvc.plusPageValue(paginationProps);
	}
	
	private void updateitemOrderToPreviousPage(PaginationService paginationSvc)throws ServiceException{
		paginationSvc.decreasePageValue(paginationProps);
	}
	
	private PaginationProperties getSessionPaginationProperties(){
		PaginationProperties paginationProps = (PaginationProperties) this.request.getSession().getAttribute("paginationProperties");
		if(paginationProps == null)
			paginationProps = new PaginationProperties();
		return paginationProps;
	}
	
	private void putPropertiesInSession(){
		request.getSession().setAttribute("paginationProperties", paginationProps);
	}
	
	private void resetFilter() throws PaginationException{
		try {
			String resetFilters = this.request.getParameter("resetFilters");
			
			if(!RequestParameterValidation.isEmpty(resetFilters) && 
					!RequestParameterValidation.isEmpty(resetFilters) && Boolean.parseBoolean(resetFilters) == true){
				this.request.getSession().setAttribute("paginationProperties", null);
			}
		} catch (ValidatorException e) {
			throw new PaginationException(e);
		}
	}
}
