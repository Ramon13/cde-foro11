package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class LogLevel {

	private Long id;
	private String description;
	private Set<AppLog> appLogs = new HashSet<>(0);
	
	public Set<AppLog> getAppLogs() {
		return appLogs;
	}
	public void setAppLogs(Set<AppLog> appLogs) {
		this.appLogs = appLogs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	
	@SuppressWarnings(value = "unused")
	private void setId(Long id) {
		this.id = id;
	}
}
