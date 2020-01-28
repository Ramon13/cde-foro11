package br.com.javamon.validation;

import br.com.javamon.entity.Item;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.ItemService;
import br.com.javamon.service.ServiceFactory;
import br.com.javamon.validatior.Validator;

public class ItemValidation extends Validator{

	private Item item;
	private static ItemService itemSvc;
	
	public ItemValidation(Item item){
		this.item = item;
		try {
			itemSvc = ServiceFactory.getInstance().getService(ItemService.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isIdExists(Long id) throws ValidatorException{
		try {
			return ServiceFactory.getInstance().getService(ItemService.class).load(id) != null;
		} catch (ServiceException e) {
			throw new ValidatorException();
		}
	}
	
	
}