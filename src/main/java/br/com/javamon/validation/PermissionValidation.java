package br.com.javamon.validation;

import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.PermissionService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.Validator;

public class PermissionValidation extends Validator{

	public static boolean isPermissionExists(Long id) throws ValidatorException {
		try {
			return ServiceFactory.getInstance().getService(PermissionService.class).load(id) != null;
		} catch (ServiceException e) {
			throw new ValidatorException(e);
		}
	}
}
