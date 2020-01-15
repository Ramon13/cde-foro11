package br.com.javamon.entity;

public class EntryItem {

	private Long id;
	private Item item;
	private Long amount;
	private Entry entry;
	private Double unityValue;
	private Double total;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	
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
	@Override
	public String toString() {
		return "EntryItem [id=" + id + ", item=" + item + ", amount=" + amount + ", entry="
				+ entry + ", unityValue=" + unityValue + ", total=" + total + "]";
	}
	
	
}
