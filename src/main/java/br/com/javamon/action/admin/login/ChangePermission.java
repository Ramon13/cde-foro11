package br.com.javamon.action.admin.login;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Login;
import br.com.javamon.service.LoginService;
import br.com.javamon.util.LoginAction;
import br.com.javamon.validation.RequestParameterValidation;

public class ChangePermission extends AdminAction<FilterProperties>{

	public ChangePermission() {
		super(FilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		String action = getRequest().getParameter("action");
		String strUserId = getRequest().getParameter("userId");
		
		if (!RequestParameterValidation.validateStringParam(action, 32)
				&& action.equalsIgnoreCase(LoginAction.BLOCK.getValue())
				&& !RequestParameterValidation.validateStringParam(strUserId, 32)){
			
			Login login = getServiceFactory()
					.getService(LoginService.class)
					.load(StringConvert.stringToLong(strUserId));
			
			Login userLogin = (Login) getRequest().getSession().getAttribute("login");
			if (userLogin.getPermission().getLevel() > login.getPermission().getLevel())
				login.setActive(!login.getActive());
		}
	}
	
	

}
