package cc.ileiwang.bookstore.domain;

import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.domain.Order;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 上午10:54:53
*/
public class OrderItem {
	private int id;
	private int quantity;
	private Book book;
	private Order order;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItem() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
