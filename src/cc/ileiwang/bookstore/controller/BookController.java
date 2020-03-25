package cc.ileiwang.bookstore.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.domain.OrderItem;
import cc.ileiwang.bookstore.service.BookStoreService;
import cc.ileiwang.bookstore.util.tag.PageModel;
import cc.ileiwang.bookstore.vo.JsonBean;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018��7��11�� ����3:44:07
*/
@Controller
public class BookController {
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	
	//����ͼ��
	@RequestMapping(value = "/searchbook",method=RequestMethod.POST)
	public @ResponseBody JsonBean searchbook(HttpServletResponse response,String name) throws IOException {
		JsonBean bean = new JsonBean();
		Book book = Service.findBookByName(name);
		if(book==null){
			bean.setCode(0);
		}
		else{
			bean.setCode(1);
			bean.setMsg(book.getId());
		}
		return bean;
	}
	
	//ͼ����ϸ
	@RequestMapping(value = "/bookdetail/{id}")
	public ModelAndView bookdetail(@PathVariable("id")int id,ModelAndView mv){
		Book book = Service.findBookById(id);
		mv.addObject("book", book);
		//System.out.println(id);
		mv.setViewName("/book/bookdetail");
		return mv;
	}
	
	//��ʾ���ͼ��ҳ
	@RequestMapping(value = "/showaddbook")
	public ModelAndView showaddbook(ModelAndView mv){
		mv.setViewName("/book/addbook");
		return mv;
	}
	
	//��ҳ��ʾͼ��(�ڹ���Ա����ͼ��ʱʹ��)
	@RequestMapping(value = "/booklist/{pageIndex}")
	public ModelAndView booklist(@PathVariable("pageIndex")Integer pageIndex,ModelAndView mv){
/*		List<Book> books = Service.findAllBook();
		mv.addObject("books", books);*/
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		Book book = new Book();
		List<Book> books = Service.findBook(book,pageModel);
		mv.addObject("books", books);
		mv.addObject("pageModel", pageModel);
		mv.setViewName("/book/booklist");
		return mv;
	}
	
	//��ҳ��ʾͼ��(��main.jsp��ʹ��)
	@RequestMapping(value = "/booklistbypage/{pageIndex}")
	public ModelAndView booklistbypage(ModelAndView mv,@PathVariable("pageIndex") Integer pageIndex,HttpSession session){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
/*		List<Book> books = Service.findAllBook();
		mv.addObject("books", books);*/
		Book book = new Book();
		List<Book> books = Service.findBook(book, pageModel);
		session.setAttribute("books", books);
		session.setAttribute("pageModel", pageModel);
		mv.setViewName("redirect:/main");
		return mv;
	}
	
	//���ͼ��
/*	@RequestMapping(value = "/addbook")
	public ModelAndView addbook(HttpServletRequest request, ModelAndView mv, @ModelAttribute Book book,
			@RequestParam("image") MultipartFile image)
			throws IllegalStateException, IOException {
		if (!image.isEmpty()) {
			// �ϴ��ļ�·��
			String path = request.getServletContext().getRealPath("/images/book/");
			System.out.println(path);
			// �ϴ��ļ���
			String filename = image.getOriginalFilename();
			System.out.println(filename);
			System.out.println(book.getName());
			System.out.println(book.getPrice());
			System.out.println(book.getQuantity());
			book.setImg(filename);
			File filepath = new File(path, filename);
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// ���ϴ��ļ����浽һ��Ŀ���ļ�����
			image.transferTo(new File(path + File.separator + filename));
			Service.addBook(book);
			mv.setViewName("redirect:/booklist/1");
		}
		return mv;
	}*/
	
	
	//���ͼ�飬ʹ��Ajax��ʽ
	@RequestMapping(value = "/addbook",method = RequestMethod.POST)
	public @ResponseBody JsonBean addbook(HttpServletRequest request, ModelAndView mv, @ModelAttribute Book book,
			@RequestParam("image") MultipartFile image)
			throws IllegalStateException, IOException {
		
		JsonBean bean = new JsonBean();
		Book booktmp = Service.findBookByName(book.getName());
		//ͼ���Ѵ���
		if(booktmp!=null) {
			bean.setCode(0);
			bean.setMsg(" ͼ���Ѵ��ڣ������ٴ����");
		}
		//ͼ�鲻����
		else
		{
			if (!image.isEmpty()) {
				// �ϴ��ļ�·��
				String path = request.getServletContext().getRealPath("/images/book/");
				System.out.println(path);
				// �ϴ��ļ���
				String filename = image.getOriginalFilename();
/*				System.out.println(filename);
				System.out.println(book.getName());
				System.out.println(book.getPrice());
				System.out.println(book.getQuantity());*/
				book.setImg(filename);
				File filepath = new File(path, filename);
				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				// ���ϴ��ļ����浽һ��Ŀ���ļ�����
				image.transferTo(new File(path + File.separator + filename));
				Service.addBook(book);
				//��ӳɹ�
				bean.setCode(2);
				Book b = Service.findBookByName(book.getName());
				bean.setMsg(b.getId());
				//mv.setViewName("redirect:/booklist/1");
			}
			else{
				bean.setCode(1);
				bean.setMsg("ͼƬΪ�գ����ϴ�ͼƬ");
			}
		}
		//return mv;
		return bean;
	}
	
	//ɾ��ͼ��
	@RequestMapping(value = "/deletebook",method=RequestMethod.POST)
	public @ResponseBody JsonBean deletebook(int id){
		//��ȡͼ��
		JsonBean bean = new JsonBean();
		List<OrderItem> item = Service.findOrderItemsByBookId(id);
		System.out.println("Hello");
		int len = item.size();
		//�ж��������ɾ��
		if(len!=0)
		{
			bean.setCode(1);
			bean.setMsg("��ͼ���ж��������ɾ��");
			System.out.println("World");
		}
		else {
			try {
				//��ȡͼ��
				Book book = Service.findBookById(id);
				//���ɾ�����
				book.setIsdel(1);
				//����
				Service.modifyBook(book);
				bean.setCode(2);
				bean.setMsg("ɾ���ɹ�");
				System.out.println("Success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bean.setCode(3);
				bean.setMsg("ɾ��ʱ���ִ���");
				System.out.println("Error");
			}
		}
		System.out.println("Finish");
		return bean;
	}
	
	//��ʾ�޸�ͼ��
	@RequestMapping(value = "/updatebook")
	public ModelAndView updatebook(ModelAndView mv,int id){
		Book book = Service.findBookById(id);
		System.out.println(id);
		mv.addObject("book", book);
		mv.setViewName("book/updatebook");
		return mv;
	}
	
	//�޸�ͼ��
	@RequestMapping(value = "/update")
	public ModelAndView update(ModelAndView mv,@ModelAttribute Book book){

		System.out.println(book.getName());
		System.out.println(book.getQuantity());
		Service.modifyBook(book);
		mv.setViewName("redirect:/booklist/1");
		return mv;
	}
	
	
/*	//��ȡȫ��ͼ��
	@RequestMapping(value = "/findall",method=RequestMethod.GET)
	public @ResponseBody JsonBean searchbook(){
		JsonBean bean = new JsonBean();
		List<Book> books = Service.findAllBook();
		bean.setMsg(books);
		bean.setCode(1);
		return bean;
	}*/
}
