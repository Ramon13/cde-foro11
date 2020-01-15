package br.com.javamon.action.admin.item;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import br.com.javamon.convert.DateConvert;

public class ChangeFilterDate extends Action{

	@Override
	public void process() throws Exception {
		String startDate = getRequest().getParameter("startDate");
		String currentDate = getRequest().getParameter("currentDate");
		
		if(!StringUtils.isBlank(startDate)){
			getRequest().getSession().setAttribute("startDate", DateConvert.stringToLocalDate(startDate, "yyyy-MM-dd"));
		}
		
		if(!StringUtils.isBlank(currentDate)){
			getRequest().getSession().setAttribute("currentDate", DateConvert.stringToLocalDate(currentDate, "yyyy-MM-dd"));
		}
		
		redirect("/admin/ListItens.action");
	}

	
}
