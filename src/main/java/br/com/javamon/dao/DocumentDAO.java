package br.com.javamon.dao;

import java.util.List;

import org.hibernate.query.Query;

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
	
	public Document getDocumentByDescription(String number) throws DAOException{
		String hql = "from Document d where lower(d.number) like :number";
		Query<Document> query = createQuery(hql, Document.class);
		query.setParameter("number", number.toLowerCase());
		return query.uniqueResult();
	}
}
