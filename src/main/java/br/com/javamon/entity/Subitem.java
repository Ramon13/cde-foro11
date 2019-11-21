package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class Subitem{

	private Long id;
	private Integer description;
	private SortedSet<ItemType> itemTypes;
	private Set<Item> itens = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SortedSet<ItemType> getItemTypes() {
		return itemTypes;
	}
	public void setItemTypes(SortedSet<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}
	public Set<Item> getItens() {
		return itens;
	}
	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	public Integer getDescription() {
		return description;
	}
	public void setDescription(Integer description) {
		this.description = description;
	}
	
}
