package br.com.javamon.admin.domain;

import br.com.javamon.exception.PaginationException;

public class PaginationProperties {

	private int page = 1;
	private Long numOfItens;
	
	public final Integer MAX_NUM_OF_ITENS = 50; 
	/**
	 * first value of paged list
	 */
	public final Integer DEFAULT_FIRST_OCURRENCE_VALUE = 0;	
	/**
	 * first value of each pagination list
	 */
	private Integer firstValue;
	/**
	 * last value of each pagination list
	 */
	private Integer lastValue;
	
	public PaginationProperties(){
		this.firstValue = DEFAULT_FIRST_OCURRENCE_VALUE;
		this.lastValue = MAX_NUM_OF_ITENS;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}

	public Integer getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(Integer firstValue) {
		this.firstValue = firstValue;
	}

	public Integer getLastValue() {
		return lastValue;
	}

	public void setLastValue(Integer lastValue) {
		this.lastValue = lastValue;
	}

	public Long getNumOfItens() {
		return numOfItens;
	}

	public void setNumOfItens(Long numOfItens) throws PaginationException{
		try {
			this.numOfItens = numOfItens;
			if(this.lastValue > numOfItens){
				lastValue = numOfItens.intValue();
				page--;
			}
			if( firstValue > numOfItens)
					firstValue = numOfItens.intValue();
		} catch (Exception e) {
			throw new PaginationException(e);
		}
	}
	
	
	
}
