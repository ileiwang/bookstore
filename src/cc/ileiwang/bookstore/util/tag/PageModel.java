package cc.ileiwang.bookstore.util.tag;
import cc.ileiwang.bookstore.util.common.BSConstants;
/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018��5��8�� ����5:44:13
*/
public class PageModel {
	//��¼����
	private int recordCount;
	//��ǩҳ
	private int pageIndex ;
	//ÿҳ��ʾ���ټ�¼
	private int pageSize = BSConstants.PAGE_DEFAULT_SIZE = 5;
	//��ҳ��
	private int totalSize;
	
	//����ܼ�¼��
	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
		return recordCount;
	}
	//�����ܼ�¼��
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	
	//���ҳ��
	public int getPageIndex() {
		//ֻ�ܴӵ�һҳ��ʾ����ҳ��С�ڶ�Ӧ0��Ӧ��Ϊ1
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		//��ҳ��������ҳ����ҳ��ֻ֮��Ϊ���һҳ
		this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;
		return pageIndex;
	}
	//����ҳ��
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
	//���ÿҳ��ʾ��¼��
	public int getPageSize() {
		//��ÿҳ��ʾ�ļ�¼��Ĭ���٣�����Ĭ�ϴ�С��ҳ
		this.pageSize = this.pageSize <= BSConstants.PAGE_DEFAULT_SIZE?BSConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//�����ҳ��
	public int getTotalSize() {
		if(this.getRecordCount() <=0){
			totalSize = 0 ;
		}else{
			totalSize = (this.getRecordCount() -1)/this.getPageSize() + 1;
		}
		return totalSize;
	}
	public int getFirstLimitParam(){
		//0 1 2 3 4
		//5 6 7 8 9
		//SQL��ѯ��ʼ��¼��
		return (this.getPageIndex()-1)*this.getPageSize() ;
	}
}
