package br.com.javamon.dao;

import java.util.List;
import org.hibernate.query.Query;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.Login;
import br.com.javamon.exception.DAOException;

public class LoginDAO extends DAOUtil<Login>{

	protected LoginDAO() {
		super(Login.class);
	}
	
	public List<Login> list() throws DAOException{
		String hql = "from Login";
		return createQuery(hql, Login.class).list();
	}
	
	public List<Login> list(PaginationProperties paginationProps, FilterProperties filterProps) throws DAOException{
		StringBuilder hql = new StringBuilder("from Login l where ");
		
		boolean hasSearchKey = addSearchFilter(hql, filterProps);
		if(hasSearchKey == true)
			hql.append(" and ");
		
		addPropertyFilters(hql, filterProps);
		setSQLQueryOrder(hql, filterProps);
		System.out.println(hql.toString());
		
		Query<Login> query = createQuery(hql.toString(), Login.class);
		setPaginationParameters(query, paginationProps);
		
		return query.list();
	}
	
	public Long count(FilterProperties filterProps) throws DAOException{
		StringBuilder hql = new StringBuilder("select count (*) from Login l where ");
		
		addPropertyFilters(hql, filterProps);
		Query<Long> query = createQuery(hql.toString(), Long.class);
		return query.uniqueResult();
	}
	
	public Login login(String username, String password) throws DAOException{
		String hql = "from Login l where l.user like :user and l.password like :pass";
		Query<Login> query = createQuery(hql, Login.class);
		query.setParameter("user", username);
		query.setParameter("pass", password);
		
		return query.uniqueResult();
	}
	
	public void update(Login login) throws DAOException{
		getSession().update(login);
	}
	
	public List<Login> listLoginsByLocale(Locale locale) throws DAOException{
		String hql = "from Login as l where l.locale = :locale";
		Query<Login> query = createQuery(hql, Login.class);
		query.setParameter("locale", locale);
		return query.list();
	}
	
	public Login getLoginByDescription(String description) throws DAOException{
		String hql = "from Login as l where l.user = :description";
		Query<Login> query = createQuery(hql, Login.class);
		query.setParameter("description", description);
		return query.uniqueResult();
	}
}
