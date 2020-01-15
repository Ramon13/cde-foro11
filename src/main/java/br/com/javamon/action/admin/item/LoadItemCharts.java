package br.com.javamon.action.admin.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.Locale;
import br.com.javamon.entity.OrderItem;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.OrderService;

public class LoadItemCharts extends Action{

	@Override
	public void process() throws Exception {
		String chartYear = (String) getRequest().getParameter("chartYear");
		if(StringUtils.isBlank(chartYear))
			getRequest().getSession().setAttribute("chartYear", "2020");
		else
			getRequest().getSession().setAttribute("chartYear", chartYear);
		
		Long itemId = StringConvert.stringToLong(getRequest().getParameter("itemId"));
		
		LocaleService localeSvc = getServiceFactory().getService(LocaleService.class);
		OrderService orderSvc = getServiceFactory().getService(OrderService.class);
		ItemService itemSvc = getServiceFactory().getService(ItemService.class);
		
		
		List<Map<Locale, List<OrderItem>>> ordersLocales = new ArrayList<Map<Locale,List<OrderItem>>>();
		Map<Locale, List<OrderItem>> map = new HashMap<Locale, List<OrderItem>>();
		ordersLocales.add(map);
		
		List<Locale> locales = localeSvc.listLocales();
		List<OrderItem> orders = null;
		Item item = itemSvc.load(itemId);
		
		for(Locale locale : locales){
			orders = orderSvc.listOrdersByLocale(locale, item);
			map.put(locale, orders);
		}
		
		getRequest().setAttribute("ordersLocales", ordersLocales);
		getRequest().setAttribute("item", item);
		
		foward("/admin/jsp/ajax/item_charts.jsp");
	}

}
