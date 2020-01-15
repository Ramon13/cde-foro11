package br.com.javamon.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.javamon.entity.Login;

@WebFilter( urlPatterns = {"/admin/*"})
public class AuthFilter extends Filter{

	@Override
	protected boolean preProcess(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Login login = (Login) req.getSession().getAttribute("login");
				
		if(login == null){
			resp.sendRedirect(req.getContextPath() + "/login/login.jsp");
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void posProcess(ServletRequest request, ServletResponse response) throws IOException, ServletException {}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
