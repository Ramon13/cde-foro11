package br.com.javamon.exception;

public class FilterException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilterException(Exception e){
		super(e);
	}
	
	public FilterException(Exception e, String msg){
		super(msg, e);
	}
	
	public FilterException(String msg){
		super(msg);
	}
	
}
