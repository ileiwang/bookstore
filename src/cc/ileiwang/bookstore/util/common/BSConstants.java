package cc.ileiwang.bookstore.util.common;
/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:57:15
*/
public class BSConstants {
	public static final String ADMINTABLE = "admin";
	public static final String BOOKTABLE = "book";
	public static final String ORDERTABLE = "ordert";
	public static final String USERTABLE = "user";
	public static final String ORDERITEMTABLE = "orderitem";
	
	public static final String LOGIN = "loginForm";
	public static final String REGISTER = "registerForm";
	
	public static final String USERSESSION = "user";
	public static final String ADMINSESSION = "admin";
	
	public static int PAGE_DEFAULT_SIZE = 5;
	
	public static final int ORDERSTATE_WFH = 0;
	public static final int ORDERSTATE_YFHWQS = 1;
	public static final int ORDERSTATE_YQS = 2;
	public static final int ORDERSTATE_ZZTH = 3;
	public static final int ORDERSTATE_YTH = 4;
	
	public static final int BOOKSTATE_NOTDEL = 0;
	public static final int BOOKSTATE_DEL = 1;
	
	public static final int USERSTATE_UNLOCKED = 0;
	public static final int USERSTATE_LOCKED = 1;

}
