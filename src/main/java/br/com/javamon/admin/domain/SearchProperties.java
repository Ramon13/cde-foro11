package br.com.javamon.admin.domain;

import br.com.javamon.admin.domain.FilterProperties.PROPERTIES;

public class SearchProperties {

	private PROPERTIES searchType;
	private String searchKey;
	
	public SearchProperties(){
		this.searchType = PROPERTIES.ID;
	}

	public PROPERTIES getSearchType() {
		return searchType;
	}

	public void setSearchType(PROPERTIES searchType) {
		this.searchType = searchType;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
