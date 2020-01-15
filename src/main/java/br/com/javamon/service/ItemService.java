package br.com.javamon.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.convert.DateConvert;
import br.com.javamon.dao.DAOFactory;
import br.com.javamon.dao.ItemDAO;
import br.com.javamon.entity.Item;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

/**
 * This class provides public methods to process item operations
 * @author ramon
 *@version 1.0
 */
public class ItemService extends Service{

	private ItemDAO itemDAO;
	
	public ItemService(){
		try {
			itemDAO = DAOFactory.getInstance().getDAO(ItemDAO.class);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Item> listItens() throws ServiceException{
		try {
			return itemDAO.listItem();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Item> listItens(PaginationProperties paginationProp) throws ServiceException{
		try {
			return itemDAO.listItem(paginationProp.getFirstValue() - 1, paginationProp.MAX_NUM_OF_ITENS);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Item> listItens(PaginationProperties paginationProp, FilterProperties filterProperties) throws ServiceException{
		try {
			return itemDAO.listItem(paginationProp, filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countNumOfItens(FilterProperties filterProperties)throws ServiceException{
		try {
			return itemDAO.countNumItens(filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long getNumOfPages(Long numOfItens, PaginationProperties paginationProps) throws ServiceException{
		return numOfItens / paginationProps.MAX_NUM_OF_ITENS;
	}
	
	public Date convertLocalDateToDate(LocalDate localDate) throws ServiceException{ 
		try {
			return DateConvert.localDateToDate(localDate);
		} catch (ConvertException e) {
			throw new ServiceException(e);
		}
	}
	
	public Item load(Serializable id) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemDAO.class).load(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
