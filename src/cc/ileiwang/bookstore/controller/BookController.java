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
* @version 2018年7月11日 下午3:44:07
*/
@Controller
public class BookController {
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	
	//搜索图书
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
	
	//图书详细
	@RequestMapping(value = "/bookdetail/{id}")
	public ModelAndView bookdetail(@PathVariable("id")int id,ModelAndView mv){
		Book book = Service.findBookById(id);
		mv.addObject("book", book);
		//System.out.println(id);
		mv.setViewName("/book/bookdetail");
		return mv;
	}
	
	//显示添加图书页
	@RequestMapping(value = "/showaddbook")
	public ModelAndView showaddbook(ModelAndView mv){
		mv.setViewName("/book/addbook");
		return mv;
	}
	
	//分页显示图书(在管理员管理图书时使用)
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
	
	//分页显示图书(在main.jsp中使用)
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
	
	//添加图书
/*	@RequestMapping(value = "/addbook")
	public ModelAndView addbook(HttpServletRequest request, ModelAndView mv, @ModelAttribute Book book,
			@RequestParam("image") MultipartFile image)
			throws IllegalStateException, IOException {
		if (!image.isEmpty()) {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/images/book/");
			System.out.println(path);
			// 上传文件名
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
			// 将上传文件保存到一个目标文件当中
			image.transferTo(new File(path + File.separator + filename));
			Service.addBook(book);
			mv.setViewName("redirect:/booklist/1");
		}
		return mv;
	}*/
	
	
	//添加图书，使用Ajax方式
	@RequestMapping(value = "/addbook",method = RequestMethod.POST)
	public @ResponseBody JsonBean addbook(HttpServletRequest request, ModelAndView mv, @ModelAttribute Book book,
			@RequestParam("image") MultipartFile image)
			throws IllegalStateException, IOException {
		
		JsonBean bean = new JsonBean();
		Book booktmp = Service.findBookByName(book.getName());
		//图书已存在
		if(booktmp!=null) {
			bean.setCode(0);
			bean.setMsg(" 图书已存在，请勿再次添加");
		}
		//图书不存在
		else
		{
			if (!image.isEmpty()) {
				// 上传文件路径
				String path = request.getServletContext().getRealPath("/images/book/");
				System.out.println(path);
				// 上传文件名
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
				// 将上传文件保存到一个目标文件当中
				image.transferTo(new File(path + File.separator + filename));
				Service.addBook(book);
				//添加成功
				bean.setCode(2);
				Book b = Service.findBookByName(book.getName());
				bean.setMsg(b.getId());
				//mv.setViewName("redirect:/booklist/1");
			}
			else{
				bean.setCode(1);
				bean.setMsg("图片为空，请上传图片");
			}
		}
		//return mv;
		return bean;
	}
	
	//删除图书
	@RequestMapping(value = "/deletebook",method=RequestMethod.POST)
	public @ResponseBody JsonBean deletebook(int id){
		//获取图书
		JsonBean bean = new JsonBean();
		List<OrderItem> item = Service.findOrderItemsByBookId(id);
		System.out.println("Hello");
		int len = item.size();
		//有订单项，不可删除
		if(len!=0)
		{
			bean.setCode(1);
			bean.setMsg("该图书有订单项，不可删除");
			System.out.println("World");
		}
		else {
			try {
				//获取图书
				Book book = Service.findBookById(id);
				//添加删除标记
				book.setIsdel(1);
				//保存
				Service.modifyBook(book);
				bean.setCode(2);
				bean.setMsg("删除成功");
				System.out.println("Success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bean.setCode(3);
				bean.setMsg("删除时出现错误");
				System.out.println("Error");
			}
		}
		System.out.println("Finish");
		return bean;
	}
	
	//显示修改图书
	@RequestMapping(value = "/updatebook")
	public ModelAndView updatebook(ModelAndView mv,int id){
		Book book = Service.findBookById(id);
		System.out.println(id);
		mv.addObject("book", book);
		mv.setViewName("book/updatebook");
		return mv;
	}
	
	//修改图书
	@RequestMapping(value = "/update")
	public ModelAndView update(ModelAndView mv,@ModelAttribute Book book){

		System.out.println(book.getName());
		System.out.println(book.getQuantity());
		Service.modifyBook(book);
		mv.setViewName("redirect:/booklist/1");
		return mv;
	}
	
	
/*	//获取全部图书
	@RequestMapping(value = "/findall",method=RequestMethod.GET)
	public @ResponseBody JsonBean searchbook(){
		JsonBean bean = new JsonBean();
		List<Book> books = Service.findAllBook();
		bean.setMsg(books);
		bean.setCode(1);
		return bean;
	}*/
}
