package br.com.javamon.action.auth;

import br.com.javamon.action.Action;

public class LogoffAction extends Action{

	@Override
	public void process() throws Exception {
		getRequest().getSession().setAttribute("login", null);
		redirect("/login/login.jsp");
	}

}
