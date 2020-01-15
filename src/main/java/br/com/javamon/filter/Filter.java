package br.com.javamon.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class Filter implements javax.servlet.Filter{

	protected abstract boolean preProcess(ServletRequest req, ServletResponse resp) throws IOException, ServletException;
	protected abstract void posProcess(ServletRequest req, ServletResponse resp) throws IOException, ServletException;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		if(preProcess(request, response))
			return;

		chain.doFilter(request, response);
		
		posProcess(request, response);
	}

}








