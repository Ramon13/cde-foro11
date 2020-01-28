package br.com.javamon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.dao.PermissionDAO;
import br.com.javamon.entity.Permission;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class PermissionService extends Service{

	public Permission load(Long id) throws ServiceException{
		try {
			return getDaoFactory().getDAO(PermissionDAO.class).load(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
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
	
	public List<Permission> listByLevel(Integer level) throws ServiceException{
		List<Permission> permissions = new ArrayList<Permission>();
		for(Permission p : list())
			if(p.getLevel() < level)
				permissions.add(p);
		return permissions;
	}
}
