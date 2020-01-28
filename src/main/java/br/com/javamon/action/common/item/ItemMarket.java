package br.com.javamon.action.common.item;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import br.com.javamon.entity.Item;
import br.com.javamon.service.ItemService;

public class ItemMarket extends Action{

	@Override
	public void process() throws Exception {
		String strItemId = getRequest().getParameter("itemId");
		if(!StringUtils.isBlank(strItemId)) {
			Item item = getServiceFactory().getService(ItemService.class).load(Long.parseLong(strItemId));
			
			getRequest().setAttribute("item", item);
			foward("/common/jsp/item/info.jsp");     
		}else {
			redirect("/common/list_item.action");
		}
	}

}
