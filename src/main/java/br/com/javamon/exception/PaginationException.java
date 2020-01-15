package br.com.javamon.exception;

public class PaginationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaginationException(Exception e){
		super(e);
	}
	
	public PaginationException(Exception e, String msg){
		super(msg, e);
	}
	
	public PaginationException(String msg){
		super(msg);
	}
	
}
