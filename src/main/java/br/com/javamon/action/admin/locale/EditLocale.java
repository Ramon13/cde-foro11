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

public class EditLocale extends AdminAction<FilterProperties>{

	public EditLocale() {
		super(FilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		load();
		edit();
	}

	private void edit() throws Exception, ValidatorException{
		String save = getRequest().getParameter("save");
		String strLocaleId = getRequest().getParameter("localeId");
		
		validateDescription();
		if(!RequestParameterValidation.validateStringParam(save, 32)
				&& RequestParameterValidation.validateLongParam(strLocaleId, 32)){
			
			String localeDescription = getRequest().getParameter("newDescription");
			
			LocaleService localeSvc = getServiceFactory().getService(LocaleService.class);
			Locale locale = localeSvc.load(StringConvert.stringToLong(strLocaleId));
			
			if(!locale.getDescription().equals(localeDescription))
				locale.setDescription(localeDescription);
			
			getRequest().setAttribute("locales", localeSvc.listLocales());
			foward("/admin/jsp/include/add_locale_select.jsp");
			return;
		}
	}
	
	private void load() throws Exception, ValidatorException{
		try{
			String strLocaleId = getRequest().getParameter("localeId");
			String edit = getRequest().getParameter("edit");
			
			if(!RequestParameterValidation.validateStringParam(strLocaleId, 32)
				&&	!RequestParameterValidation.validateStringParam(edit, 32)){
				
				Long localeId = StringConvert.stringToLong(strLocaleId);
				Locale locale = getServiceFactory().getService(LocaleService.class).load(localeId);
				
				getRequest().setAttribute("localeDescription", locale.getDescription());
				foward("/admin/jsp/ajax/add_locale.jsp");
				
			}
		}catch(ConvertException | ServiceException e){
			throw new ValidatorException("Local inválido"); 
		}
	}
	
	private void validateDescription() throws ValidatorException{
		String localeDescription = getRequest().getParameter("newDescription");
		
		if(RequestParameterValidation.validateStringParam(localeDescription, 32))
			throw new ValidatorException("Local inválido");
		if(LocaleValidation.isLocaleDescriptionExists(localeDescription))
			throw new ValidatorException("Este local já está cadastrado no sistema.");
	}
}
