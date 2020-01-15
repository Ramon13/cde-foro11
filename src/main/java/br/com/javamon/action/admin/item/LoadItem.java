package br.com.javamon.action.admin.item;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.StringConvert;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.ItemTypeService;
import br.com.javamon.service.SubitemService;
import br.com.javamon.service.UnityTypeService;

public class LoadItem extends AdminAction<FilterProperties> {

	public LoadItem() {
		super(FilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		ItemService itemSvc = getServiceFactory().getService(ItemService.class);
		UnityTypeService untTypeSvc = getServiceFactory().getService(UnityTypeService.class);
		SubitemService subitemSvc = getServiceFactory().getService(SubitemService.class);
		ItemTypeService typeSvc = getServiceFactory().getService(ItemTypeService.class);
		
		
		String itemId = getRequest().getParameter("itemId");
		if(!StringUtils.isBlank(itemId)){
			
			getRequest().setAttribute("item", itemSvc.load(StringConvert.stringToLong(itemId)));
			getRequest().setAttribute("unityTypes", untTypeSvc.list());
			getRequest().setAttribute("subitens", subitemSvc.list());
			getRequest().setAttribute("types", typeSvc.list());
			
			
			foward("/admin/jsp/ajax/item.jsp");
			return;
		}
			
		redirect("/admin/jsp/ajax/item.jsp");
	}

}
