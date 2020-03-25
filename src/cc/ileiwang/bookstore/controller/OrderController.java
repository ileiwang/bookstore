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
* @version 2018��7��11�� ����3:49:12
*/
@Controller
public class OrderController {
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	//�鿴���˶������û��������ж���������Ա��
	@RequestMapping(value = "/orderlist/{pageIndex}")
	public ModelAndView orderlist(HttpSession session, ModelAndView mv,@PathVariable("pageIndex")Integer pageIndex) {
		
		User user = (User)session.getAttribute("user");
		Admin admin = (Admin)session.getAttribute("admin");
		
		//�鿴���˶���ҳ
		if(user!=null){
			//�û��޸Ķ����󣬲鿴������״̬δ�䣬��Ϊsession�е�userδ�䣬��user�л��orderҲδ��
			//List<Order> orders =  user.getOrders();
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			//�հ׶���
			Order order = new Order();
			//���ö����û����ܲ�ѯ�����û������ж���
			order.setUser(user);
			//List<Order> orders = Service.findOrdersByUserId(user.getId());
			//order�а������û�����Ϣ����ѯʱ��ֻ��ѯ���û�����Ϣ
			List<Order> orders = Service.findOrder(order, pageModel);
			mv.addObject("orders", orders);
			mv.addObject("pageModel", pageModel);
			mv.setViewName("order/orderlist");
		}
		
		//����Ա�鿴���ж���
		if(admin!=null){
/*			List<Order> orders =  Service.findAllOrder();*/
/*			mv.addObject("orders", orders);*/
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			//�հ׶���
			Order order = new Order();
			//���ݿհ׶������ң��ܹ����ҳ����ж���
			List<Order> orders = Service.findOrder(order, pageModel);
			mv.addObject("orders", orders);
			mv.addObject("pageModel", pageModel);
			mv.setViewName("order/orderlist");
		}
		return mv;
	}
	
	//�鿴ĳ�û��Ķ���
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
		//���ö����û����ܲ�ѯ�����û������ж���
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
	
	//�鿴������ϸ��Ϣ
	@RequestMapping(value = "/orderdetail/{id}",method=RequestMethod.GET)
	public ModelAndView orderdetail(@PathVariable("id")int id, ModelAndView mv) {
		Order order = Service.findOrderById(id);
		mv.addObject("order", order);
		mv.setViewName("order/orderdetail");
		return mv;
	}
	
	//����
	
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
	
	//�ջ�
	
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
	
	//�����˻�
	
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
	
	//��׼�˻�
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
