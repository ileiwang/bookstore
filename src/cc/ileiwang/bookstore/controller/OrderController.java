package cc.ileiwang.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.domain.Admin;
import cc.ileiwang.bookstore.domain.Order;
import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.service.BookStoreService;
import cc.ileiwang.bookstore.util.common.BSConstants;
import cc.ileiwang.bookstore.util.tag.PageModel;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:49:12
*/
@Controller
public class OrderController {
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	//查看个人订单（用户）或所有订单（管理员）
	@RequestMapping(value = "/orderlist/{pageIndex}")
	public ModelAndView orderlist(HttpSession session, ModelAndView mv,@PathVariable("pageIndex")Integer pageIndex) {
		
		User user = (User)session.getAttribute("user");
		Admin admin = (Admin)session.getAttribute("admin");
		
		//查看个人订单页
		if(user!=null){
			//用户修改订单后，查看订单，状态未变，因为session中的user未变，从user中获得order也未变
			//List<Order> orders =  user.getOrders();
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			//空白订单
			Order order = new Order();
			//设置订单用户，能查询出该用户的所有订单
			order.setUser(user);
			//List<Order> orders = Service.findOrdersByUserId(user.getId());
			//order中包含了用户的信息，查询时，只查询该用户的信息
			List<Order> orders = Service.findOrder(order, pageModel);
			mv.addObject("orders", orders);
			mv.addObject("pageModel", pageModel);
			mv.setViewName("order/orderlist");
		}
		
		//管理员查看所有订单
		if(admin!=null){
/*			List<Order> orders =  Service.findAllOrder();*/
/*			mv.addObject("orders", orders);*/
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			//空白订单
			Order order = new Order();
			//根据空白订单查找，能够查找出所有订单
			List<Order> orders = Service.findOrder(order, pageModel);
			mv.addObject("orders", orders);
			mv.addObject("pageModel", pageModel);
			mv.setViewName("order/orderlist");
		}
		return mv;
	}
	
	//查看某用户的订单
	@RequestMapping(value = "/findordersbyuserid/{id}/{pageIndex}",method=RequestMethod.GET)
	public ModelAndView orderlist(@PathVariable("id")int id,@PathVariable("pageIndex")Integer pageIndex, ModelAndView mv) {
/*		List<Order> orders = Service.findOrdersByUserId(id);
		for(Order order:orders)
		{
			System.out.println(order.getCreatedate());
		}*/
		
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		
		Order order = new Order();
		//设置订单用户，能查询出该用户的所有订单
		User user = new User();
		user.setId(id);
		order.setUser(user);
		List<Order> orders = Service.findOrder(order, pageModel);
		mv.addObject("orders", orders);
		mv.addObject("userid", id);
		mv.addObject("pageModel",pageModel);
		mv.setViewName("order/orderlistbyuser");
		return mv;
	}
	
	//查看订单详细信息
	@RequestMapping(value = "/orderdetail/{id}",method=RequestMethod.GET)
	public ModelAndView orderdetail(@PathVariable("id")int id, ModelAndView mv) {
		Order order = Service.findOrderById(id);
		mv.addObject("order", order);
		mv.setViewName("order/orderdetail");
		return mv;
	}
	
	//发货
	
	@RequestMapping(value = "/sendOrder/{id}",method=RequestMethod.GET)
	public ModelAndView sendOrder(@PathVariable("id") int id, ModelAndView mv) {
		
		Order order = null;
		order = Service.findOrderById(id);
		if(order!=null)
		{
			order.setState(BSConstants.ORDERSTATE_YFHWQS);
			Service.modifyOrder(order);
		}
		mv.setViewName("redirect:/orderlist/1");
		return mv;
	}
	
	//收货
	
	@RequestMapping(value = "/receiveOrder/{id}",method=RequestMethod.GET)
	public ModelAndView receiveOrder(@PathVariable("id") int id, ModelAndView mv) {
		
		Order order = null;
		order = Service.findOrderById(id);
		if(order!=null)
		{
			order.setState(BSConstants.ORDERSTATE_YQS);
			Service.modifyOrder(order);
		}
		mv.setViewName("redirect:/orderlist/1");
		return mv;
	}
	
	//申请退货
	
	@RequestMapping(value = "/applybackOrder/{id}",method=RequestMethod.GET)
	public ModelAndView applybackOrder(@PathVariable("id") int id, ModelAndView mv) {
		
		Order order = null;
		order = Service.findOrderById(id);
		if(order!=null)
		{
			order.setState(BSConstants.ORDERSTATE_ZZTH);
			Service.modifyOrder(order);
		}
		mv.setViewName("redirect:/orderlist/1");
		return mv;
	}
	
	//批准退货
	@RequestMapping(value = "/approvebackOrder/{id}",method=RequestMethod.GET)
	public ModelAndView approvebackOrder(@PathVariable("id") int id, ModelAndView mv) {
		
		Order order = null;
		order = Service.findOrderById(id);
		if(order!=null)
		{
			order.setState(BSConstants.ORDERSTATE_YTH);
			Service.modifyOrder(order);
		}
		mv.setViewName("redirect:/orderlist/1");
		return mv;
	}
}
