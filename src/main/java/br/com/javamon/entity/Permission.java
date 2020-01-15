package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class Permission {

	private Long id;
	private String description;
	private Set<Login> accounts = new HashSet<>();
	
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
	public Set<Login> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Login> accounts) {
		this.accounts = accounts;
	}
	
}
