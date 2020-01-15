package br.com.javamon.util;

public enum PermissionType {

	ADMIN ("admin"),
	USER ("user"),
	SUPER_ADMIN ("superAdmin");
	
	private String value;
	
	PermissionType( String value ) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
