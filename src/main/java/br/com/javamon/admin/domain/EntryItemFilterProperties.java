package br.com.javamon.admin.domain;

import java.util.HashMap;

public class EntryItemFilterProperties extends FilterProperties {
	
	
	public EntryItemFilterProperties() throws Exception{
		super();
		initFiltersMap();
	}

	public void initFiltersMap() {
		propertiesOrderMap = new HashMap<>();
		propertiesOrderMap.put(PROPERTIES.ID, "ei.id");
		propertiesOrderMap.put(PROPERTIES.DATE, "ei.entry.date");
		propertiesOrderMap.put(PROPERTIES.AMOUNT, "ei.amount");
		propertiesOrderMap.put(PROPERTIES.UNITY_VALUE, "ei.unityValue");
		propertiesOrderMap.put(PROPERTIES.TOTAL, "ei.total");
		propertiesOrderMap.put(PROPERTIES.PROVIDER, "ei.entry.provider.description");
		propertiesOrderMap.put(PROPERTIES.DOCUMENT_NUMBER, "ei.entry.document.number");
		
		
		propertiesIdMap = new HashMap<>();
		propertiesIdMap.put(PROPERTIES.PROVIDER, "ei.entry.provider.id");
	}
}
