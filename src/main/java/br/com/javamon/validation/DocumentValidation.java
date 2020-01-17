package br.com.javamon.validation;

import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.DocumentService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.Validator;

public class DocumentValidation extends Validator{

	public static boolean isDocumentDescriptionExists(String docNumber) throws ValidatorException{
		try {
			return ServiceFactory
					.getInstance()
					.getService(DocumentService.class)
					.getDocumentByDescription(docNumber) != null;
		} catch (ServiceException e) {
			throw new ValidatorException(e);
		}
	}
}
