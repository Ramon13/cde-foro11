package br.com.javamon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.dao.ProviderDAO;
import br.com.javamon.entity.Provider;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validation.ProviderValidation;
import br.com.javamon.validatior.StringValidator;

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
	
	public void delete(Provider provider) throws ServiceException{
		try {
			getDaoFactory().getDAO(ProviderDAO.class).delete(provider);
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
	
	public void editProvider(Provider newProvider, Provider oldProvider) throws ServiceException, ValidatorException{
		ProviderValidation providerValidation = new ProviderValidation(newProvider);
		
		if (!StringValidator.isEqualsIgnoreCase(newProvider.getDescription(), oldProvider.getDescription())){
			providerValidation.isDescriptionExists();
			oldProvider.setDescription(newProvider.getDescription());
		}
		
		if (!StringValidator.isEqualsIgnoreCase(newProvider.getCnpj(), oldProvider.getCnpj())){
			providerValidation.isCnpjExists();
			oldProvider.setCnpj(newProvider.getCnpj());
		}
	}
}