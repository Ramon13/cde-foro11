package br.com.javamon.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Entry{
	
	private Long id;
	@Deprecated
	private Long amount = 0L;
	@Deprecated
	private Double unityValue = 0D;
	@Deprecated
	private Item item;
	@Deprecated
	private Double total = 0D;
	private LocalDate date;
	private Document document;
	private Provider provider;
	private Set<EntryItem> entryItens = new HashSet<>();
	

	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Set<EntryItem> getEntryItens() {
		return entryItens;
	}
	public void setEntryItens(Set<EntryItem> entryItens) {
		this.entryItens = entryItens;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Double getUnityValue() {
		return unityValue;
	}
	public void setUnityValue(Double unityValue) {
		this.unityValue = unityValue;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
}
