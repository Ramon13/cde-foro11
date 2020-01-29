package br.com.javamon.entity;

import java.util.HashSet;
import java.util.Set;

public class Login {

	private Long id;
	private String user;
	private String password;
	private String confirmationPassword;
	private Locale locale;
	private Permission permission;
	private Boolean readjustToCart = false;
	private Boolean active;
	private Boolean resetPassword = false;
	private Cart cart;
	private Set<Order> orders = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passwords) {
		this.password = passwords;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getConfirmationPassword() {
		return confirmationPassword;
	}
	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public void setReadjustToCart(Boolean readjustToCart) {
		this.readjustToCart = readjustToCart;
	}
	public Boolean getReadjustToCart() {
		return readjustToCart;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getDescription() {
		return user;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(Boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
}
