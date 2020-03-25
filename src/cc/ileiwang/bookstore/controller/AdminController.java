package cc.ileiwang.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.service.BookStoreService;


/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018��7��11�� ����3:49:22
*/
@Controller
public class AdminController {
	// �Զ�ע��Service
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	// �û�����
	@RequestMapping(value = "/usermgmt")
	public ModelAndView usermgmt(ModelAndView mv) {
		mv.setViewName("admin/usermgmt");
		return mv;
	}
	
	// ��������
	@RequestMapping(value = "/ordermgmt")
	public ModelAndView ordermgmt(ModelAndView mv) {
		mv.setViewName("admin/ordermgmt");
		return mv;
	}
	
	// ͼ�����
	@RequestMapping(value = "/bookmgmt")
	public ModelAndView bookmgmt(ModelAndView mv) {
		mv.setViewName("admin/bookmgmt");
		return mv;
	}
}
