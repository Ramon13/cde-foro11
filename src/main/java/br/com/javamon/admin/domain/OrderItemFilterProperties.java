package br.com.javamon.admin.domain;

import java.util.HashMap;

public class OrderItemFilterProperties extends FilterProperties {

	public OrderItemFilterProperties() throws Exception{
		super();
		setProperty(PROPERTIES.DATE);
		setOrder(ORDER.DESC);
		initFiltersMap();
	}
	
	public void initFiltersMap() {
		propertiesOrderMap = new HashMap<>();
		propertiesOrderMap.put(PROPERTIES.ID, "oi.id");
		propertiesOrderMap.put(PROPERTIES.DATE, "oi.order.date");
		propertiesOrderMap.put(PROPERTIES.AMOUNT, "oi.amount");
		propertiesOrderMap.put(PROPERTIES.LOCALE, "oi.order.login.locale.description");
		propertiesOrderMap.put(PROPERTIES.DESCRIPTION, "oi.item.description");
		propertiesOrderMap.put(PROPERTIES.ORDER, "oi.order.id");
	}

	
}
