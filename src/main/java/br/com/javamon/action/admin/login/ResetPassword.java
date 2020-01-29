package br.com.javamon.action.admin.login;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Login;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.LoginService;
import br.com.javamon.validation.RequestParameterValidation;

public class ResetPassword extends AdminAction<FilterProperties> {

	public ResetPassword() {
		super(FilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		String strLoginId = getRequest().getParameter("loginId");
		
		if ( RequestParameterValidation.validateLongParam(strLoginId, 32)){
			LoginService loginSvc = getServiceFactory().getService(LoginService.class);
			
			Login login = loginSvc.load(StringConvert.stringToLong(strLoginId));
			String tempPass = loginSvc.generateTemporaryPassword();
			
			login.setResetPassword(true);
			login.setPassword(tempPass);
			
			getResponse().getWriter().print(tempPass);
		
		}else{
			throw new ValidatorException("Não foi possível resetar a senha, contate o administrador do sistema.");
		}
		
	}
	
	

}
