package br.com.javamon.admin.domain;

import java.util.HashMap;
import java.util.List;

import br.com.javamon.service.ItemTypeService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.service.SubitemService;
import br.com.javamon.service.UnityTypeService;

public class ItemFilterProperties extends FilterProperties {
	
	private List<Long> unityTypeIdList;
	private List<Long> subitemIdList;
	private List<Long> itemTypeIdList;
		
	public ItemFilterProperties() throws Exception{
		super();
		ServiceFactory svcFactory = ServiceFactory.getInstance();
		unityTypeIdList = svcFactory.getService(UnityTypeService.class)
				.getUnitypeIds();
		
		subitemIdList = svcFactory.getService(SubitemService.class)
				.getSubitemIds();
		
		itemTypeIdList = svcFactory.getService(ItemTypeService.class)
				.getItemTypeIds();
		
		getSearchProperties().setSearchType(PROPERTIES.DESCRIPTION);
		initFiltersMap();
	}
		
	public void initFiltersMap() {
		propertiesIdMap = new HashMap<>();
		propertiesIdMap.put(PROPERTIES.UNITY_TYPE, "i.unityType.id");
		propertiesIdMap.put(PROPERTIES.SUBITEM, "i.subitem.id");
		propertiesIdMap.put(PROPERTIES.TYPE, "i.type.id");
	
		propertiesOrderMap = new HashMap<>();
		propertiesOrderMap.put(PROPERTIES.ID, "i.id");
		propertiesOrderMap.put(PROPERTIES.DESCRIPTION, "i.description");
		propertiesOrderMap.put(PROPERTIES.UNITY_TYPE, "i.unityType.description");
		propertiesOrderMap.put(PROPERTIES.SUBITEM, "i.subitem.description");
		propertiesOrderMap.put(PROPERTIES.TYPE, "i.type.description");
	
		idListMap = new HashMap<>();
		idListMap.put(PROPERTIES.UNITY_TYPE, unityTypeIdList);
		idListMap.put(PROPERTIES.SUBITEM, subitemIdList);
		idListMap.put(PROPERTIES.TYPE, itemTypeIdList);
	}

	public List<Long> getUnityTypeIdList() {
		return unityTypeIdList;
	}

	public void setUnityTypeIdList(List<Long> unityTypeIdList) {
		this.unityTypeIdList = unityTypeIdList;
	}

	public List<Long> getSubitemIdList() {
		return subitemIdList;
	}

	public void setSubitemIdList(List<Long> subitemIdList) {
		this.subitemIdList = subitemIdList;
	}

	public List<Long> getItemTypeIdList() {
		return itemTypeIdList;
	}

	public void setItemTypeIdList(List<Long> itemTypeIdList) {
		this.itemTypeIdList = itemTypeIdList;
	}
}
