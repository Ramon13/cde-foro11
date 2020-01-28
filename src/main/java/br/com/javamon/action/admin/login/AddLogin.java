
package br.com.javamon.action.admin.login;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.Login;
import br.com.javamon.entity.Permission;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.LoginService;
import br.com.javamon.service.PermissionService;
import br.com.javamon.validation.LocaleValidation;
import br.com.javamon.validation.LoginValidation;
import br.com.javamon.validation.PermissionValidation;
import br.com.javamon.validation.RequestParameterValidation;

public class AddLogin extends AdminAction<FilterProperties>{

	public AddLogin() {
		super(FilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		save();
	}

	public void save() throws Exception, ValidatorException {
		String save = getRequest().getParameter("save");
		
		if(!RequestParameterValidation.validateStringParam(save, 32)) {
			System.out.println(this.getClass().getCanonicalName());
			Login newLogin = createLogin();
			getServiceFactory().getService(LoginService.class).save(newLogin);
			return;
		}
		
		Login login = (Login) getRequest().getSession().getAttribute("login");
		getRequest().setAttribute("locales", getServiceFactory().getService(LocaleService.class).listLocales());
		getRequest().setAttribute("permissions", getServiceFactory()
				.getService(PermissionService.class)
				.listByLevel(login.getPermission().getLevel()));

		
		foward("/admin/jsp/ajax/new_login.jsp");
	}
	
	public Login createLogin() throws ValidatorException {
		try {
			String strLocaleId = getRequest().getParameter("localeId");
			String strPermissionId = getRequest().getParameter("permissionId");
			String strUser = getRequest().getParameter("user");
			String strPassword = getRequest().getParameter("password");
			String strConfirmPassword = getRequest().getParameter("confirmPassword");
			
			Long localeId = StringConvert.stringToLong(strLocaleId);
			Long permissionId = StringConvert.stringToLong(strPermissionId);
			
			if (!LocaleValidation.isLocaleExists(localeId)
					|| !PermissionValidation.isPermissionExists(permissionId))
				throw new ValidatorException("Valor inválido");
			
			if(LoginValidation.isLoginDescriptionExists(strUser))
				throw new ValidatorException("Usuário já cadastrado no sistema.");
			
			if (RequestParameterValidation.validateStringParam(strPassword, 32)
					|| RequestParameterValidation.validateStringParam(strConfirmPassword, 32)
					|| !strPassword.equals(strConfirmPassword)){
				throw new ValidatorException("senhas inválidas");
			}
			
			Locale locale = getServiceFactory().getService(LocaleService.class).load(localeId);
			Permission permission = getServiceFactory().getService(PermissionService.class).load(permissionId);
			
			Login newLogin = new Login();
			newLogin.setLocale(locale);
			newLogin.setPermission(permission);
			newLogin.setPassword(strPassword);
			newLogin.setUser(strUser);
			
			return newLogin;
		} catch (ConvertException | ServiceException e) {
			e.printStackTrace();
			throw new ValidatorException("Valor inválido");
		}
		
	}
}
