package cc.ileiwang.bookstore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.ileiwang.bookstore.service.BookStoreService;
import cc.ileiwang.bookstore.dao.AdminDAO;
import cc.ileiwang.bookstore.dao.BookDAO;
import cc.ileiwang.bookstore.dao.OrderDAO;
import cc.ileiwang.bookstore.dao.OrderItemDAO;
import cc.ileiwang.bookstore.dao.UserDAO;
import cc.ileiwang.bookstore.domain.Admin;
import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.domain.Order;
import cc.ileiwang.bookstore.domain.OrderItem;
import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.util.tag.PageModel;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:45:52
*/
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("Service")
public class BookStoreServiceImpl implements BookStoreService{
	
	@Autowired
	private AdminDAO adminDao;

	@Autowired
	private BookDAO bookDao;


	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private OrderItemDAO orderitemDao;

	@Autowired
	private UserDAO userDao;

	@Override
	public User userlogin(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUsernameAndPassword(username, password);
		return user;
	}

	@Override
	public Admin adminlogin(String username, String password) {
		// TODO Auto-generated method stub
		//return null;
		Admin admin = adminDao.selectByUsernameAndPassword(username, password);
		return admin;
	}

	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		List<Admin> admins = adminDao.selectAll();
		return admins;
	}

	@Override
	public Admin findAdminById(int id) {
		// TODO Auto-generated method stub
		//return null;
		Admin admin = adminDao.selectById(id);
		return admin;
	}

	@Override
	public Admin findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		//return null;
		Admin admin = adminDao.selectByUsername(username);
		return admin;
	}



	@Override
	public Admin findAdminByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.selectByUsernameAndPassword(username, password);
		return admin;
	}

	@Override
	public void deleteAdminById(int id) {
		// TODO Auto-generated method stub
		
		adminDao.deleteById(id);
	}

	@Override
	public void deleteAdminByUsername(String username) {
		// TODO Auto-generated method stub
		adminDao.deleteByUsername(username);
	}

	@Override
	public List<Book> findBook(Book book, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		params.put("book", book);
		int recordCount = bookDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<Book> books = bookDao.selectByPage(params);
		 
		return books;
	}

	@Override
	public List<Book> findAllBook() {
		// TODO Auto-generated method stub
		List<Book> books = bookDao.selectAll();
		return books;
	}


	@Override
	public Book findBookById(int id) {
		// TODO Auto-generated method stub
		Book book = bookDao.selectById(id);
		return book;
	}


	@Override
	public Book findBookByName(String name) {
		// TODO Auto-generated method stub
		Book book = bookDao.selectByName(name);
		return book;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookDao.save(book);
	}

	@Override
	public void modifyBook(Book book) {
		// TODO Auto-generated method stub
		bookDao.update(book);
		
	}

	@Override
	public void deleteBookById(int id) {
		// TODO Auto-generated method stub
		bookDao.deleteById(id);
	}

	@Override
	public void deleteBookByName(String name) {
		// TODO Auto-generated method stub
		bookDao.deleteByName(name);
		
	}




	@Override
	public List<Order> findOrder(Order order, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		params.put("order", order);
		int recordCount = orderDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<Order> orders = orderDao.selectByPage(params);
		return orders;
	}

	@Override
	public List<Order> findAllOrder() {
		// TODO Auto-generated method stub
		List<Order> orders = orderDao.selectAll();
		return orders;
	}

	@Override
	public List<Order> findOrdersByUserId(int user_id) {
		// TODO Auto-generated method stub
		List<Order> orders = orderDao.selectByUserId(user_id);
		return orders;
	}

	@Override
	public Order findOrderById(int id) {
		// TODO Auto-generated method stub
		Order order = orderDao.selectById(id);
		return order;
	}
	
	public Order findOrderByUserIdAndAmount(int user_id,int amount)
	{
		Order order = orderDao.selectByUserIdAndAmount(user_id,amount);
		return order;
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
		
	}

	@Override
	public void modifyOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}

	@Override
	public void deleteOrderById(int id) {
		// TODO Auto-generated method stub
		orderDao.deleteById(id);
	}

	@Override
	public void deleteOrderByUserId(int user_id) {
		// TODO Auto-generated method stub
		orderDao.deleteByUserId(user_id);
	}

	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<User> users = userDao.selectByPage(params);
		return users;
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		List<User> users = userDao.selectAll();
		return users;
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		User user = userDao.selectById(id);
		return user;
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUsername(username);
		return user;
	}


	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.selectByUsernameAndPassword(username, password);
		return user;
	}

	@Override
	public User findUserByOrderId(int order_id) {
		// TODO Auto-generated method stub
		User user = userDao.selectByOrderId(order_id);
		return user;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	@Override
	public void deleteUserByUsername(String username) {
		// TODO Auto-generated method stub
		userDao.deleteByUsername(username);
	}

	@Override
	public void changepasswd(int user_id, String password) {
		// TODO Auto-generated method stub
		userDao.changepasswd(user_id, password);
	}

	@Override
	public String findpasswd(String username, String email) {
		// TODO Auto-generated method stub
		String password = userDao.findpasswd(username,email);
		return password;
		
	}

	@Override
	public List<OrderItem> findOrderItem(OrderItem orderitem, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		params.put("orderitem", orderitem);
		int recordCount = orderitemDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<OrderItem> orderitems = orderitemDao.selectByPage(params);
		return orderitems;
	}

	@Override
	public List<OrderItem> findAllOrderItem() {
		// TODO Auto-generated method stub
		List<OrderItem> orderitems = orderitemDao.selectAll();
		return orderitems;
	}

	@Override
	public List<OrderItem> findOrderItemsByOrderId(int order_id) {
		// TODO Auto-generated method stub
		List<OrderItem> orderitems = orderitemDao.selectByOrderId(order_id);
		return orderitems;
	}

	@Override
	public List<OrderItem> findOrderItemsByBookId(int book_id) {
		// TODO Auto-generated method stub
		List<OrderItem> orderitems = orderitemDao.selectByBookId(book_id);
		return orderitems;
	}

	@Override
	public List<OrderItem> findOrderItemsByOrderIdAndBookId(int order_id, int book_id) {
		// TODO Auto-generated method stub
		List<OrderItem> orderitems = orderitemDao.selectByOrderIdAndBookId(order_id, book_id);
		return orderitems;
	}

	@Override
	public void addOrderItem(OrderItem orderitem) {
		// TODO Auto-generated method stub
		orderitemDao.save(orderitem);
	}

	@Override
	public void modifyOrderItem(OrderItem orderitem) {
		// TODO Auto-generated method stub
		orderitemDao.update(orderitem);
	}

	@Override
	public void deleteOrderItemByOrderId(int order_id) {
		// TODO Auto-generated method stub
		orderitemDao.deleteByOrderId(order_id);
	}

	@Override
	public void deleteOrderItemByBookId(int book_id) {
		// TODO Auto-generated method stub
		orderitemDao.deleteByBookId(book_id);
	}

	@Override
	public void deleteOrderItemByOrderIdAndBookId(int order_id, int book_id) {
		// TODO Auto-generated method stub
		orderitemDao.deleteByOrderIdAndBookId(order_id, book_id);
		
	}

	@Override
	public void lockUser(int id) {
		// TODO Auto-generated method stub
		userDao.lock(id);
	}

	@Override
	public void unlockUser(int id) {
		// TODO Auto-generated method stub
		userDao.unlock(id);
	}

	@Override
	public void sendOrder(int id) {
		// TODO Auto-generated method stub
		orderDao.send(id);
		
	}

	@Override
	public void approvebackOrder(int id) {
		// TODO Auto-generated method stub
		orderDao.approveback(id);
	}

	@Override
	public void receiveOrder(int id) {
		// TODO Auto-generated method stub
		orderDao.receive(id);
	}

	@Override
	public void applybackOrder(int id) {
		// TODO Auto-generated method stub
		orderDao.applyback(id);
		
	}
	

}
