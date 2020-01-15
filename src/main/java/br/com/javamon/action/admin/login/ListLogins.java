package br.com.javamon.action.admin.login;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.LoginFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Login;
import br.com.javamon.service.LoginService;
import br.com.javamon.service.PermissionService;

public class ListLogins extends AdminAction<LoginFilterProperties> {

	public ListLogins() {
		super(LoginFilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		LoginService loginSvc = getServiceFactory().getService(LoginService.class);
		
		FilterProperties filterProperties = updateFilterListStats(getRequest(), getSessionFilterProperties("loginFilterProperties"));
		updateSearchListStats(getRequest(), filterProperties);
		
		Long numOfItens = loginSvc.count(filterProperties);
		PaginationProperties pagProps = updatePaginationStats(getRequest(), numOfItens);
		java.util.List<Login> logins = loginSvc.list(filterProperties, pagProps);
		
		getRequest().setAttribute("logins", logins);
		getRequest().setAttribute("numOfItens", numOfItens);
		getRequest().setAttribute("locales", getServiceFactory().getService(LocaleService.class).listLocales());
		getRequest().setAttribute("permissions", getServiceFactory().getService(PermissionService.class).list());
		
		foward("/admin/jsp/ajax/logins_list.jsp");
	}

}
