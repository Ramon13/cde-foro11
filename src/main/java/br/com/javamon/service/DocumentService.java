package br.com.javamon.service;

import java.util.List;

import br.com.javamon.dao.DocumentDAO;
import br.com.javamon.entity.Document;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class DocumentService extends Service{

	public void save(Document document) throws ServiceException{
		try {
			getDaoFactory().getDAO(DocumentDAO.class).save(document);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Document load(Long id) throws ServiceException{
		try {
			return getDaoFactory().getDAO(DocumentDAO.class).load(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Document> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(DocumentDAO.class).list();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public Document getDocumentByDescription(String docNumber) throws ServiceException{
		try {
			return getDaoFactory().getDAO(DocumentDAO.class).getDocumentByDescription(docNumber);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
