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
	
	public List<Long> getProviderIds() throws ServiceException{
		List<Long> providerIdList = new ArrayList<>();
		for(Provider provider : list()){
			providerIdList.add(provider.getId());
		}
		return providerIdList;
	}
}
