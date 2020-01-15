package br.com.javamon.dao;

import java.util.List;

import org.hibernate.query.Query;

import br.com.javamon.entity.Provider;
import br.com.javamon.exception.DAOException;

public class ProviderDAO extends DAOUtil<Provider>{

	protected ProviderDAO() {
		super(Provider.class);
	}

	public List<Provider> list() throws DAOException{
		StringBuilder sb = new StringBuilder("from Provider");
		return list(sb.toString());
	}
	
	public boolean isDescriptionExists(String providerDescription) throws DAOException{
		String hql = "from Provider p where p.description = :description";
		Query<Provider> query = createQuery(hql, Provider.class);
		query.setParameter("description", providerDescription);
		return query.uniqueResult() != null;
	}
}
