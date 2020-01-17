package br.com.javamon.validation;

import br.com.javamon.entity.EntryItem;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validatior.StringValidator;
import br.com.javamon.validatior.Validator;

public class EntryItemValidation extends Validator{

	private EntryItem entryItem;
	
	public EntryItemValidation(EntryItem entryItem) {
		this.entryItem = entryItem;
	}
	
	public void validate()throws ValidatorException{
		
	}
	
	public void valLengthAttributes() throws ValidatorException{
		
	}
}
