package br.com.javamon.admin.domain;

public enum PageAction {
	
	NEXT("next"),
	PREVIOUS("previous");
	
	public final String value;
	
	private PageAction(String value){
		this.value = value;
	}
}
