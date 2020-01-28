package br.com.javamon.util;

public enum LoginAction {

	BLOCK("block"),
	RE_ADD_CART("reAddCart");
	
	private String value;
	
	LoginAction( String value ) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
