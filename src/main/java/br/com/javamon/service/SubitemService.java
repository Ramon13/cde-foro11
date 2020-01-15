package br.com.javamon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.dao.SubitemDAO;
import br.com.javamon.entity.Subitem;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;

public class SubitemService extends Service {

	public List<Subitem> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(SubitemDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getSubitemIds() throws ServiceException{
		List<Long> subitemIdList = new ArrayList<>();
		for(Subitem sb : list()){
			subitemIdList.add(sb.getId());
		}
		return subitemIdList;
	}
}
