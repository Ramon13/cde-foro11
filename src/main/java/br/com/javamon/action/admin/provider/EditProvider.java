	package br.com.javamon.action.admin.provider;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Provider;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.ProviderService;
import br.com.javamon.validation.ProviderValidation;
import br.com.javamon.validation.RequestParameterValidation;

public class EditProvider extends AdminAction<FilterProperties>{

	public EditProvider() {
		super(FilterProperties.class);
	}
	
	@Override
	protected void processAction() throws Exception {
		String save = getRequest().getParameter("save");
		
		if (!StringUtils.isBlank(save)){
			ProviderService providerSvc = getServiceFactory().getService(ProviderService.class);
			
			Provider oldProvider = loadProvider();
			Provider newProvider = createProvider();
			providerSvc.editProvider(newProvider, oldProvider);
			
			getRequest().setAttribute("providers", providerSvc.list());
			foward("/admin/jsp/include/add_provider_select.jsp");
		}else {
			Provider provider = loadProvider();
			getRequest().setAttribute("provider", provider);
			foward("/admin/jsp/ajax/add_provider.jsp");
		}
	}
	
	private Provider loadProvider() throws ServiceException, ValidatorException, ConvertException{
		String strProviderId = getRequest().getParameter("providerId");
		RequestParameterValidation.validateStringParam(strProviderId, 16);
		
		Long providerId = StringConvert.stringToLong(strProviderId);
		
		return getServiceFactory().getService(ProviderService.class).load(providerId);
	}
	
	private Provider createProvider() throws ValidatorException, ServiceException {
		String description = getRequest().getParameter("providerDesc");
		String cnpj = getRequest().getParameter("providerCnpj");
		
		Provider provider = new Provider();
		provider.setDescription(description);
		provider.setCnpj(cnpj);
		
		ProviderValidation providerValidation = new ProviderValidation(provider);
		providerValidation.valLengthAttributes();
		return provider;
	}	
}