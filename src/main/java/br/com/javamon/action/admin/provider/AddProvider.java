	package br.com.javamon.action.admin.provider;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.entity.Provider;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.ProviderService;
import br.com.javamon.validation.ProviderValidation;

public class AddProvider extends AdminAction<FilterProperties>{

	public AddProvider() {
		super(FilterProperties.class);
	}
	
	@Override
	protected void processAction() throws Exception {
		String save = getRequest().getParameter("save");
		
		if (!StringUtils.isBlank(save)){
			saveProvider();
			
			getRequest().setAttribute("providers", getServiceFactory().getService(ProviderService.class).list());
			foward("/admin/jsp/include/add_provider_select.jsp");
		}else {
			foward("/admin/jsp/ajax/add_provider.jsp");
		}
	}
	
	private void saveProvider() throws ServiceException, ValidatorException{
		Provider provider = createProvider();
		getServiceFactory().getService(ProviderService.class).save(provider);
	}
	
	private Provider createProvider() throws ValidatorException, ServiceException {
		String description = getRequest().getParameter("providerDesc");
		String cnpj = getRequest().getParameter("providerCnpj");
		
		Provider provider = new Provider();
		provider.setDescription(description);
		provider.setCnpj(cnpj);
		
		new ProviderValidation(provider).validateProvider();
		return provider;
	}	
}