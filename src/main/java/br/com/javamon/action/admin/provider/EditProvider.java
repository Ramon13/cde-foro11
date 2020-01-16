	package br.com.javamon.action.admin.provider;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.Convert;
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
			foward("/admin/AddProvider.action");
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
}