package br.com.javamon.action.admin.pagination;

import javax.servlet.http.HttpSession;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.convert.NumberConvert;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validation.RequestParameterValidation;

public class ChangeNumItems extends AdminAction<FilterProperties>{

	public ChangeNumItems() {
		super(FilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		change();
		redirect("/admin/ListItens.action");
	}
	
	private void change() throws ValidatorException{
		String strNumItems = getRequest().getParameter("numItems");
		if(!RequestParameterValidation.validateStringParam(strNumItems, 32)) {
			HttpSession session = getRequest().getSession();
			Integer numItems = NumberConvert.stringToInteger(strNumItems);
			
			session.setAttribute("paginationProperties", new PaginationProperties( (numItems > 100 || numItems <= 0) ? 500 : numItems));
		}
	}

}
