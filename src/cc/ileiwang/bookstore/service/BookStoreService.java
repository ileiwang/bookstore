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
* @version 2018��7��11�� ����3:45:14
*/
public interface BookStoreService {
	
	//Login����
	
	public User userlogin(String username,String password);
	public Admin adminlogin(String username,String password);
	
	//Admin
	
/*	public List<Admin> findAdmin(Admin admin,PageModel pageModel);*/
	public List<Admin> findAllAdmin();
	//������
	public Admin findAdminById(int id);
	public Admin findAdminByUsername(String username);
	public Admin findAdminByUsernameAndPassword(String username,String password);
/*	//���
	public void addAdmin(Admin admin);
	//�޸�
	public void modifyAdmin(Admin admin);*/
	//ɾ��
	public void deleteAdminById(int id);
	public void deleteAdminByUsername(String username);
	
	//Book
	
	//������
	public List<Book> findBook(Book book,PageModel pageModel);
	public List<Book> findAllBook();
	
	//������
	public Book findBookById(int id);
	public Book findBookByName(String name);
	//���
	public void addBook(Book book);
	//�޸�
	public void modifyBook(Book book);
	//ɾ��
	public void deleteBookById(int id);
	public void deleteBookByName(String name);
	
	//����
	//������
	public List<Order> findOrder(Order order,PageModel pageModel);
	public List<Order> findAllOrder();
	public List<Order> findOrdersByUserId(int user_id);
	//������
	public Order findOrderById(int id);
	public Order findOrderByUserIdAndAmount(int user_id,int amount);

	//���
	public void addOrder(Order order);
	//�޸�
	public void modifyOrder(Order order);
	//ɾ��
	public void deleteOrderById(int id);
	public void deleteOrderByUserId(int user_id);
	
	//User
	
	//������
	public List<User> findUser(User user,PageModel pageModel);
	public List<User> findAllUser();
	
	//������
	public User findUserById(int id);
	public User findUserByUsername(String username);
	public User findUserByUsernameAndPassword(String username,String password);
	public User findUserByOrderId(int order_id);
	//���
	public void addUser(User user);
	//�޸�
	public void modifyUser(User user);
	//ɾ��
	public void deleteUserById(int id);
	public void deleteUserByUsername(String username);
	
	//�޸�����
	public void changepasswd(int user_id,String password);
	
	public String findpasswd(String username,String email);
	
	
	//����ͼ����
	//������
	public List<OrderItem> findOrderItem(OrderItem orderitem,PageModel pageModel);
	public List<OrderItem> findAllOrderItem();
	public List<OrderItem> findOrderItemsByOrderId(int order_id);
	public List<OrderItem> findOrderItemsByBookId(int book_id);
	public List<OrderItem> findOrderItemsByOrderIdAndBookId(int order_id,int book_id);

	//���
	public void addOrderItem(OrderItem orderitem);
	//�޸�
	public void modifyOrderItem(OrderItem orderitem);
	//ɾ��
	public void deleteOrderItemByOrderId(int order_id);
	public void deleteOrderItemByBookId(int book_id);
	public void deleteOrderItemByOrderIdAndBookId(int order_id,int book_id);
	
	//����
	public void lockUser(int id);
	
	//����
	public void unlockUser(int id);
	
	// ��̨����Ա����
	//����
	public void sendOrder(int id);
	
	//ȷ���˻�
	public void approvebackOrder(int id);
	
	//ǰ̨�û�����
	//ǩ��
	public void receiveOrder(int id);
	//�����˻�
	
	public void applybackOrder(int id);

}
