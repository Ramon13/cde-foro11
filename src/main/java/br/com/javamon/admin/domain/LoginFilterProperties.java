package br.com.javamon.admin.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.service.PermissionService;
import br.com.javamon.service.ServiceFactory;

public class LoginFilterProperties extends FilterProperties {
	
	private List<Long> localeIds;
	private List<Long> permissionIds;
	
	
	public LoginFilterProperties() throws Exception{
		super();
		this.localeIds = ServiceFactory.getInstance().getService(LocaleService.class).getLocaleIds();
		this.permissionIds = ServiceFactory.getInstance().getService(PermissionService.class).getPermissionIds();
		initFiltersMap();
	}

	private Map<PROPERTIES, String> propertiesIdMap;
	
	public void initFiltersMap() {
		propertiesIdMap = new HashMap<>();
		propertiesIdMap.put(PROPERTIES.LOCALE, "l.locale.id");
		propertiesIdMap.put(PROPERTIES.PERMISSION, "l.permission.id");
		propertiesIdMap.put(PROPERTIES.TYPE, "i.type.id");
		
		propertiesOrderMap = new HashMap<>();
		propertiesOrderMap.put(PROPERTIES.ID, "l.id");
		propertiesOrderMap.put(PROPERTIES.LOGIN, "l.user");
		propertiesOrderMap.put(PROPERTIES.LOCALE, "l.locale.description");
		propertiesOrderMap.put(PROPERTIES.PERMISSION, "l.permission.description");
		
		idListMap = new HashMap<>();
		idListMap.put(PROPERTIES.LOCALE, localeIds);
		idListMap.put(PROPERTIES.PERMISSION, permissionIds);
	}
	
	public List<Long> getLocaleIds() {
		return localeIds;
	}
	
	public List<Long> getPermissionIds() {
		return permissionIds;
	}
	
	public Map<PROPERTIES, String> getPropertiesIdMap() {
		return propertiesIdMap;
	}
	
}
