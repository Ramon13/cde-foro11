package br.com.javamon.admin.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class FilterProperties {
	
	private PROPERTIES property;
	private List<PROPERTIES> properties;
	private SearchProperties searchProperties;
	private ORDER order = ORDER.ASC;
	
	protected Map<PROPERTIES, String> propertiesOrderMap;
	protected Map<PROPERTIES, List<Long>> idListMap;
	protected Map<PROPERTIES, String> propertiesIdMap;
	
	
	public FilterProperties(){
		property = PROPERTIES.ID;
		searchProperties = new SearchProperties();
		
		properties = Arrays.asList(PROPERTIES.values());
	}
	
	public enum ORDER {
		ASC, 
		DESC
	};
	
	public enum PROPERTIES {
		ID,
		DESCRIPTION,
		UNITY_TYPE,
		SUBITEM,
		TYPE,
		SEARCH_DESCRIPTION,
		SEARCH_SPECIFICATION,
		DATE,
		LOGIN,
		LOCALE,
		STATUS,
		AMOUNT,
		UNITY_VALUE,
		TOTAL,
		PERMISSION,
		ORDER,
		PROVIDER,
		DOCUMENT_NUMBER
	}

	public PROPERTIES getProperty() {
		return property;
	}

	public void setProperty(PROPERTIES property) {
		this.property = property;
	}

	public List<PROPERTIES> getProperties() {
		return properties;
	}

	public void setProperties(List<PROPERTIES> properties) {
		this.properties = properties;
	}

	public SearchProperties getSearchProperties() {
		return searchProperties;
	}

	public void setSearchProperties(SearchProperties searchProperties) {
		this.searchProperties = searchProperties;
	}

	public ORDER getOrder() {
		return order;
	}

	public void setOrder(ORDER order) {
		this.order = order;
	}

	public Map<PROPERTIES, String> getPropertiesOrderMap() {
		return propertiesOrderMap;
	}

	public void setPropertiesOrderMap(Map<PROPERTIES, String> propertiesOrderMap) {
		this.propertiesOrderMap = propertiesOrderMap;
	};
	
	public Map<PROPERTIES, List<Long>> getIdListMap() {
		return idListMap;
	}
	
	public Map<PROPERTIES, String> getPropertiesIdMap() {
		return propertiesIdMap;
	}
}
