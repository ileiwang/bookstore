package cc.ileiwang.bookstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.service.BookStoreService;
import cc.ileiwang.bookstore.util.tag.PageModel;
import cc.ileiwang.bookstore.vo.JsonBean;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018��7��11�� ����3:48:51
*/
@Controller
public class UserController {
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	//��ѯ�û����Ƿ��ѱ�ע��
	@RequestMapping(value = "/findusername")
	public void findusername(String username,HttpServletResponse response) throws IOException
	{
		PrintWriter writer = response.getWriter();
		User user = Service.findUserByUsername(username);
		if(user==null){
			writer.write("{\"code\":0}");
		}
		else{
			writer.write("{\"code\":1}");
		}
		writer.flush();
		writer.close();
	}
	
	//�鿴�û��б�
	@RequestMapping(value = "/userlist/{pageIndex}")
	public ModelAndView userlist(ModelAndView mv,HttpSession session,HttpServletRequest request,Model model,@PathVariable("pageIndex")Integer pageIndex) {
		//ֻ�й���Ա���Է����û��б�
		if(session.getAttribute("admin")!=null)
		{
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			User user = new User();
			List<User> users = Service.findUser(user,pageModel);
			System.out.println("��ѯ��");
			System.out.println(users.size());
			mv.addObject("users", users);
			mv.addObject("pageModel", pageModel);
			mv.setViewName("user/userlist");
			
			
			
			
/*			List<User> users = Service.findAllUser();
			mv.addObject("users", users);
			mv.setViewName("user/userlist");*/
		}
		//��Ϊ�û�����δ��¼��ֱ����ת����ҳ
		else{
			mv.setViewName("${ctx}/main");
		}
		return mv;
	}
	
	//�����û��������û�
	@RequestMapping(value = "/finduserbyusername",method=RequestMethod.POST)
	public @ResponseBody JsonBean finduserbyusername(HttpServletResponse response,String username) throws IOException {
		JsonBean bean = new JsonBean();
		User user = Service.findUserByUsername(username);
		if(user==null){
			bean.setCode(0);
			bean.setMsg("�û�������");
		}
		else
		{
			bean.setCode(1);
			bean.setMsg(user.getId());
		}
		return bean;
	}
	
	//�鿴�û���ϸ����
	@RequestMapping(value = "/userdetail/{id}",method=RequestMethod.GET)
	public ModelAndView userdetail(@PathVariable("id")int id,ModelAndView mv) throws IOException {
		User user = Service.findUserById(id);
		mv.addObject("user", user);
		System.out.println(id);
		mv.setViewName("/user/userdetail");
		return mv;
	}
	
	
	//ɾ���û�
	@RequestMapping(value = "/deleteuser/{id}",method=RequestMethod.GET)
	public ModelAndView deleteuser(@PathVariable("id") int id,ModelAndView mv){
		User user = Service.findUserById(id);
		if(user!=null)
		{
			Service.deleteUserById(id);
			
		}
		mv.setViewName("redirect:/userlist/1");
		return mv;
	}
	
	//��ʾ�޸��û�
	@RequestMapping(value = "/updateuser/{id}",method=RequestMethod.GET)
	public ModelAndView updateuser(@PathVariable("id") int id,ModelAndView mv){
		User user = Service.findUserById(id);
		if(user!=null){
			//Service.deleteUserById(id);
			mv.addObject("user", user);
			
		}
		mv.setViewName("user/updateuser");
		return mv;
	}
	
	@RequestMapping(value = "/saveupdateuser")
	public ModelAndView update(ModelAndView mv,@ModelAttribute User user){
		Service.modifyUser(user);
		mv.setViewName("redirect:/userdetail/"+user.getId());
		return mv;
	}
	
	//�û�����
	@RequestMapping(value = "/unlockuser/{id}",method=RequestMethod.GET)
	public ModelAndView unlockuser(@PathVariable("id") int id,ModelAndView mv) {
		User user = null;
		user = Service.findUserById(id);
		if(user!=null)
		{
			//����
			user.setIslocked(0);
			//���ô������
			user.setErrorcount(0);
			//�����޸�
			Service.modifyUser(user);
		}
		mv.setViewName("redirect:/userlist/1");
		return mv;
	}
}
