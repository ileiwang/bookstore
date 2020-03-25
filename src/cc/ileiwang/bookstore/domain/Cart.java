package cc.ileiwang.bookstore.domain;

import java.util.ArrayList;
import java.util.List;

import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.domain.CartItem;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 上午10:55:24
*/
public class Cart {
	List<CartItem> items;//购物车由多个购物车项组成
    int numberOfItems;//总件数
    int total;//总金额
    
    public synchronized List<CartItem> getItems() {
		return items;
	}
    
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
    public synchronized int getNumberOfItems() {

        numberOfItems = 0;

        for (CartItem scItem : items) {

            numberOfItems += scItem.getQuantity();
        }
        return numberOfItems;
    }
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	public synchronized int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	public Cart(List<CartItem> items, int numberOfItems, int total) {
		super();
		this.items = items;
		this.numberOfItems = numberOfItems;
		this.total = total;
	}
	
	public Cart() {
		super();
        items = new ArrayList<CartItem>();
        numberOfItems = 0;
        total = 0;
	}
	
	
    public synchronized void addItem(CartItem item) {
    	Book book = item.getBook();
    	
        boolean newItem = true;
        //判断图书是否已经加到购物车中
        for (CartItem scItem : items) {

            if (scItem.getBook().getId() == book.getId()) {

                newItem = false;
                scItem.incrementQuantity();
            }
        }

        if (newItem) {
        	items.add(item);
            //CartItem scItem = new CartItem(book);
            //items.add(scItem);
        }
    }
    
    public synchronized void update(Book book, String quantity) {

        int qty = -1;

        qty = Integer.parseInt(quantity);

        if (qty >= 0) {

            CartItem item = null;

            for (CartItem scItem : items) {

                if (scItem.getBook().getId() == book.getId()) {

                    if (qty != 0) {
                        // set item quantity to new value
                        scItem.setQuantity(qty);
                    } else {
                        // if quantity equals 0, save item and break
                        item = scItem;
                        break;
                    }
                }
            }

            if (item != null) {
                // remove from cart
                items.remove(item);
            }
        }
    }
    

    
    public synchronized int getSubtotal() {

        int amount = 0;

        for (CartItem scItem : items) {

            Book book = (Book) scItem.getBook();
            amount += (scItem.getQuantity() * book.getPrice());
        }

        return amount;
    }
    
    public synchronized void calculateTotal(String surcharge) {

        int amount = 0;

        // cast surcharge as double
        int s = Integer.parseInt(surcharge);

        amount = this.getSubtotal();
        amount += s;

        total = amount;
    }
    

    //清空购物车
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }

}
