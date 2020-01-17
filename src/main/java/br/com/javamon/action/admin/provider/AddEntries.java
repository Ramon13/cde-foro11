package br.com.javamon.action.admin.provider;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validatior.StringValidator;

public class AddEntries extends AdminAction<FilterProperties> {

	public AddEntries() {
		super(FilterProperties.class);
	}
	
	@Override
	protected void processAction() throws Exception {
		saveEntry();
	}

	private void saveEntry() throws ValidatorException{
		createEntries();
		
	}

	
	private void createEntries() throws ValidatorException{
		String[] amountArr = getRequest().getParameterValues("amount");
		String[] unitaryValueArr = getRequest().getParameterValues("unitaryValue");
		String documentNumber = getRequest().getParameter("documentNumber");
		String[] strItemIdsArr = getRequest().getParameterValues("itemId");
		
		String strProviderId = getRequest().getParameter("providerId");
		
		validateParams(amountArr);
		validateParams(unitaryValueArr);
		validateParams(strItemIdsArr);
		validateParams(documentNumber);
		validateParams(strProviderId);
		
//		Provider provider = serviceFactory.getService(ProviderService.class).load(strProviderId);
//		
//		
//		//valida e em seguida converte a data
//		addEntryAction.validateDateParam(getRequest().getParameter("date"));
//		LocalDate date = ConvertUtil.stringToLocalDate(getRequest().getParameter("date"));
//		
//		Entry[] entries = new Entry[strAmountArr.length];
//		
//		for(int i = 0 ; i < strAmountArr.length; i++){
//			
//			Long amount = addEntryAction.validateAmountParam(strAmountArr[i]);
//			
//			Double unitaryValue = addEntryAction.validateUnitaryValueParam( strUnitaryValueArr[i] );
//			String documentNumber = strDocumentNumberArr[i];
//			
//			Item item = new Item();
//			item.setId(ConvertUtil.stringToLong(strItemIdsArr[i]));
//			
//			Document document = new Document();
//			document.setNumber(documentNumber);
//			
//			Entry entry = new Entry();
//			entry.setDate(date);
//			entry.setAmount(amount);
//			entry.setUnityValue(unitaryValue);
//			entry.setDocument(document);
//			entry.setProvider(provider);
//			entry.setItem(item);
//			
//			entries[i] = entry;
//		}
//		
//		return entries;
	}
	
	private void validateParams(String ... strArr)throws ValidatorException{
		if(!StringValidator.isValidLen(32, strArr) | StringValidator.isEmpty(strArr))
			throw new ValidatorException("Os valores fornecidos são incosistentes ou inválidos.");
	}
}
