package br.com.javamon.action.admin.provider;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.javamon.action.Action;
import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import convert.ConvertUtil;
import entity.Document;
import entity.Entry;
import entity.Item;
import entity.Provider;
import service.EntryService;
import service.ItemService;
import service.ProviderService;

public class AddEntries extends AdminAction<FilterProperties> {

	public AddEntries() {
		super(FilterProperties.class);
	}
	
	@Override
	protected void processAction() throws Exception {
		System.out.println(this.getClass().getCanonicalName());
		System.out.println(getRequest().getParameter("date"));
//
//		String save = getRequest().getParameter("save");
//		String async = getRequest().getParameter("async");
//		
//		
//		if(!StringUtils.isAllBlank(save)){
//			try {
//				Entry[] createEntries = createEntries();
//				for(Entry entry : createEntries){
//					Long itemId = entry.getItem().getId();
//				
//					serviceFactory.getService(EntryService.class).saveNewEntry(entry, itemId);			
//				}
//				
//				getRequest().setAttribute("success", "Sucesso. foram adicionadas " + createEntries.length + " novas entradas.");
//				sendToBathEntriesPage();
//				return;
//			} catch (Exception e) {
//				getRequest().setAttribute("errorMsg", "Erro. 1 ou mais campos não foram preenchidos corretamente.");
//				sendToBathEntriesPage();
//				e.printStackTrace();
//				return;
//			}
//		}
//			
//		if(!StringUtils.isBlank(async)){
//			List<Item> itemList = serviceFactory.getService(ItemService.class).listAll();
//			getRequest().setAttribute("itemList", itemList);
//			foward("/jsp/admin/item/entry/ajax/ajax-batch-table-row.jsp");
//			return;
//		}
//		
//		sendToBathEntriesPage();
//		
	}

	private void sendToBathEntriesPage() throws Exception{
		List<Provider> providerList = serviceFactory.getService(ProviderService.class).listAll();
		List<Item> itemList = serviceFactory.getService(ItemService.class).listAll();
		
		getRequest().setAttribute("providerList", providerList);
		getRequest().setAttribute("currentDate", LocalDate.now());
		getRequest().setAttribute("itemList", itemList);
		
		foward("/jsp/admin/item/entry/batch_entry.jsp");
	}
	
	private Entry[] createEntries()throws Exception{
		AddEntryAction addEntryAction = new AddEntryAction();
		
		String[] strAmountArr = getRequest().getParameterValues("amount");
		String[] strUnitaryValueArr = getRequest().getParameterValues("unitaryValue");
		String[] strDocumentNumberArr = getRequest().getParameterValues("documentNumber");
		String[] strItemIdsArr = getRequest().getParameterValues("itemId");
		
		String strProviderId = getRequest().getParameter("providerId");
		Provider provider = serviceFactory.getService(ProviderService.class).load(strProviderId);
		
		
		//valida e em seguida converte a data
		addEntryAction.validateDateParam(getRequest().getParameter("date"));
		LocalDate date = ConvertUtil.stringToLocalDate(getRequest().getParameter("date"));
		
		Entry[] entries = new Entry[strAmountArr.length];
		
		for(int i = 0 ; i < strAmountArr.length; i++){
			
			Long amount = addEntryAction.validateAmountParam(strAmountArr[i]);
			
			Double unitaryValue = addEntryAction.validateUnitaryValueParam( strUnitaryValueArr[i] );
			String documentNumber = strDocumentNumberArr[i];
			
			Item item = new Item();
			item.setId(ConvertUtil.stringToLong(strItemIdsArr[i]));
			
			Document document = new Document();
			document.setNumber(documentNumber);
			
			Entry entry = new Entry();
			entry.setDate(date);
			entry.setAmount(amount);
			entry.setUnityValue(unitaryValue);
			entry.setDocument(document);
			entry.setProvider(provider);
			entry.setItem(item);
			
			entries[i] = entry;
		}
		
		return entries;
	}
}
