package br.com.javamon.entity;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

public class Item {

	private Long id;
	private String description;
	private UnityType unityType;
	private Subitem subitem;
	private ItemType type;
	private Long currentAmount;
	private Blob specification;
	private Long mainImage;
	private Set<ItemImage> images = new HashSet<>();
	private Set<Entry> entries = new HashSet<>();
	private Set<OrderItem> orderItens = new HashSet<>();
	
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
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}
	public Long getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Long currentAmount) {
		this.currentAmount = currentAmount;
	}
	public Set<ItemImage> getImages() {
		return images;
	}
	public void setImages(Set<ItemImage> images) {
		this.images = images;
	}
	public Set<Entry> getEntries() {
		return entries;
	}
	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}
	public UnityType getUnityType() {
		return unityType;
	}
	public void setUnityType(UnityType unityType) {
		this.unityType = unityType;
	}
	public Set<OrderItem> getOrderItens() {
		return orderItens;
	}
	public void setOrderItens(Set<OrderItem> orderItens) {
		this.orderItens = orderItens;
	}
	public Subitem getSubitem() {
		return subitem;
	}
	public void setSubitem(Subitem subitem) {
		this.subitem = subitem;
	}
	public Blob getSpecification() {
		return specification;
	}
	public void setSpecification(Blob specification) {
		this.specification = specification;
	}
	public Long getMainImage() {
		return mainImage;
	}
	public void setMainImage(Long mainImage) {
		this.mainImage = mainImage;
	}
}
