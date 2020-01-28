package br.com.javamon.admin.domain;

import java.util.List;

import br.com.javamon.entity.Order;

public class OrderPaginate {

	private List<Order> orderList;
	private int numPages;
	
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public int getNumPages() {
		return numPages;
	}
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
	
}
