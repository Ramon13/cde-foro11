package br.com.javamon.action.admin.item;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.ItemFilterProperties;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.entity.Item;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.ItemTypeService;
import br.com.javamon.service.SubitemService;
import br.com.javamon.service.UnityTypeService;

/**
 * This class loads and configure through services all contents of homepage
 * Updates the number of itens per page
 * Loads itens from database and put them on request
 * 
 * @author Ramon
 * @version 1.0
 *
 */
public class ListItens extends AdminAction<ItemFilterProperties>{
		
	public ListItens() {
		super(ItemFilterProperties.class);
	}

	@Override
	public void processAction() throws Exception {
		
			ItemService itemService = getServiceFactory()
					.getService(ItemService.class);
			
			FilterProperties itemFilterProperty = updateFilterListStats(getRequest(), getSessionFilterProperties("itemFilterProperties"));
			updateSearchListStats(getRequest(), itemFilterProperty);
			//identify if the user change the page and change the attributes on user session
			Long numOfItens = itemService.countNumOfItens(itemFilterProperty);
			PaginationProperties paginationProp = updatePaginationStats(getRequest(), numOfItens);
			
			//Search a list of Itens in database based on page and filters applied
			List<Item> itens = itemService.listItens(paginationProp, itemFilterProperty);
			
			setListLayout();
			getRequest().setAttribute("itens", itens);
			getRequest().setAttribute("locales", getServiceFactory().getService(LocaleService.class).listLocales());
			getRequest().setAttribute("numOfItens", numOfItens);
			getRequest().setAttribute("unityTypeList", getServiceFactory().getService(UnityTypeService.class).list());
			getRequest().setAttribute("subitemList", getServiceFactory().getService(SubitemService.class).list());
			getRequest().setAttribute("itemTypeList", getServiceFactory().getService(ItemTypeService.class).list());
						
			foward("/admin/jsp/ajax/itens_list.jsp");
	}
	
	private void setListLayout(){
		HttpSession usrSession = getRequest().getSession();
		String selectedLayout = getRequest().getParameter("listLayout");
		
		if(!StringUtils.isBlank(selectedLayout)){
			if(selectedLayout.equals("grid"))
				usrSession.setAttribute("listLayout", "grid");
			else
				usrSession.setAttribute("listLayout", "list");
		
		}else{
			if(StringUtils.isBlank( (String) usrSession.getAttribute("listLayout")))
				usrSession.setAttribute("listLayout", "list");
			
		}
	}
}
