package br.com.javamon.service;

import java.util.List;

import br.com.javamon.dao.DocumentDAO;
import br.com.javamon.entity.Document;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class DocumentService extends Service{

	public List<Document> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(DocumentDAO.class).list();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
