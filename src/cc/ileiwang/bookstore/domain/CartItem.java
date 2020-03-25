package cc.ileiwang.bookstore.domain;

import cc.ileiwang.bookstore.domain.Book;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 上午10:55:52
*/
public class CartItem {
	private Book book;//购物车项对应的图书
	int quantity;//购物车项对应的数量
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItem(Book book, int quantity) {
		super();
		this.book = book;
		this.quantity = quantity;
	}
	public CartItem() {
		super();
	}
	public CartItem(Book book) {
		super();
		this.book = book;
		this.quantity = 1;
	}
	public void incrementQuantity() {
		quantity++;
	}
    public void decrementQuantity() {
        quantity--;
    }
    public int getTotal() {
        int amount = 0;
        amount = (this.getQuantity() * book.getPrice());
        return amount;
    }

}
