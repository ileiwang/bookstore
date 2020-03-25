package cc.ileiwang.bookstore.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Lei Wang
 * @email ileiwang@live.com
 * @blog www.ileiwang.cc
 * @version 2018��5��8�� ����5:44:40
 */
public class PagerTag extends SimpleTagSupport {

	//URLռλ��
	private static final String TAG = "{0}";

	//��ǰҳ��
	private int pageIndex;
	
	//ÿҳ��ʾ��¼��
	private int pageSize;
	
	//�ܼ�¼��
	private int recordCount;
	
	//����URL����ʽ
	//���磺"${ctx}/booklistbypage/{0}"
	private String submitUrl;
	
	//��ʽ
	private String style = "sabrosus";

	//��ҳ��
	private int totalPage = 0;

	//��ҳ���������Զ����ǩ
	//����һ����ǩ������
	@Override
	public void doTag() throws JspException, IOException {
		//ƴ�����ս��
		StringBuilder res = new StringBuilder();
		
		//ƴ���м�ҳ��
		StringBuilder str = new StringBuilder();
		
		//�м�¼
		if (recordCount > 0) {
			
			//��Ҫ�ֵ�ҳ��
			totalPage = (this.recordCount - 1) / this.pageSize + 1;

			//��ǰΪ��һҳ
			if (this.pageIndex == 1) {
				
				//��һҳ����
				str.append("<span class='disabled'><font size='3'>&lt;</font></span>");
				str.append("<span class='disabled'><font size='3'>��ҳ</font></span>");
				//�����м�ҳ��
				this.calcPage(str);

				//��Ϊ���һҳ����ֻ��һҳ
				if (this.pageIndex == totalPage){
					//��һҳ����
					str.append("<span class='disabled'><font size='3'>βҳ</font></span>");
					str.append("<span class='disabled'><font size='3'>&gt;</font></span>");
				}
				
				//���ж�ҳ
				else {
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
					str.append("<a href='" + tempUrl + "'><font size='3'>βҳ</font></a>");
					tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					str.append("<a href='" + tempUrl + "'><font size='3'>&gt;</font></a>");
				}
			} 
			
			//��ǰΪ���һҳ
			else if (this.pageIndex == totalPage) {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='" + tempUrl + "'><font size='3'>&lt;</font></a>");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>��ҳ</font></a>");
				
				this.calcPage(str);
				
				str.append("<span class='disabled'><font size='3'>βҳ</font></span>");
				str.append("<span class='disabled'><font size='3'>&gt;</font></span>");
			} 
			
			
			
			//��ǰΪ�м�ҳ��
			else {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='" + tempUrl + "'><font size='3'>&lt;</font></a>");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>��ҳ</font></a>");
				
				this.calcPage(str);
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
				str.append("<a href='" + tempUrl + "'><font size='3'>βҳ</font></a>");
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				str.append("<a href='" + tempUrl + "'><font size='3'>&gt;</font></a>");
			}

			res.append("<table width='100%' align='center' style='font-size:13px;' class='" + style + "'>");
			//��ҳ��Ϣ
			res.append("<tr><td>"+ str.toString()+"</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td>");
			//��ʼ����
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			//��������
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;
			res.append(
					"�ܹ�<font size='5'>" + this.recordCount + "</font>����¼����ǰ��ʾ<font size='5'>" + startNum + "</font>��<font size='5'>" + endNum + "</font>����¼");
			res.append("</td></tr>");
			res.append("</table>");
		} 
		
		//�޼�¼
		else {
			res.append("<table align='center' style='font-size:16px;'><tr><td>��ѯ��0����¼</td></tr></table>");
		}
		
		//�����ҳ��
		this.getJspContext().getOut().print(res.toString());
	}

	//�����м�ҳ��
	private void calcPage(StringBuilder str) {
		//�ж���ҳ��
		if (this.totalPage <= 11) {
			//��С��11ҳ��ȫ����ʾ
			for (int i = 1; i <= this.totalPage; i++) {
				if (this.pageIndex == i) {
					//��ǰҳ
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
			//������ҳ
			if (this.pageIndex <= 8) {
				for (int i = 1; i <= 10; i++) {
					if (this.pageIndex == i) {
						//��ǰҳ
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
			//����βҳ
			else if (this.pageIndex + 8 >= this.totalPage) {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>1</font></a>&nbsp;");
				str.append("<font size='3'>...</font>&nbsp;");

				for (int i = this.totalPage - 10; i <= this.totalPage; i++) {
					if (this.pageIndex == i) {
						//��ǰҳ
						str.append("<span class='current'><font size='3'>" + i + "</font></span>&nbsp;");
					} else {
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='" + tempUrl + "'><font size='3'>" + i + "</font></a>&nbsp;");
					}
				}
			}
			//���м�
			else {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='" + tempUrl + "'><font size='3'>1</font></a>&nbsp;");
				str.append("<font size='3'>...</font>&nbsp;");

				for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++) {
					if (this.pageIndex == i) {
						//��ǰҳ
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
