package br.com.javamon.dao;

import java.util.List;

import br.com.javamon.entity.ItemType;
import br.com.javamon.exception.DAOException;

public class ItemTypeDAO extends DAO<ItemType>{

	protected ItemTypeDAO() {
		super(ItemType.class);
	}
	
	public List<ItemType> list() throws DAOException{
		String hql = "from ItemType";
		return createQuery(hql, ItemType.class).list();
	}
	
	public List<Long> listIds() throws DAOException{
		String hql = "select i.id from ItemType i";
		return createQuery(hql, Long.class).list();
	}

}
