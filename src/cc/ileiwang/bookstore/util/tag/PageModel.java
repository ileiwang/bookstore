package cc.ileiwang.bookstore.util.tag;
import cc.ileiwang.bookstore.util.common.BSConstants;
/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年5月8日 下午5:44:13
*/
public class PageModel {
	//记录总数
	private int recordCount;
	//标签页
	private int pageIndex ;
	//每页显示多少记录
	private int pageSize = BSConstants.PAGE_DEFAULT_SIZE = 5;
	//总页数
	private int totalSize;
	
	//获得总记录数
	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
		return recordCount;
	}
	//设置总记录数
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	
	//获得页号
	public int getPageIndex() {
		//只能从第一页显示，若页号小于对应0，应改为1
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		//若页数大于总页数，页号之只能为最后一页
		this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;
		return pageIndex;
	}
	//设置页号
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
	//获得每页显示记录数
	public int getPageSize() {
		//若每页显示的记录比默认少，按照默认大小分页
		this.pageSize = this.pageSize <= BSConstants.PAGE_DEFAULT_SIZE?BSConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//获得总页数
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
		//SQL查询起始记录号
		return (this.getPageIndex()-1)*this.getPageSize() ;
	}
}
