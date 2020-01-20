package br.com.javamon.action.admin.entry;

import java.time.LocalDate;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.convert.NumberConvert;
import br.com.javamon.entity.Document;
import br.com.javamon.entity.Entry;
import br.com.javamon.entity.EntryItem;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.DocumentService;
import br.com.javamon.service.EntryItemService;
import br.com.javamon.service.EntryService;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.ProviderService;
import br.com.javamon.validation.DocumentValidation;
import br.com.javamon.validation.ItemValidation;
import br.com.javamon.validation.ProviderValidation;
import br.com.javamon.validatior.StringValidator;

public class AddEntries extends AdminAction<FilterProperties> {

	public AddEntries() {
		super(FilterProperties.class);
	}
	
	@Override
	protected void processAction() throws Exception {
		saveEntry();
	}

	private void saveEntry() throws ValidatorException, ServiceException{
		Entry entry = createEntry();
		
		EntryItemService entryItemSvc = getServiceFactory().getService(EntryItemService.class);
		for(EntryItem ei : entry.getEntryItens()){
			entryItemSvc.save(ei);
		}
		
		getServiceFactory().getService(EntryService.class).save(entry);
	}

	
	private Entry  createEntry() throws ValidatorException, ServiceException{
		try {
			ItemService itemSvc = getServiceFactory().getService(ItemService.class);
			DocumentService documentSvc = getServiceFactory().getService(DocumentService.class);
			ProviderService providerSvc = getServiceFactory().getService(ProviderService.class);
			
			String[] strAmountArr = getRequest().getParameterValues("amount");
			String[] strUnitaryValueArr = getRequest().getParameterValues("unitaryValue");
			String documentNumber = getRequest().getParameter("documentNumber");
			String[] strItemIdsArr = getRequest().getParameterValues("itemId");
			String strProviderId = getRequest().getParameter("providerId");
			String strDate = getRequest().getParameter("date");
			
			validateParams(strAmountArr);
			validateParams(strUnitaryValueArr);
			validateParams(documentNumber);
			validateIds(strItemIdsArr);
			validateIds(strProviderId);
			validateParams(strDate);
			
			
			Long[] amountArr = NumberConvert.stringToLong(strAmountArr);
			Double[] unitaryValueArr = NumberConvert.stringToDouble(strUnitaryValueArr);
			Long[] itemIdsArr = NumberConvert.stringToLong(strItemIdsArr);
			Long providerId = NumberConvert.stringToLong(strProviderId);
			LocalDate date = LocalDate.parse(strDate);
			
			validateIfProviderExists(providerId);
			validateIfDocumentDescriptionExists(documentNumber);
			
			Document doc = new Document();
			doc.setNumber(documentNumber);
			documentSvc.save(doc);
			
			Entry entry = new Entry();
			entry.setDate(date);
			entry.setDocument(doc);
			entry.setProvider(providerSvc.load(providerId));
			
			EntryItem entryItem;
			for (int i = 0 ; i < itemIdsArr.length ; i++){
				validateIfItemExists(itemIdsArr[i]);
				
				entryItem = new EntryItem();
				entryItem.setItem(itemSvc.load(itemIdsArr[i]));
				entryItem.setAmount(amountArr[i]);
				entryItem.setUnityValue(unitaryValueArr[i]);
				entryItem.setTotal(amountArr[i] * unitaryValueArr[i]);
				entryItem.setEntry(entry);
				entry.getEntryItens().add(entryItem);
			}
			
			return entry;
		} catch (ValidatorException e) {
			throw new ValidatorException("Os valores fornecidos são incosistentes ou inválidos." + e.getMessage());
		}
	}
	
	private void validateIds(String ... strArr) throws ValidatorException{
		for(String s : strArr){
			if(!StringValidator.isValidLen(32, s) | StringValidator.isEmpty(s) | StringValidator.containsZeros(s))
				throw new ValidatorException();
		}
	}
	
	private void validateParams(String ... strArr) throws ValidatorException{
		for(String s : strArr){
			if(!StringValidator.isValidLen(32, s) | StringValidator.isEmpty(s))
				throw new ValidatorException();
		}
	}
	
	private void validateIfItemExists(Long itemId) throws ValidatorException{
		if(!ItemValidation.isIdExists(itemId))
			throw new ValidatorException();
	}
	
	private void validateIfProviderExists(Long providerId) throws ValidatorException{
		if(!ProviderValidation.isIdExists(providerId))
			throw new ValidatorException();
	}
	
	private void validateIfDocumentDescriptionExists(String docNumber) throws ValidatorException{
		if(DocumentValidation.isDocumentDescriptionExists(docNumber))
			throw new ValidatorException("O número do documento foi cadastrado em outro evento.");
	}
}
