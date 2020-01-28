package br.com.javamon.action.admin.locale;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Locale;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validation.LocaleValidation;
import br.com.javamon.validation.RequestParameterValidation;

public class DeleteLocale extends AdminAction<FilterProperties>{

	public DeleteLocale() {
		super(FilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		delete();
	}

	private void delete() throws Exception, ValidatorException{
		String save = getRequest().getParameter("delete");
		String strLocaleId = getRequest().getParameter("localeId");
		
		if(!RequestParameterValidation.validateStringParam(save, 32)
				&& RequestParameterValidation.validateLongParam(strLocaleId, 32)){
			
			Locale locale = validateLocale();
			
			LocaleService localeSvc = getServiceFactory().getService(LocaleService.class);
			localeSvc.delete(locale);
			
			getRequest().setAttribute("locales", localeSvc.listLocales());
			foward("/admin/jsp/include/add_locale_select.jsp");
			return;
		}
		
		foward("/admin/jsp/ajax/add_locale.jsp");
	}
	
	private Locale validateLocale() throws ValidatorException{
		try {
			String localeId = getRequest().getParameter("localeId");
			
			if(!RequestParameterValidation.validateLongParam(localeId, 32))
				throw new ValidatorException("Local inválido");
			if(!LocaleValidation.isLocaleExists(StringConvert.stringToLong(localeId)))
				throw new ValidatorException("Este local não está cadastrado no sistema.");
			
			Locale locale = getServiceFactory().getService(LocaleService.class).load(StringConvert.stringToLong(localeId));
			
			if(LocaleValidation.localeIsUsed(locale))
				throw new ValidatorException("Existem usuários cadastrados com este local.");
			
			return locale;
		} catch (ConvertException | ServiceException e) {
			throw new ValidatorException("Local inválido");
		}
	}
}
