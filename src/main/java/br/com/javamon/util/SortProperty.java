package br.com.javamon.util;

public enum SortProperty {

	ID ("id"),
	DESCRIPTION ("description"), 
	UNITY("unityType.description"),
	SUBITEM("subitem.description"),
	TYPE ("type.description"),
	AMOUNT ("currentAmount"),
	USER ("login.user"),
	LOCALE ("login.locale.description"),
	DATE ("date");
	
	public static enum ORDER{ASC, DESC};
	
	private String value;
	private ORDER order;
	
	SortProperty( String value ) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public ORDER getOrder() {
		return order;
	}

	public void setOrder(ORDER order) {
		this.order = order;
	}
	
}
