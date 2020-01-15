package br.com.javamon.dao;

import java.util.List;

import br.com.javamon.entity.Permission;
import br.com.javamon.exception.DAOException;

public class PermissionDAO extends DAOUtil<Permission>{

	protected PermissionDAO() {
		super(Permission.class);
	}
	
	public List<Permission> list() throws DAOException{
		StringBuilder hql = new StringBuilder("from Permission");
		return createQuery(hql.toString(), Permission.class).list();
	}
	
	public List<Long> listIds() throws DAOException{
		String hql = "select p.id from Permission p";
		return createQuery(hql, Long.class).list();
	}

}
