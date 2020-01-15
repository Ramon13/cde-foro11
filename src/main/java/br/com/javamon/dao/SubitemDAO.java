package br.com.javamon.dao;

import java.util.List;

import br.com.javamon.entity.Subitem;
import br.com.javamon.exception.DAOException;

public class SubitemDAO extends DAO<Subitem>{

	protected SubitemDAO() {
		super(Subitem.class);
	}
	
	public List<Subitem> list() throws DAOException{
		String hql = "from Subitem";
		return createQuery(hql, Subitem.class).list();
	}

}
