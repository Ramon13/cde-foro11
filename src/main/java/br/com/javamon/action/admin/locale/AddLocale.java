package br.com.javamon.action.admin.locale;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.entity.Locale;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validation.LocaleValidation;
import br.com.javamon.validation.RequestParameterValidation;

public class AddLocale extends AdminAction<FilterProperties>{

	public AddLocale() {
		super(FilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		save();
	}

	private void save() throws Exception, ValidatorException{
		String save = getRequest().getParameter("save");
		
		if(!RequestParameterValidation.validateStringParam(save, 32) ){
			LocaleService localeSvc = getServiceFactory().getService(LocaleService.class);
			Locale newLocale = validateLocale();
			localeSvc.save(newLocale);
			
			getRequest().setAttribute("locales", localeSvc.listLocales());
			foward("/admin/jsp/include/add_locale_select.jsp");
			return;
		}
		
		foward("/admin/jsp/ajax/add_locale.jsp");
	}
	
	private Locale validateLocale() throws ValidatorException{
		String localeDescription = getRequest().getParameter("localeDescription");
		
		if(RequestParameterValidation.validateStringParam(localeDescription, 32))
			throw new ValidatorException("Local inválido");
		if(LocaleValidation.isLocaleDescriptionExists(localeDescription))
			throw new ValidatorException("Este local já está cadastrado no sistema.");
		
		Locale locale = new Locale();
		locale.setDescription(localeDescription);
		return locale;
	}
}
