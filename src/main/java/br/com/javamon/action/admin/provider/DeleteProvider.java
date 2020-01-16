	package br.com.javamon.action.admin.provider;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Provider;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.ProviderService;
import br.com.javamon.validation.RequestParameterValidation;

public class DeleteProvider extends AdminAction<FilterProperties>{

	public DeleteProvider() {
		super(FilterProperties.class);
	}
	
	@Override
	protected void processAction() throws Exception {
		Provider provider = loadProvider();
		
		ProviderService providerSvc = getServiceFactory().getService(ProviderService.class);
		providerSvc.delete(provider);
		
		getRequest().setAttribute("providers", providerSvc.list());
		foward("/admin/jsp/include/add_provider_select.jsp");
	}
	
	private Provider loadProvider() throws ServiceException, ValidatorException, ConvertException{
		String strProviderId = getRequest().getParameter("providerId");
		RequestParameterValidation.validateStringParam(strProviderId, 16);
		
		Long providerId = StringConvert.stringToLong(strProviderId);
		
		return getServiceFactory().getService(ProviderService.class).load(providerId);
	}
}