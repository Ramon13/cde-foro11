package br.com.javamon.action.common.item;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import entity.Item;
import service.ItemService;

public class ItemMarket extends Action{

	@Override
	protected void process() throws Exception {
		String strItemId = getRequest().getParameter("itemId");
		if(!StringUtils.isBlank(strItemId)) {
			Item item = serviceFactory.getService(ItemService.class).loadItem(strItemId);
			
			getRequest().setAttribute("item", item);
			foward("/jsp/common/item/info.jsp");     
		}else {
			redirect("list_item.action");
		}
	}

}
