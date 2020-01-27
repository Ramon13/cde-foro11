package br.com.javamon.dao;

import java.util.List;

import org.hibernate.query.Query;

import br.com.javamon.entity.Locale;
import br.com.javamon.exception.DAOException;

public class LocaleDAO extends DAO<Locale>{

	protected LocaleDAO() {
		super(Locale.class);
	}

	public List<Locale> list() throws DAOException{
		String hql = "from Locale l order by l.description";
		return list(hql);
	}
	
	public Locale getLocaleByDescription(String description) throws DAOException{
		String hql = "from Locale l where l.description = :description";
		Query<Locale> query = createQuery(hql, Locale.class);
		query.setParameter("description", description);
		return query.uniqueResult();
	}
}
