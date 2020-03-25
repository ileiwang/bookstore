package cc.ileiwang.bookstore.controller;

import java.io.IOException;
/*import java.io.PrintWriter;*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.domain.Admin;
import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.domain.Cart;
import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.util.common.BSConstants;
import cc.ileiwang.bookstore.util.tag.PageModel;
import cc.ileiwang.bookstore.vo.JsonBean;
import cc.ileiwang.bookstore.service.BookStoreService;

/**
 * @author Lei Wang
 * @email ileiwang@live.com
 * @blog www.ileiwang.cc
 * @version 2018��7��11�� ����3:49:01
 */
@Controller
public class LoginController {
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;

	// ��¼����
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody JsonBean login(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("type") String type, HttpSession session,
			HttpServletResponse response, HttpServletRequest request,Model model) throws IOException {
		Admin admin = null;
		User user = null;
		User usertmp = null;
		JsonBean bean = new JsonBean();

		/* PrintWriter writer = response.getWriter(); */

		// ����Ա
		if (type.equals("admin")) {
			try {
				admin = Service.findAdminByUsername(username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �û�������
			if (admin == null) {
				/* writer.write("{\"code\":0}"); */
				bean.setCode(0);
			} else {
				admin = Service.adminlogin(username, password);
				// �û����ڵ����������
				if (admin == null) {
					/* writer.write("{\"code\":1}"); */
					bean.setCode(1);
				} else {
					/* writer.write("{\"code\":2}"); */
					bean.setCode(3);
					session.setAttribute(BSConstants.ADMINSESSION, admin);
					session.setMaxInactiveInterval(60 * 60);
/*					List<Book> books = Service.findAllBook();
					session.setAttribute("books", books);*/
					PageModel pageModel = new PageModel();
					Book book = new Book();
					book.setIsdel(0);
					List<Book> books = Service.findBook(book, pageModel);
					session.setAttribute("books", books);
					session.setAttribute("pageModel", pageModel);
				}
			}
		}

		
		// �û�
		else {
			try {
				user = Service.findUserByUsername(username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				bean.setCode(8);
				e.printStackTrace();
				bean.setMsg(e.getMessage());
			}

			// �û�������
			if (user == null) {
				/* writer.write("{\"code\":0}"); */
				bean.setCode(0);
			}

			// �û�����
			else {
				// usertmp��Ϊnull,����������������
				usertmp = Service.findUserByUsername(username);

				// �˺��ѱ������������ٴε�¼
				if (usertmp.getIslocked() == 1) {

					bean.setCode(9);
					bean.setMsg("��������������Σ��˺��ѱ�����������ϵ����Ա���н�����");
				}
				// �˺�δ�����������Ե�¼
				else {
					user = Service.userlogin(username, password);

					// �������
					if (user == null) {
						/* writer.write("{\"code\":1}"); */
						bean.setCode(1);
						bean.setMsg("�������");
						user = Service.findUserByUsername(username);
						user.setErrorcount(user.getErrorcount() + 1);
						if (user.getErrorcount() == 3) {
							user.setIslocked(1);
						}
						Service.modifyUser(user);
					}

					// ��¼�ɹ�
					else {
						/* writer.write("{\"code\":2}"); */
						bean.setCode(2);
						session.setAttribute(BSConstants.USERSESSION, user);
						session.setMaxInactiveInterval(60 * 60);
						//��ȡͼ���б�
						
						PageModel pageModel = new PageModel();
/*						System.out.println("getPageIndex = " + pageModel.getPageIndex());
						System.out.println("getPageSize = " + pageModel.getPageSize());
						System.out.println("getRecordCount = " + pageModel.getRecordCount());*/
						Book book = new Book();
						book.setIsdel(0);
						List<Book> books = Service.findBook(book, pageModel);
/*						model.addAttribute("books", books);
						model.addAttribute("pageModel", pageModel);*/
						
						
						
						//List<Book> books = Service.findAllBook();
						session.setAttribute("books", books);
						session.setAttribute("pageModel", pageModel);
/*						System.out.println("getPageIndex = " + pageModel.getPageIndex());
						System.out.println("getPageSize = " + pageModel.getPageSize());
						System.out.println("getRecordCount = " + pageModel.getRecordCount());*/
						//�������ﳵ
						Cart cart = new Cart();
						session.setAttribute("cart", cart);

					}

				}
			}
		}

		
		return bean;
		/*
		 * writer.flush(); writer.close();
		 */
	}

	// ע��
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody JsonBean register(@ModelAttribute User user, ModelAndView mv, HttpSession session) {
		JsonBean bean = new JsonBean();
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		String username = user.getUsername();
		User user2 = null;
		// �����û����Ƿ����
		try {
			user2 = Service.findUserByUsername(username);
			bean.setCode(1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e1.getMessage());
		}

		if (user2 == null) {
			try {
				Service.addUser(user);
				bean.setCode(2);
				//ע��ɹ���Ҫ��ת����¼ҳ�����ܽ��û�����session��
				//session.setAttribute(BSConstants.USERSESSION, user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				bean.setCode(0);
				bean.setMsg(e.getMessage());
				e.printStackTrace();
			}
		}
		return bean;
	}

	// �˳�
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		session.invalidate();
		mv.setViewName("redirect:/loginForm");
		return mv;
	}

}
