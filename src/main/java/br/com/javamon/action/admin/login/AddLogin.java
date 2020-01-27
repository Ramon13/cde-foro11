
package br.com.javamon.action.admin.login;

import br.com.javamon.action.Action;
import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.entity.Login;
import br.com.javamon.service.PermissionService;

public class AddLogin extends Action{

	@Override
	public void process() throws Exception {
		Login login = (Login) getRequest().getSession().getAttribute("login");
		getRequest().setAttribute("locales", getServiceFactory().getService(LocaleService.class).listLocales());
		getRequest().setAttribute("permissions", getServiceFactory()
				.getService(PermissionService.class)
				.listByLevel(login.getPermission().getLevel()));

		
		foward("/admin/jsp/ajax/new_login.jsp");
	}

}
