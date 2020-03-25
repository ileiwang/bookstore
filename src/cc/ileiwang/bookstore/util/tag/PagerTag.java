package cc.ileiwang.bookstore.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Lei Wang
 * @email ileiwang@live.com
 * @blog www.ileiwang.cc
 * @version 2018年5月8日 下午5:44:40
 */
public class PagerTag extends SimpleTagSupport {

	//URL占位符
	private static final String TAG = "{0}";

	//当前页码
	private int pageIndex;
	
	//每页显示记录数
	private int pageSize;
	
	//总记录数
	private int recordCount;
	
	//请求URL的样式
	//例如："${ctx}/booklistbypage/{0}"
	private String submitUrl;
	
	//样式
	private String style = "sabrosus";

	//总页数
	private int totalPage = 0;

	//在页面上引用自定义标签
	//触发一个标签处理类
	@Override
	public void doTag() throws JspException, IOException {
		//拼接最终结果
		StringBuilder res = new StringBuilder();
		
		//拼接中间页码
		StringBuilder str = new StringBuilder();
		
		//有记录
		if (recordCount > 0) {
			
			//需要分的页数
			totalPage = (this.recordCount - 1) / this.pageSize + 1;

			//当前为第一页
			if (this.pageIndex == 1) {
				
				//上一页禁用
				str.append("<span class='disabled'><font size='3'>&lt;</font></span>");
				str.append("<span class='disabled'><font size='3'>首页</font></span>");
				//计算中间页码
				this.calcPage(str);

				//若为最后一页，且只有一页
				if (this.pageIndex == totalPage){
					//下一页禁用
					str.append("<span class='disabled'><font size='3'>尾页</font></span>");
					str.append("<span class='disabled'><font size='3'>&gt;</font></span>");
				}
				
				//若有多页
				else {
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
					str.append("<a href='" + tempUrl + "'><font size='3'>尾页</font></a>");
					tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					str.append("<a href='" + tempUrl + "'><font size='3'>&gt;</font></a>");
				}
			} 
			
			//当前为最后一页
			else if (this.pageIndex == totalPage) {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='" + tempUrl + "'><font size='3'>&lt;</font></a>");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>首页</font></a>");
				
				this.calcPage(str);
				
				str.append("<span class='disabled'><font size='3'>尾页</font></span>");
				str.append("<span class='disabled'><font size='3'>&gt;</font></span>");
			} 
			
			
			
			//当前为中间页面
			else {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='" + tempUrl + "'><font size='3'>&lt;</font></a>");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>首页</font></a>");
				
				this.calcPage(str);
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
				str.append("<a href='" + tempUrl + "'><font size='3'>尾页</font></a>");
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				str.append("<a href='" + tempUrl + "'><font size='3'>&gt;</font></a>");
			}

			res.append("<table width='100%' align='center' style='font-size:13px;' class='" + style + "'>");
			//分页信息
			res.append("<tr><td>"+ str.toString()+"</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td>");
			//开始条数
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			//结束条数
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;
			res.append(
					"总共<font size='5'>" + this.recordCount + "</font>条记录，当前显示<font size='5'>" + startNum + "</font>至<font size='5'>" + endNum + "</font>条记录");
			res.append("</td></tr>");
			res.append("</table>");
		} 
		
		//无记录
		else {
			res.append("<table align='center' style='font-size:16px;'><tr><td>查询到0条记录</td></tr></table>");
		}
		
		//输出到页面
		this.getJspContext().getOut().print(res.toString());
	}

	//计算中间页码
	private void calcPage(StringBuilder str) {
		//判断总页数
		if (this.totalPage <= 11) {
			//若小于11页，全部显示
			for (int i = 1; i <= this.totalPage; i++) {
				if (this.pageIndex == i) {
					//当前页
					str.append("<span class='current'><font size='3'>" + i + "</font></span>&nbsp;");
				} 
				else {
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='" + tempUrl + "'><font size='3'>" + i + "</font></a>&nbsp;");
				}
			}
		} 
		else 
		{
			//靠近首页
			if (this.pageIndex <= 8) {
				for (int i = 1; i <= 10; i++) {
					if (this.pageIndex == i) {
						//当前页
						str.append("<span class='current'><font size='3'>" + i + "</font></span>&nbsp;");
					} else {
						String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='" + tempUrl + "'><font size='3'>" + i + "</font></a>&nbsp;");
					}
				}
				str.append("<font size='3'>...</font>&nbsp;");
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='" + tempUrl + "'><font size='3'>" + this.totalPage + "</font></a>&nbsp;");
			}
			//靠近尾页
			else if (this.pageIndex + 8 >= this.totalPage) {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>1</font></a>&nbsp;");
				str.append("<font size='3'>...</font>&nbsp;");

				for (int i = this.totalPage - 10; i <= this.totalPage; i++) {
					if (this.pageIndex == i) {
						//当前页
						str.append("<span class='current'><font size='3'>" + i + "</font></span>&nbsp;");
					} else {
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='" + tempUrl + "'><font size='3'>" + i + "</font></a>&nbsp;");
					}
				}
			}
			//在中间
			else {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>1</font></a>&nbsp;");
				str.append("<font size='3'>...</font>&nbsp;");

				for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++) {
					if (this.pageIndex == i) {
						//当前页
						str.append("<span class='current'><font size='3'>" + i + "</font></span>&nbsp;");
					} 
					else {
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='" + tempUrl + "'><font size='3'>" + i + "</font></a>&nbsp;");
					}
				}

				str.append("<font size='3'>...</font>&nbsp;");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='" + tempUrl + "'><font size='3'>" + this.totalPage + "</font></a>&nbsp;");
			}
		}
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
