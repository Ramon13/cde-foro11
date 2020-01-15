package br.com.javamon.action.admin.page;

import java.util.Map;
import java.util.Stack;

import br.com.javamon.action.Action;
import br.com.javamon.admin.domain.ApplicationHistory;
import br.com.javamon.admin.domain.History;

public class ReturnPage extends Action{

	@Override
	public void process() throws Exception {
		ApplicationHistory applicationHistory = (ApplicationHistory) getRequest().getSession().getAttribute("history");
		Stack<History> hStack = applicationHistory.getHistoryStack();
		printStack(hStack);
		
		if(hStack.size() > 0){
			History topStackPage = hStack.pop();
			topStackPage = hStack.pop();
			
			Map<String, String[]> parameterMaps = topStackPage.getParameterMaps();
			for(String key : parameterMaps.keySet()){
				getRequest().setAttribute(key, parameterMaps.get(key));
			}
			redirect(topStackPage.getUrl());
		}
	}
	
	private void printStack(Stack<History> stack){
		for(History h : stack){
			System.out.println(h.getUrl());
		}
		
	}
	

}
