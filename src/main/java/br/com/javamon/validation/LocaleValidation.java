package br.com.javamon.validation;

import java.util.List;

import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.Login;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.LoginService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.Validator;

public class LocaleValidation extends Validator{

	public static boolean isLocaleDescriptionExists(String description) throws ValidatorException{
		try {
			return ServiceFactory
					.getInstance()
					.getService(LocaleService.class)
					.getLocaleByDescription(description) != null;
		} catch (ServiceException e) {
			throw new ValidatorException(e);
		}
	}
	
	public static boolean isLocaleExists(Long id) throws ValidatorException{
		try {
			return ServiceFactory
					.getInstance()
					.getService(LocaleService.class)
					.load(id) != null;
		} catch (ServiceException e) {
			throw new ValidatorException(e);
		}
	}
	
	public static boolean localeIsUsed(Locale locale) throws ValidatorException{
		try {
			List<Login> list = ServiceFactory.getInstance().getService(LoginService.class).listLoginsByLocale(locale);
			if(list != null && list.size() > 0)
				return true;
			return false;
		} catch (ServiceException e) {
			throw new ValidatorException(e);
		}
	}
}
