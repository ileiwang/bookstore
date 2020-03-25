package cc.ileiwang.bookstore.domain;

import java.util.List;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 上午10:55:07
*/
public class Book {
	
	private int id;
	private String name;
	private int price;
	private int quantity;
	private String img;
	private int isdel;
	private List<OrderItem> orderitems;
	public Book() {
		super();
		this.isdel = 0;
	}
	public Book(int id, String name, int price, int quantity, String img, int isdel, List<OrderItem> orderitems) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.img = img;
		this.isdel = isdel;
		this.orderitems = orderitems;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public List<OrderItem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	

}
