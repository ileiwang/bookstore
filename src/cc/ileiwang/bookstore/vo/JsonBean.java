package cc.ileiwang.bookstore.vo;
/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月12日 上午8:26:17
*/
public class JsonBean {
	private int code;
	private Object msg;//接受字符串或者图书列表
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
}
