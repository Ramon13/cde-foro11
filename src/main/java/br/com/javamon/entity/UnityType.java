package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class UnityType {

	private Long id;
	private String description;
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
	
	
}
