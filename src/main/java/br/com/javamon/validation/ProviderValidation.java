package br.com.javamon.validation;

import br.com.javamon.entity.Provider;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.ProviderService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.StringValidator;
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
	
	public void valLengthAttributes() throws ValidatorException{
		if (!StringValidator.isValidLen(provider.getDescription(), 64) 
				| !StringValidator.isValidLen(provider.getCnpj(), 64)
				| StringValidator.isEmpty(provider.getDescription())
				| StringValidator.isEmpty(provider.getCnpj())){
			throw new ValidatorException("Fornecedor ou cnpj inválido");
		}
	}
	
	public void isDescriptionExists() throws ValidatorException, ServiceException{		
		if (providerSvc.isDescriptionExists(provider.getDescription()))
			throw new ValidatorException("Este fornecedor já existe.");
	}

	public void isCnpjExists() throws ValidatorException, ServiceException{
		if (providerSvc.isCnpjExists(provider.getCnpj()))
			throw new ValidatorException("Este CNPJ já existe.");
	}
}