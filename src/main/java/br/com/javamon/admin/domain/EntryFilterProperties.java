package br.com.javamon.admin.domain;

import java.util.HashMap;
import java.util.List;

import br.com.javamon.service.ProviderService;
import br.com.javamon.service.ServiceFactory;

public class EntryFilterProperties extends FilterProperties {
	
	private List<Long> providerIds;
	
	public EntryFilterProperties() throws Exception{
		super();
		providerIds = ServiceFactory.getInstance().getService(ProviderService.class).getProviderIds();
		initFiltersMap();
	}

	public void initFiltersMap() {
		propertiesOrderMap = new HashMap<>();
		propertiesOrderMap.put(PROPERTIES.ID, "e.id");
		propertiesOrderMap.put(PROPERTIES.DATE, "e.date");
		propertiesOrderMap.put(PROPERTIES.PROVIDER, "e.provider.description");
		propertiesOrderMap.put(PROPERTIES.DOCUMENT_NUMBER, "e.document.number");
		
		
		propertiesIdMap = new HashMap<>();
		propertiesIdMap.put(PROPERTIES.PROVIDER, "e.provider.id");
		
		idListMap = new HashMap<>();
		idListMap.put(PROPERTIES.PROVIDER, providerIds);
	}
	
	public List<Long> getProviderIds() {
		return providerIds;
	}
}
