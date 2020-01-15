package br.com.javamon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.dao.UnityTypeDAO;
import br.com.javamon.entity.UnityType;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class UnityTypeService extends Service {

	public List<UnityType> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(UnityTypeDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getUnitypeIds() throws ServiceException{
		List<Long> unityTypeIdList = new ArrayList<>();
		for(UnityType ut : list()){
			unityTypeIdList.add(ut.getId());
		}
		return unityTypeIdList;
	}
}
