package br.com.javamon.dao;

import java.util.List;

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
}
