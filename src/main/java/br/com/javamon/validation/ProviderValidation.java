package br.com.javamon.validation;

import br.com.javamon.entity.Provider;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.ProviderService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.Validator;

public class ProviderValidation extends Validator{

	private Provider provider;
	private ProviderService providerSvc;
	
	public ProviderValidation(Provider provider){
		this.provider = provider;
		try {
			providerSvc = ServiceFactory.getInstance().getService(ProviderService.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void validateProvider() throws ValidatorException, ServiceException{
		valLengthAttributes();
		isDescriptionExists();
		isCnpjExists();
	}
	
	private void valLengthAttributes() throws ValidatorException{
		if(!RequestParameterValidation.validateStringParam(provider.getDescription(), 64)
				&& !RequestParameterValidation.validateStringParam(provider.getCnpj(), 64))
			throw new ValidatorException("fornecedor vazio");
	}
	
	private void isDescriptionExists() throws ValidatorException, ServiceException{		
		if (providerSvc.isDescriptionExists(provider.getDescription()))
			throw new ValidatorException("Este fornecedor já existe.");
	}

	private void isCnpjExists() throws ValidatorException, ServiceException{
		if (providerSvc.isCnpjExists(provider.getCnpj()))
			throw new ValidatorException("Este CNPJ já existe.");
	}
}