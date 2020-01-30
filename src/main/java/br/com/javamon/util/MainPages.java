package br.com.javamon.util;

public enum MainPages {

	LIST_ITENS("ListItens.action"),
	LIST_LOGINS("ListLogins.action"),
	LIST_ENTRIES("ListEntries.action"),
	LIST_ORDERS("ListOrders.action"),
	LOAD_ITEM("LoadItem.action");
	
	private String value;
	
	MainPages( String value ) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
