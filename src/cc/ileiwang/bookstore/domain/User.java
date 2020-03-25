package cc.ileiwang.bookstore.domain;

import java.util.List;

import cc.ileiwang.bookstore.domain.Order;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 上午10:52:43
*/
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private List<Order> orders;
	private int islocked;
	private int errorcount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public int getIslocked() {
		return islocked;
	}
	public void setIslocked(int islocked) {
		this.islocked = islocked;
	}
	public User() {
		super();
		this.islocked = 0;
		this.errorcount = 0;
	}
	public User(int id, String username, String password, String email, List<Order> orders, int islocked,int errorcount) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.orders = orders;
		this.islocked = islocked;
		this.errorcount = errorcount;
	}
	public int getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}
	
}
