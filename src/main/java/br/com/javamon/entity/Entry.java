package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class Entry extends SystemOperation{

	private Double unityValue;
	private Double total;
	private Document document;
	private Provider provider;
	private Set<EntryFile> files = new HashSet<>();
	
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
	public Set<EntryFile> getFiles() {
		return files;
	}
	public void setFiles(Set<EntryFile> files) {
		this.files = files;
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
}
