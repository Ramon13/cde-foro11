package br.com.javamon.validation;


import org.apache.commons.lang3.StringUtils;

import br.com.javamon.convert.StringConvert;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.validatior.Validator;

public class RequestParameterValidation extends Validator {

	public static boolean isNull(String parameter) throws ValidatorException{
		return (parameter == null) ? true : false;
	}
	
	public static boolean isEmpty(String parameter) throws ValidatorException{
		return StringUtils.isBlank(parameter);
	}
	
	private static void validateStringLen(String param, int maxLen) throws ValidatorException{
		if(param.length() > maxLen)
			throw new ValidatorException(new StringIndexOutOfBoundsException("the string lenght exceeds the maximun size"));
	}
	
	public static boolean validateStringParam(String param, int maxLen) throws ValidatorException{
		if(isEmpty(param))
			return true;
		
		validateStringLen(param, maxLen);
		return false;
	}
	
	public static boolean validateLongParam(String longParam, int maxLen) throws ValidatorException{
		if(isEmpty(longParam))
			return false;
		
		validateStringLen(longParam, maxLen);
		try {
			validateStringConversion(longParam);
		} catch (ConvertException e) {
			throw new ValidatorException("the string cannot be converted to a number", e);
		}
		
		return true;
	}
	
	private static void validateStringConversion(String str) throws ConvertException{
		StringConvert.stringToLong(str);
	}
}
