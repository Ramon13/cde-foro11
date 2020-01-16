package br.com.javamon.action.admin.item;

import java.util.List;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.EntryItemFilterProperties;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.entity.EntryItem;
import br.com.javamon.service.EntryItemService;
import br.com.javamon.validation.RequestParameterValidation;

public class ListEntryItens extends AdminAction<EntryItemFilterProperties>{

	public ListEntryItens() {
		super(EntryItemFilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		EntryItemService entryItemSvc = getServiceFactory().getService(EntryItemService.class);
		
		updateSearchListStats(getRequest(), getSessionFilterProperties("entryItemFilterProperties"));
		FilterProperties entryFilterProperties = updateFilterListStats(getRequest(), getSessionFilterProperties("entryItemFilterProperties"));
		
		String itemId = getRequest().getParameter("itemId");
		String entryId = getRequest().getParameter("entryId");
		System.out.println(entryId);
		if(!RequestParameterValidation.validateStringParam(itemId, 32)){
			
			Long countEntries = entryItemSvc.countEntryItensByItem(Long.parseLong(itemId), entryFilterProperties);
			PaginationProperties paginationProperties = updatePaginationStats(getRequest(), countEntries);
			
			List<EntryItem> entryItens = entryItemSvc.listEntryItensByItem(StringConvert.stringToLong(itemId),
					entryFilterProperties, paginationProperties);
			updateFilterListStats(getRequest(), entryFilterProperties);
			
			
			getRequest().setAttribute("entryItens", entryItens);
			getRequest().setAttribute("itemId", itemId);
			getRequest().setAttribute("numOfItens", countEntries);
				
		}else
			if(!RequestParameterValidation.validateStringParam(entryId, 32)){
				Long countEntries = entryItemSvc.countEntryItensByEntry(Long.parseLong(entryId), entryFilterProperties);
				PaginationProperties paginationProperties = updatePaginationStats(getRequest(), countEntries);
				
				List<EntryItem> entryItens = entryItemSvc.listEntryItensByEntry(StringConvert.stringToLong(entryId),
						entryFilterProperties, paginationProperties);
				updateFilterListStats(getRequest(), entryFilterProperties);
				
				
				getRequest().setAttribute("entryItens", entryItens);
				getRequest().setAttribute("entryId", entryId);
				getRequest().setAttribute("numOfItens", countEntries);
			}
		
		foward("/admin/jsp/ajax/item_entries.jsp");

	}
}