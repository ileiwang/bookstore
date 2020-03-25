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
* @version 2018年7月11日 下午3:49:46
*/
@Controller
public class CartController {
	
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	//查看购物车
	@RequestMapping(value = "/cart")
	public ModelAndView cart(HttpSession session, ModelAndView mv) {
		mv.setViewName("cart/cart");
		return mv;
	}
	
	//加入购物车
	@RequestMapping(value = "/addtocart",method=RequestMethod.GET)
	public ModelAndView addtocart(HttpSession session, ModelAndView mv,String ids) {
		
		//从session中取出购物车
		Cart cart = (Cart)session.getAttribute("cart");
		String[] idArray = ids.split(",");
		for(String id : idArray){
			
			//查找图书
			Book book = Service.findBookById(Integer.parseInt(id));
			//若有库存
			if(book.getQuantity()!=0)
			{
				
				int quantity = book.getQuantity();
				CartItem item = new CartItem();
				item.setBook(book);//设置物品项图书
				item.setQuantity(1);//默认数量为1
				//物品项加入购物车
				cart.addItem(item);
				//更新库存
				book.setQuantity(quantity-1);
				Service.modifyBook(book);
			}
		}
		//计算总金额
		int total = cart.getSubtotal();
		cart.setTotal(total);
		mv.setViewName("redirect:/cart");
		return mv;
	}
	
	
	//更新购物车
	@RequestMapping(value = "/updatecart",method=RequestMethod.GET)
	public ModelAndView updatecart(HttpSession session, ModelAndView mv,String nums) {
		
		//从session中取出购物车
		Cart cart = (Cart)session.getAttribute("cart");
		//获取各物品的数量
		String[] numArray = nums.split(",");
		//获取购物车中的物品项
		List<CartItem> items = cart.getItems();
		
		int len = numArray.length;
		//更新各物品项数量
		for(int i = 0;i<len;i++)
		{
			CartItem item = items.get(i);
			item.setQuantity(Integer.parseInt(numArray[i]));
			
		}
		//计算总价格
		int total = cart.getSubtotal();
		cart.setTotal(total);
		mv.setViewName("redirect:/cart");
		return mv;
	}
	
	//购买
	@RequestMapping(value = "/purchase")
	public ModelAndView purchase(HttpSession session, ModelAndView mv) {
		
		//取出购物车
		Cart cart = (Cart)session.getAttribute("cart");
		//取出用户
		User user = (User)session.getAttribute("user");
		
		
		Order order = new Order();//新建订单
		order.setAmount(cart.getTotal());//设置金额
		order.setState(0);//设置默认状态
		order.setUser(user);//设置用户
		Service.addOrder(order);//添加
		
		//取出添加的订单，获得订单号
		order = Service.findOrderByUserIdAndAmount(user.getId(), cart.getTotal());
		
		//取出购物车中所有购物项
		List<CartItem> cartitems = cart.getItems();
		for(CartItem cartitem:cartitems)
		{
			//每一个购物项对应一个订单项
			OrderItem orderitem = new OrderItem();
			//设置订单项对应的图书
			orderitem.setBook(cartitem.getBook());
			System.out.println(order.getId());
			//设置订单项对应的订单号
			orderitem.setOrder(order);
			//设置订单项中图书的数量
			orderitem.setQuantity(cartitem.getQuantity());
			//保存订单项到订单项表中
			Service.addOrderItem(orderitem);
		}
		
		Order orders = Service.findOrderById(order.getId());
		//购买成功后，清空购物车
		cart.clear();
		session.setAttribute("cart", cart);
		mv.addObject("order", orders);
		mv.setViewName("cart/shopping-success");
		return mv;
	}
	
	
	//清空购物车
	@RequestMapping(value = "/clearcart")
	public ModelAndView clearcart(HttpSession session,ModelAndView mv)
	{
		Cart cart = (Cart)session.getAttribute("cart");
		//依次遍历购物项
		List<CartItem> items = cart.getItems();
		for(CartItem item:items) {
			//获取购物项图书
			Book book = item.getBook();
			//获取购物项图书数量
			int quantity=item.getQuantity();
			//图书入库
			book.setQuantity(book.getQuantity()+quantity);
			//保存
			Service.modifyBook(book);
		}
		//清空购物车
		cart.clear();
		//session.setAttribute(arg0, arg1);
		mv.setViewName("redirect:/cart");
		return mv;
	}
}
