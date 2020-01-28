package br.com.javamon.validation;

import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.LoginService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.Validator;

public class LoginValidation extends Validator{

	public static boolean isLoginDescriptionExists(String description) throws ValidatorException{
		try {
			return ServiceFactory.getInstance().getService(LoginService.class).getLoginByDescription(description) != null;
		} catch (ServiceException e) {
			throw new ValidatorException(e);
		}
	}
}
