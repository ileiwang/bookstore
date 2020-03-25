package cc.ileiwang.bookstore.dao;

import static cc.ileiwang.bookstore.util.common.BSConstants.BOOKTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import cc.ileiwang.bookstore.dao.provider.BookDAOProvider;
import cc.ileiwang.bookstore.domain.Book;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:43:06
*/
public interface BookDAO {
	@SelectProvider(type=BookDAOProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="price",property="price"),
		@Result(column="quantity",property="quantity"),
		@Result(column="img",property="img"),
		@Result(column="isdel",property="isdel"),
		@Result(column="id",property="orderitems",
		many=@Many(
				select="cc.ileiwang.bookstore.dao.OrderItemDAO.selectByBookId",
				fetchType=FetchType.LAZY))
	})
	List<Book> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=BookDAOProvider.class,method="countBook")
	int count(Map<String, Object> params);

	@SelectProvider(type=BookDAOProvider.class,method="insertBook")
	void save(Book book);
	
	@SelectProvider(type=BookDAOProvider.class,method="updateBook")
	void update(Book book);
	
	@Select("select * from "+BOOKTABLE)
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="price",property="price"),
		@Result(column="quantity",property="quantity"),
		@Result(column="img",property="img"),
		@Result(column="isdel",property="isdel"),
		@Result(column="id",property="orderitems",
		many=@Many(
				select="cc.ileiwang.bookstore.dao.OrderItemDAO.selectByBookId",
				fetchType=FetchType.LAZY))
	})
	List<Book> selectAll();
	
		
	@Select("select * from "+BOOKTABLE+" where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="price",property="price"),
		@Result(column="quantity",property="quantity"),
		@Result(column="img",property="img"),
		@Result(column="isdel",property="isdel"),
		@Result(column="id",property="orderitems",
		many=@Many(
				select="cc.ileiwang.bookstore.dao.OrderItemDAO.selectByBookId",
				fetchType=FetchType.LAZY))
	})
	Book selectById(int id);
	
	@Select("select * from "+BOOKTABLE+" where name = #{name}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="price",property="price"),
		@Result(column="quantity",property="quantity"),
		@Result(column="img",property="img"),
		@Result(column="isdel",property="isdel"),
		@Result(column="id",property="orderitems",
		many=@Many(
				select="cc.ileiwang.bookstore.dao.OrderItemDAO.selectByBookId",
				fetchType=FetchType.LAZY))
	})
	Book selectByName(String name);
	
	//删除
	@Delete("delete from "+BOOKTABLE+" where id = #{id} ")
	void deleteById(int id);
	@Delete("delete from "+BOOKTABLE+" where name = #{name} ")
	void deleteByName(String name);

}
