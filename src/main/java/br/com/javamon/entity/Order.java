package br.com.javamon.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private Long id;
	private LocalDate date;
	private LocalDate releaseDate;
	private Login login;
	private char status;
	private String receivedUser;
	private Set<OrderItem> orderItens = new HashSet<>();
	
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
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Set<OrderItem> getOrderItens() {
		return orderItens;
	}
	public void setOrderItens(Set<OrderItem> orderItens) {
		this.orderItens = orderItens;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", login=" + login + ", status=" + status + ", orderItens="
				+ orderItens + "]";
	}
	
	public String getReceivedUser() {
		return receivedUser;
	}
	
	public void setReceivedUser(String receivedUser) {
		this.receivedUser = receivedUser;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
}
