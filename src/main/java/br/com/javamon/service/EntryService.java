package br.com.javamon.service;

import java.io.Serializable;
import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.dao.EntryDAO;
import br.com.javamon.entity.Entry;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class EntryService extends Service{

	public void save(Entry entry) throws ServiceException{
		try {
			getDaoFactory().getDAO(EntryDAO.class).save(entry);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Entry> listEntriesByItem(Serializable itemId, FilterProperties filterProps, PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryDAO.class).listEntriesByItem(itemId, filterProps, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Entry> listEntries(FilterProperties filterProps, PaginationProperties paginationProps) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryDAO.class).listEntries(filterProps, paginationProps);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countEntriesByItem(Long itemId, FilterProperties filteRProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryDAO.class).countNumEntriesByItem(itemId, filteRProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countEntries(FilterProperties filteRProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryDAO.class).countNumEntries(filteRProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Entry> listEntriesByDocument(String doc) throws ServiceException{
		try {
			return getDaoFactory().getDAO(EntryDAO.class).listEntriesByDocument(doc);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
