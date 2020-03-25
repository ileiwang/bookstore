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
* @version 2018��7��11�� ����4:59:22
*/
@Controller
public class FormController {
	
	@Autowired
	@Qualifier("Service")
	private BookStoreService Service;
	
	@RequestMapping(value = "/{formName}")
	public String formName(@PathVariable String formName,HttpSession session) {
		
		//��ֹδ�˳�������¼��ֻ�е��û��˳�ʱ�����ܴ򿪵�¼ҳ
		if(formName.equals("loginForm")&&((session.getAttribute("user")!=null)||(session.getAttribute("admin")!=null))){
			return "main";
		}
		
		//ĳͼ����빺�ﳵ����ת�����ﳵҳ����ͼ����Ӧ�ü��٣�����������ҳʱ��ҳ����ϢӦ�ø��£����Ҫˢ��ҳ��
		//���»�ȡ
		//Ȼ����booklistbypageÿ�β�ѯ��ĳһҳ��Ҳ����ת����ҳ������ת����ҳ��Ҫˢ��ҳ�棬���²�ѯ����ĳһҳ���ݣ�ֻ����ʾ��һҳ
		//���ǣ�������session���pageModel���䱣��������/booklistbypageʱ�����ҳ�ţ�������������У�Ҳ�Ǹ���pageModel
		//���pageIndex����ѯ�������ʹﵽ�˼�����ͼ����Ϣ���ֿ���ʵ�ַ�ҳ���ҡ�
		//���紦�ڵڶ�ҳʱ��ѡ����Ʒ���빺�ﳵ����ת�����ﳵҳ�棬�������뷵�أ������ҳ�󣬻��ܷ��ص��ڶ�ҳ�������ǵ�һҳ��
		//���ǵ�ע���Ժ�session�������Ϣ��ʧ������¼��ҲҪ��ת��main���ʻ���ֿ�ָ�����
		//�ʼ����жϴ���
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
			//�������ᵼ�������໥����
			//return "redirect:/booklistbypage";
		}
		else {
			return formName;
		}
			
	}
}
