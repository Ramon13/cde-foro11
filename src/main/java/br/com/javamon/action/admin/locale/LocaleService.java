package br.com.javamon.action.admin.locale;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.dao.LocaleDAO;
import br.com.javamon.entity.Locale;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.service.Service;

/**
 * This class provides methods to process Locale operations
 * @author ramon
 *@version 1.0
 */
public class LocaleService extends Service{
	
	public void delete(Locale locale) throws ServiceException{
		try {
			getDaoFactory().getDAO(LocaleDAO.class).delete(locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Locale load(Long localeId) throws ServiceException{
		try {
			return getDaoFactory().getDAO(LocaleDAO.class).load(localeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void save(Locale locale) throws ServiceException{
		try {
			getDaoFactory().getDAO(LocaleDAO.class).save(locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * List all Locales. Access the DAO layer and request a list of locales.
	 * @return a java.util.List of locales
	 * @throws ServiceException
	 */
	public List<Locale> listLocales() throws ServiceException{
		try {
			return getDaoFactory().getDAO(LocaleDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getLocaleIds() throws ServiceException{
		List<Long> localeIdList = new ArrayList<>();
		for(Locale locale : listLocales()){
			localeIdList.add(locale.getId());
		}
		return localeIdList;
	}
	
	public Locale getLocaleByDescription(String description) throws ServiceException{
		try {
			return getDaoFactory().getDAO(LocaleDAO.class).getLocaleByDescription(description);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
