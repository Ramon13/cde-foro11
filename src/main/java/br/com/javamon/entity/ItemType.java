package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class ItemType implements Comparable<ItemType> {

	private Long id;
	private String description;
	private Subitem subitem;
	private Set<Item> itens = new HashSet<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Item> getItens() {
		return itens;
	}
	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	public Subitem getSubitem() {
		return subitem;
	}
	public void setSubitem(Subitem subitem) {
		this.subitem = subitem;
	}
	@Override
	public int compareTo(ItemType o) {
		if(this.id == o.id)
			return 0;
		if(this.id > o.id)
			return 1;
		return -1;
	}
	
}
