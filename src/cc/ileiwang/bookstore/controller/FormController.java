package cc.ileiwang.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.ileiwang.bookstore.domain.Book;
import cc.ileiwang.bookstore.service.BookStoreService;
import cc.ileiwang.bookstore.util.tag.PageModel;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午4:59:22
*/
@Controller
public class FormController {
	
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	@RequestMapping(value = "/{formName}")
	public String formName(@PathVariable String formName,HttpSession session) {
		
		//防止未退出继续登录，只有当用户退出时，才能打开登录页
		if(formName.equals("loginForm")&&((session.getAttribute("user")!=null)||(session.getAttribute("admin")!=null))){
			return "main";
		}
		
		//某图书加入购物车后，跳转到购物车页，该图书库存应该减少，即当返回首页时，页面信息应该更新，因此要刷新页面
		//重新获取
		//然而，booklistbypage每次查询到某一页，也是跳转到首页，而跳转到首页又要刷新页面，导致查询不到某一页数据，只能显示第一页
		//但是，借助于session里的pageModel，其保存了请求/booklistbypage时传入的页号，故在下面代码中，也是根据pageModel
		//里的pageIndex来查询，这样就达到了即更新图书信息，又可以实现分页查找。
		//比如处在第二页时，选择物品加入购物车后跳转到购物车页面，但是又想返回，点击首页后，还能返回到第二页，而不是第一页。
		//但是当注销以后，session里面的信息丢失，而登录后也要跳转到main，故会出现空指针错误
		//故加入判断代码
		if(formName.equals("main"))
		{

			if(session.getAttribute("pageModel")!=null) {
				Book book = new Book();
				book.setIsdel(0);
				PageModel pageModel = (PageModel)session.getAttribute("pageModel");
				List<Book> books = Service.findBook(book, pageModel);
				session.setAttribute("books", books);
			}
			return "main";
			//下面代码会导致无限相互请求
			//return "redirect:/booklistbypage";
		}
		else {
			return formName;
		}
			
	}
}
