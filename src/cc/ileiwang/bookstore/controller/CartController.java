package cc.ileiwang.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.domain.Cart;
import cc.ileiwang.bookstore.domain.CartItem;
import cc.ileiwang.bookstore.domain.Order;
import cc.ileiwang.bookstore.domain.OrderItem;
import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.service.BookStoreService;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018��7��11�� ����3:49:46
*/
@Controller
public class CartController {
	
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	//�鿴���ﳵ
	@RequestMapping(value = "/cart")
	public ModelAndView cart(HttpSession session, ModelAndView mv) {
		mv.setViewName("cart/cart");
		return mv;
	}
	
	//���빺�ﳵ
	@RequestMapping(value = "/addtocart",method=RequestMethod.GET)
	public ModelAndView addtocart(HttpSession session, ModelAndView mv,String ids) {
		
		//��session��ȡ�����ﳵ
		Cart cart = (Cart)session.getAttribute("cart");
		String[] idArray = ids.split(",");
		for(String id : idArray){
			
			//����ͼ��
			Book book = Service.findBookById(Integer.parseInt(id));
			//���п��
			if(book.getQuantity()!=0)
			{
				
				int quantity = book.getQuantity();
				CartItem item = new CartItem();
				item.setBook(book);//������Ʒ��ͼ��
				item.setQuantity(1);//Ĭ������Ϊ1
				//��Ʒ����빺�ﳵ
				cart.addItem(item);
				//���¿��
				book.setQuantity(quantity-1);
				Service.modifyBook(book);
			}
		}
		//�����ܽ��
		int total = cart.getSubtotal();
		cart.setTotal(total);
		mv.setViewName("redirect:/cart");
		return mv;
	}
	
	
	//���¹��ﳵ
	@RequestMapping(value = "/updatecart",method=RequestMethod.GET)
	public ModelAndView updatecart(HttpSession session, ModelAndView mv,String nums) {
		
		//��session��ȡ�����ﳵ
		Cart cart = (Cart)session.getAttribute("cart");
		//��ȡ����Ʒ������
		String[] numArray = nums.split(",");
		//��ȡ���ﳵ�е���Ʒ��
		List<CartItem> items = cart.getItems();
		
		int len = numArray.length;
		//���¸���Ʒ������
		for(int i = 0;i<len;i++)
		{
			CartItem item = items.get(i);
			item.setQuantity(Integer.parseInt(numArray[i]));
			
		}
		//�����ܼ۸�
		int total = cart.getSubtotal();
		cart.setTotal(total);
		mv.setViewName("redirect:/cart");
		return mv;
	}
	
	//����
	@RequestMapping(value = "/purchase")
	public ModelAndView purchase(HttpSession session, ModelAndView mv) {
		
		//ȡ�����ﳵ
		Cart cart = (Cart)session.getAttribute("cart");
		//ȡ���û�
		User user = (User)session.getAttribute("user");
		
		
		Order order = new Order();//�½�����
		order.setAmount(cart.getTotal());//���ý��
		order.setState(0);//����Ĭ��״̬
		order.setUser(user);//�����û�
		Service.addOrder(order);//���
		
		//ȡ����ӵĶ�������ö�����
		order = Service.findOrderByUserIdAndAmount(user.getId(), cart.getTotal());
		
		//ȡ�����ﳵ�����й�����
		List<CartItem> cartitems = cart.getItems();
		for(CartItem cartitem:cartitems)
		{
			//ÿһ���������Ӧһ��������
			OrderItem orderitem = new OrderItem();
			//���ö������Ӧ��ͼ��
			orderitem.setBook(cartitem.getBook());
			System.out.println(order.getId());
			//���ö������Ӧ�Ķ�����
			orderitem.setOrder(order);
			//���ö�������ͼ�������
			orderitem.setQuantity(cartitem.getQuantity());
			//���涩������������
			Service.addOrderItem(orderitem);
		}
		
		Order orders = Service.findOrderById(order.getId());
		//����ɹ�����չ��ﳵ
		cart.clear();
		session.setAttribute("cart", cart);
		mv.addObject("order", orders);
		mv.setViewName("cart/shopping-success");
		return mv;
	}
	
	
	//��չ��ﳵ
	@RequestMapping(value = "/clearcart")
	public ModelAndView clearcart(HttpSession session,ModelAndView mv)
	{
		Cart cart = (Cart)session.getAttribute("cart");
		//���α���������
		List<CartItem> items = cart.getItems();
		for(CartItem item:items) {
			//��ȡ������ͼ��
			Book book = item.getBook();
			//��ȡ������ͼ������
			int quantity=item.getQuantity();
			//ͼ�����
			book.setQuantity(book.getQuantity()+quantity);
			//����
			Service.modifyBook(book);
		}
		//��չ��ﳵ
		cart.clear();
		//session.setAttribute(arg0, arg1);
		mv.setViewName("redirect:/cart");
		return mv;
	}
}
