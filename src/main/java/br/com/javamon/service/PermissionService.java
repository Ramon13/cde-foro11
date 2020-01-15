package br.com.javamon.service;

import java.util.List;

import br.com.javamon.dao.PermissionDAO;
import br.com.javamon.entity.Permission;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class PermissionService extends Service{

	public List<Permission> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(PermissionDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getPermissionIds() throws ServiceException{
		try {
			return getDaoFactory().getDAO(PermissionDAO.class).listIds();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
