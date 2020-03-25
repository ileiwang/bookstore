package cc.ileiwang.bookstore.dao;

import static cc.ileiwang.bookstore.util.common.BSConstants.ORDERITEMTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import cc.ileiwang.bookstore.dao.provider.OrderItemDAOProvider;
import cc.ileiwang.bookstore.domain.OrderItem;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:51:01
*/
public interface OrderItemDAO {
	@SelectProvider(type=OrderItemDAOProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="quantity",property="quantity"),
		@Result(column="book_id",property="book",
				one = @One(select = "cc.ileiwang.bookstore.dao.BookDAO.selectById", 
				fetchType = FetchType.EAGER)),
		@Result(column="order_id",property="order",
				one = @One(select = "cc.ileiwang.bookstore.dao.OrderDAO.selectById", 
				fetchType = FetchType.EAGER))
	})
	List<OrderItem> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=OrderItemDAOProvider.class,method="countOrderItem")
	int count(Map<String, Object> params);

	@SelectProvider(type=OrderItemDAOProvider.class,method="insertOrderItem")
	void save(OrderItem orderitem);
	
	@SelectProvider(type=OrderItemDAOProvider.class,method="updateOrderItem")
	void update(OrderItem orderitem);
	
	@Select("select * from "+ORDERITEMTABLE)
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="quantity",property="quantity"),
		@Result(column="book_id",property="book",
				one = @One(select = "cc.ileiwang.bookstore.dao.BookDAO.selectById", 
				fetchType = FetchType.EAGER)),
		@Result(column="order_id",property="order",
				one = @One(select = "cc.ileiwang.bookstore.dao.OrderDAO.selectById", 
				fetchType = FetchType.EAGER))
	})
	List<OrderItem> selectAll();
	
	@Select("select * from "+ORDERITEMTABLE+" where order_id = #{order_id} and book_id = #{book_id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="quantity",property="quantity"),
		@Result(column="book_id",property="book",
				one = @One(select = "cc.ileiwang.bookstore.dao.BookDAO.selectById", 
				fetchType = FetchType.EAGER)),
		@Result(column="order_id",property="order",
				one = @One(select = "cc.ileiwang.bookstore.dao.OrderDAO.selectById", 
				fetchType = FetchType.EAGER))
	})
	List<OrderItem> selectByOrderIdAndBookId(int order_id,int book_id);
	
	@Select("select * from "+ORDERITEMTABLE+" where book_id = #{book_id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="quantity",property="quantity"),
		@Result(column="book_id",property="book",
				one = @One(select = "cc.ileiwang.bookstore.dao.BookDAO.selectById", 
				fetchType = FetchType.EAGER)),
		@Result(column="order_id",property="order",
				one = @One(select = "cc.ileiwang.bookstore.dao.OrderDAO.selectById", 
				fetchType = FetchType.EAGER))
	})
	List<OrderItem> selectByBookId(int book_id);//一本图书的所有购买记录
	
	@Select("select * from "+ORDERITEMTABLE+" where order_id = #{order_id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="quantity",property="quantity"),
		@Result(column="book_id",property="book",
				one = @One(select = "cc.ileiwang.bookstore.dao.BookDAO.selectById", 
				fetchType = FetchType.EAGER)),
		@Result(column="order_id",property="order",
				one = @One(select = "cc.ileiwang.bookstore.dao.OrderDAO.selectById", 
				fetchType = FetchType.EAGER))
	})
	List<OrderItem> selectByOrderId(int order_id);//一个订单的所有购买记录
	
	//删除操作
	@Delete("delete from "+ORDERITEMTABLE+" where order_id = #{order_id} ")
	void deleteByOrderId(int order_id);
	@Delete("delete from "+ORDERITEMTABLE+" where book_id = #{book_id} ")
	void deleteByBookId(int book_id);
	@Delete("delete from "+ORDERITEMTABLE+" where order_id = #{order_id} and book_id = #{book_id}")
	void deleteByOrderIdAndBookId(int order_id,int book_id);

}
