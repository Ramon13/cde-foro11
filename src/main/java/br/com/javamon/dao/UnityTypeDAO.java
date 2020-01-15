package br.com.javamon.dao;

import java.util.List;

import br.com.javamon.entity.UnityType;
import br.com.javamon.exception.DAOException;

public class UnityTypeDAO extends DAO<UnityType>{

	protected UnityTypeDAO() {
		super(UnityType.class);
	}
	
	public List<UnityType> list() throws DAOException{
		String hql = "from UnityType";
		return createQuery(hql, UnityType.class).list();
	}

}
