package br.com.javamon.admin.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.service.LoginService;
import br.com.javamon.service.OrderService.ORDER_TYPE;
import br.com.javamon.service.ServiceFactory;

public class OrderFilterProperties extends FilterProperties {
	
	private ORDER_TYPE orderType;
	private List<Long> loginIdList;
	private List<Long> localeIdList;
	
	public OrderFilterProperties() throws Exception{
		super();
		ServiceFactory srvFactory = ServiceFactory.getInstance();
		this.loginIdList = srvFactory.getService(LoginService.class).getLoginIds();
		this.localeIdList = srvFactory.getService(LocaleService.class).getLocaleIds();
		orderType = ORDER_TYPE.PENDING;
		setProperty(PROPERTIES.DATE);
		setOrder(ORDER.DESC);
		
		initFiltersMap();
	}
	
	private Map<PROPERTIES, String> propertiesIdMap;
	
	public void initFiltersMap() {
		propertiesIdMap = new HashMap<>();
		propertiesIdMap.put(PROPERTIES.LOGIN, "o.login.id");
		propertiesIdMap.put(PROPERTIES.LOCALE, "o.login.locale.id");
		
		propertiesOrderMap = new HashMap<>();
		propertiesOrderMap.put(PROPERTIES.ID, "o.id");
		propertiesOrderMap.put(PROPERTIES.DATE, "o.date");
		propertiesOrderMap.put(PROPERTIES.STATUS, "o.status");
		propertiesOrderMap.put(PROPERTIES.LOGIN, "o.login.user");
		propertiesOrderMap.put(PROPERTIES.LOCALE, "o.login.locale.description");
		
		idListMap = new HashMap<>();
		idListMap.put(PROPERTIES.LOGIN, loginIdList);
		idListMap.put(PROPERTIES.LOCALE, localeIdList);
	}

	public ORDER_TYPE getOrderType() {
		return orderType;
	}

	public void setOrderType(ORDER_TYPE orderType) {
		this.orderType = orderType;
	}

	public List<Long> getLoginIdList() {
		return loginIdList;
	}

	public void setLoginIdList(List<Long> loginIdList) {
		this.loginIdList = loginIdList;
	}

	public List<Long> getLocaleIdList() {
		return localeIdList;
	}

	public void setLocaleIdList(List<Long> localeIdList) {
		this.localeIdList = localeIdList;
	}

	public Map<PROPERTIES, String> getPropertiesIdMap() {
		return propertiesIdMap;
	}

	public void setPropertiesIdMap(Map<PROPERTIES, String> propertiesIdMap) {
		this.propertiesIdMap = propertiesIdMap;
	}
}