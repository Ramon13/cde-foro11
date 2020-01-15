package br.com.javamon.dao;

import java.util.List;

import br.com.javamon.entity.Document;
import br.com.javamon.exception.DAOException;

public class DocumentDAO extends DAOUtil<Document>{

	protected DocumentDAO() {
		super(Document.class);
	}

	public List<Document> list() throws DAOException{
		String hql = "from Document";
		return list(hql);
	}
}
