package br.com.javamon.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Entry{
	
	@Deprecated
	private Item item;
	private Long id;
	private Long amount;
	private LocalDate date;
	private Locale locale;
	private Double unityValue;
	private Double total;
	private Document document;
	private Provider provider;
	private Set<EntryItem> entryItens = new HashSet<>();
	
	public Double getUnityValue() {
		return unityValue;
	}
	public void setUnityValue(Double unityValue) {
		this.unityValue = unityValue;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Entry [item=" + item + ", id=" + id + ", amount=" + amount + ", date=" + date + ", locale=" + locale
				+ ", unityValue=" + unityValue + ", total=" + total + ", document=" + document + ", provider="
				+ provider + ", entryItens=" + entryItens + "]";
	}
	
	
}
