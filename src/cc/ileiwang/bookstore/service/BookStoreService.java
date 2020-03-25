package cc.ileiwang.bookstore.service;

import java.util.List;

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
* @version 2018年7月11日 下午3:45:14
*/
public interface BookStoreService {
	
	//Login操作
	
	public User userlogin(String username,String password);
	public Admin adminlogin(String username,String password);
	
	//Admin
	
/*	public List<Admin> findAdmin(Admin admin,PageModel pageModel);*/
	public List<Admin> findAllAdmin();
	//单查找
	public Admin findAdminById(int id);
	public Admin findAdminByUsername(String username);
	public Admin findAdminByUsernameAndPassword(String username,String password);
/*	//添加
	public void addAdmin(Admin admin);
	//修改
	public void modifyAdmin(Admin admin);*/
	//删除
	public void deleteAdminById(int id);
	public void deleteAdminByUsername(String username);
	
	//Book
	
	//批查找
	public List<Book> findBook(Book book,PageModel pageModel);
	public List<Book> findAllBook();
	
	//单查找
	public Book findBookById(int id);
	public Book findBookByName(String name);
	//添加
	public void addBook(Book book);
	//修改
	public void modifyBook(Book book);
	//删除
	public void deleteBookById(int id);
	public void deleteBookByName(String name);
	
	//订单
	//批查找
	public List<Order> findOrder(Order order,PageModel pageModel);
	public List<Order> findAllOrder();
	public List<Order> findOrdersByUserId(int user_id);
	//单查找
	public Order findOrderById(int id);
	public Order findOrderByUserIdAndAmount(int user_id,int amount);

	//添加
	public void addOrder(Order order);
	//修改
	public void modifyOrder(Order order);
	//删除
	public void deleteOrderById(int id);
	public void deleteOrderByUserId(int user_id);
	
	//User
	
	//批查找
	public List<User> findUser(User user,PageModel pageModel);
	public List<User> findAllUser();
	
	//单查找
	public User findUserById(int id);
	public User findUserByUsername(String username);
	public User findUserByUsernameAndPassword(String username,String password);
	public User findUserByOrderId(int order_id);
	//添加
	public void addUser(User user);
	//修改
	public void modifyUser(User user);
	//删除
	public void deleteUserById(int id);
	public void deleteUserByUsername(String username);
	
	//修改密码
	public void changepasswd(int user_id,String password);
	
	public String findpasswd(String username,String email);
	
	
	//订单图书项
	//批查找
	public List<OrderItem> findOrderItem(OrderItem orderitem,PageModel pageModel);
	public List<OrderItem> findAllOrderItem();
	public List<OrderItem> findOrderItemsByOrderId(int order_id);
	public List<OrderItem> findOrderItemsByBookId(int book_id);
	public List<OrderItem> findOrderItemsByOrderIdAndBookId(int order_id,int book_id);

	//添加
	public void addOrderItem(OrderItem orderitem);
	//修改
	public void modifyOrderItem(OrderItem orderitem);
	//删除
	public void deleteOrderItemByOrderId(int order_id);
	public void deleteOrderItemByBookId(int book_id);
	public void deleteOrderItemByOrderIdAndBookId(int order_id,int book_id);
	
	//加锁
	public void lockUser(int id);
	
	//解锁
	public void unlockUser(int id);
	
	// 后台管理员操作
	//发货
	public void sendOrder(int id);
	
	//确认退货
	public void approvebackOrder(int id);
	
	//前台用户操作
	//签收
	public void receiveOrder(int id);
	//办理退货
	
	public void applybackOrder(int id);

}
