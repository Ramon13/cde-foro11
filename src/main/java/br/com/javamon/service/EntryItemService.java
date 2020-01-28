package br.com.javamon.service;

import java.io.Serializable;
import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.dao.EntryItemDAO;
import br.com.javamon.entity.EntryItem;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class EntryItemService extends Service{

	public void save(EntryItem ei) throws ServiceException{
		try {
			getDaoFactory().getDAO(EntryItemDAO.class).save(ei);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<EntryItem> listEntryItensByItem(Serializable itemId, FilterProperties filterProps, PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryItemDAO.class).listEntryItensByItem(itemId, filterProps, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<EntryItem> listEntryItensByEntry(Serializable entryId, FilterProperties filterProps, PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryItemDAO.class).listEntryItensByEntry(entryId, filterProps, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<EntryItem> listEntryItens(FilterProperties filterProps, PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryItemDAO.class).listEntryItens(filterProps, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countEntryItensByItem(Long itemId, FilterProperties filteRProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryItemDAO.class).countNumEntryItensByItem(itemId, filteRProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countEntryItensByEntry(Long entryId, FilterProperties filteRProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryItemDAO.class).countNumEntryItensByEntry(entryId, filteRProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countEntryItens(FilterProperties filteRProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryItemDAO.class).countNumEntryItens(filteRProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
