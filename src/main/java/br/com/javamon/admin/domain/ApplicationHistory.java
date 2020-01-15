package br.com.javamon.admin.domain;

import java.util.Stack;

public class ApplicationHistory {

	private Stack<History> historyStack;
	
	public ApplicationHistory(){
		this.historyStack = new Stack<>();
	}
	
	public Stack<History> getHistoryStack() {
		return historyStack;
	}
	
	public History peek(){
		return this.historyStack.peek();
	}
	
	public void add(History history){
		if(historyStack.size() >= 5)
			historyStack.remove(0);
		historyStack.add(history);
	}
}
