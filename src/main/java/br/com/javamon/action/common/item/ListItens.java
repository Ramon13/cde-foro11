package br.com.javamon.action.common.item;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import br.com.javamon.admin.domain.ItemPaginate;
import br.com.javamon.entity.ItemType;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.ItemTypeService;
/**
 * Action to list all itens filtered by type.
 * @version 1.0
 * @author ramoncosta
 *
 */
public class ListItens extends Action{

	@Override
	public void process() throws Exception {
		String strItemTypeId = getRequest().getParameter( "typeId" );
		String strAsync = getRequest().getParameter( "async" );
		String strPageNum = getRequest().getParameter( "pageNum" );
		String itemSearch = getRequest().getParameter( "itemSearch" );
		String fowardUrl = "/common/jsp/home.jsp";
		
		List<ItemType> typeList = getServiceFactory().getService(ItemTypeService.class).list();
		ItemPaginate itemPaginate = null;
		getRequest().setAttribute( "typeList" , typeList);
		
		if(!StringUtils.isBlank(strAsync)){
			if(!StringUtils.isBlank(itemSearch)){
				itemPaginate = getServiceFactory().getService(ItemService.class).searchItens( itemSearch, strPageNum );
				fowardUrl = "/common/jsp/async/async-home.jsp";
				
			}else if( !StringUtils.isBlank(strItemTypeId) ) {
			
				long itemTypeId = Long.parseLong(strItemTypeId);
				itemPaginate = getServiceFactory().getService(ItemService.class).listItensByType(itemTypeId, strPageNum);
				
				fowardUrl = "/common/jsp/async/async-home.jsp";
			
			}else if(strAsync != null) {
				itemPaginate = getServiceFactory().getService(ItemService.class).listAllItens(strPageNum);
				fowardUrl = "/common/jsp/async/async-home.jsp";
			}
		
			getRequest().setAttribute("numPages", itemPaginate.getNumPages());
			getRequest().setAttribute("itemList", itemPaginate.getItens());
			getRequest().setAttribute("typeId", strItemTypeId);
			foward(fowardUrl);
			
		}else{
			foward(fowardUrl);
		}
	}
}
