package br.com.javamon.admin.domain;

import java.util.List;

import br.com.javamon.entity.Item;
import br.com.javamon.entity.Locale;
import br.com.javamon.util.SortProperty;

public class ItemPaginate {

	private List<Item> itens;
	private int numPages;
	private SortProperty sortProperty;
	private List<Locale> locales;
	private List<Integer> years;
	
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public int getNumPages() {
		return numPages;
	}
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	public List<Locale> getLocales() {
		return locales;
	}
	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}
	public List<Integer> getYears() {
		return years;
	}
	public void setYears(List<Integer> years) {
		this.years = years;
	}
	public SortProperty getSortProperty() {
		return sortProperty;
	}
	public void setSortProperty(SortProperty sortProperty) {
		this.sortProperty = sortProperty;
	}
	
	
	
}
