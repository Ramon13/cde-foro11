package br.com.javamon.exception;

public class SearchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchException(Exception e){
		super(e);
	}
	
	public SearchException(Exception e, String msg){
		super(msg, e);
	}
	
	public SearchException(String msg){
		super(msg);
	}
	
}
