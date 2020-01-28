package br.com.javamon.service;

import java.io.Serializable;
import java.util.List;

import br.com.javamon.dao.ItemTypeDAO;
import br.com.javamon.entity.ItemType;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class ItemTypeService extends Service {

	public List<ItemType> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemTypeDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public ItemType load(Serializable id) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemTypeDAO.class).load(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getItemTypeIds() throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemTypeDAO.class).listIds();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
