package br.com.javamon.service;

import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.exception.ServiceException;

/**
 * This class provides methods to handle with pagination services.
 * Pagination services loads list partially
 * @author Ramon
 * @version 1.0
 */
public class PaginationService extends Service{		
	
	public void plusPageValue(PaginationProperties paginationProps) throws ServiceException{
		int page = paginationProps.getPage() + 1;
		int maxItens = paginationProps.MAX_NUM_OF_ITENS;
		paginationProps.setFirstValue( (page - 1) * maxItens);
		paginationProps.setLastValue( page * maxItens);
		paginationProps.setPage(page);
	}
	
	public void decreasePageValue(PaginationProperties paginationProps) throws ServiceException{	
		int page = paginationProps.getPage() - 1;
		int maxItens = paginationProps.MAX_NUM_OF_ITENS;
		int firstValue = paginationProps.DEFAULT_FIRST_OCURRENCE_VALUE;
		
		paginationProps.setFirstValue( (page - 1) * maxItens);
		paginationProps.setLastValue( (page) * maxItens);
		paginationProps.setPage(page);
	
		if(paginationProps.getFirstValue() < firstValue){
			paginationProps.setFirstValue(firstValue);
			paginationProps.setLastValue(maxItens);
			paginationProps.setPage(1);
		}
	}
}
