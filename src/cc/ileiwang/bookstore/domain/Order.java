package cc.ileiwang.bookstore.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import cc.ileiwang.bookstore.domain.OrderItem;
import cc.ileiwang.bookstore.domain.User;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 上午10:54:37
*/
public class Order {
	
	private int id;//订单编号
	private int amount;//总金额
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdate;
	private List<OrderItem> orderitems;
	private User user;
	private int state;
	
	public Order() {
		super();
		this.state = 0;
	}
	public Order(int id, int amount, Date createdate, List<OrderItem> orderitems, User user, int state) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdate = createdate;
		this.orderitems = orderitems;
		this.user = user;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public List<OrderItem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
