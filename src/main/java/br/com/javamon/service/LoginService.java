package br.com.javamon.service;

import java.util.ArrayList;
import java.util.List;

import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.dao.LoginDAO;
import br.com.javamon.entity.Login;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;

public class LoginService extends Service {

	public List<Login> list() throws ServiceException{
		try {
			return getDaoFactory().getDAO(LoginDAO.class).list();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Login> list(FilterProperties filterProperties, PaginationProperties paginationProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(LoginDAO.class).list(paginationProperties, filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long count(FilterProperties filterProperties) throws ServiceException{
		try {
			return getDaoFactory().getDAO(LoginDAO.class).count(filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getLoginIds() throws ServiceException{
		List<Long> loginIdList = new ArrayList<>();
		for(Login login : list()){
			loginIdList.add(login.getId());
		}
		return loginIdList;
	}
	
	public Login login (Login login) throws ValidatorException, ServiceException{
		try {
			Login l = getDaoFactory().getDAO(LoginDAO.class).login(login.getUser(), login.getPassword());
			if( l == null ){
				throw new ValidatorException();
			}
			return l;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public void update(Login login) throws ServiceException{
		try {
			getDaoFactory().getDAO(LoginDAO.class).update(login);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
	}
}
