package br.com.javamon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.dao.ProviderDAO;
import br.com.javamon.entity.Provider;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class ProviderService extends Service {

	public List<Provider> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(ProviderDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void save(Provider p) throws ServiceException{
		try {
			getDaoFactory().getDAO(ProviderDAO.class).save(p);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Provider load(Long providerId) throws ServiceException{
		try {
			Provider provider = getDaoFactory().getDAO(ProviderDAO.class).load(providerId);
			if(provider == null)
				throw new ServiceException("This provider does not exists.");
			return provider;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getProviderIds() throws ServiceException{
		List<Long> providerIdList = new ArrayList<>();
		for(Provider provider : list()){
			providerIdList.add(provider.getId());
		}
		return providerIdList;
	}
	
	public boolean isDescriptionExists(String providerDescription) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ProviderDAO.class).isDescriptionExists(providerDescription);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public boolean isCnpjExists(String providerCnpj) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ProviderDAO.class).isCnpjExists(providerCnpj);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}