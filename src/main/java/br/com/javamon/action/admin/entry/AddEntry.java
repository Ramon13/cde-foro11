package br.com.javamon.action.admin.entry;

import br.com.javamon.action.Action;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.ProviderService;

public class AddEntry extends Action{

	@Override
	public void process() throws Exception {
		
		ProviderService providerSvc = getServiceFactory().getService(ProviderService.class);
		ItemService itemSvc = getServiceFactory().getService(ItemService.class);
		
		getRequest().setAttribute("items", itemSvc.listItens());
		getRequest().setAttribute("providers", providerSvc.list());
		foward("/admin/jsp/ajax/new_entry.jsp");
	}

}
