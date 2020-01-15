package br.com.javamon.action.admin.entry;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.EntryFilterProperties;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Entry;
import br.com.javamon.service.EntryService;
import br.com.javamon.service.ProviderService;

public class ListEntries extends AdminAction<EntryFilterProperties>{

	public ListEntries() {
		super(EntryFilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		EntryService entrySvc = getServiceFactory().getService(EntryService.class);
		
		updateSearchListStats(getRequest(), getSessionFilterProperties("entryFilterProperties"));
		FilterProperties entryFilterProperties = updateFilterListStats(getRequest(), getSessionFilterProperties("entryFilterProperties"));
			
		Long countEntries = entrySvc.countEntries(entryFilterProperties);
		PaginationProperties paginationProperties = updatePaginationStats(getRequest(), countEntries);
			
		java.util.List<Entry> entries = entrySvc.listEntries(entryFilterProperties, paginationProperties);
		updateFilterListStats(getRequest(), entryFilterProperties);
		
		getRequest().setAttribute("entries", entries);
		getRequest().setAttribute("numOfItens", countEntries);
		getRequest().setAttribute("providerList", getServiceFactory().getService(ProviderService.class).list());
		foward("/admin/jsp/ajax/entries.jsp");
		
	}
}