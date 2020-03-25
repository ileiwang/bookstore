package cc.ileiwang.bookstore.dao;

import static cc.ileiwang.bookstore.util.common.BSConstants.ORDERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cc.ileiwang.bookstore.dao.provider.OrderDAOProvider;
import cc.ileiwang.bookstore.domain.Order;
import cc.ileiwang.bookstore.util.common.BSConstants;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:50:28
*/
public interface OrderDAO {
	@SelectProvider(type=OrderDAOProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="amount",property="amount"),
		@Result(column="createdate",property="createdate",javaType = java.util.Date.class),
		@Result(column = "id", property = "orderitems", 
		many = @Many(select = "cc.ileiwang.bookstore.dao.OrderItemDAO.selectByOrderId", 
		fetchType = FetchType.LAZY)),
		@Result(column = "user_id", property = "user", 
		one = @One(select = "cc.ileiwang.bookstore.dao.UserDAO.selectById", 
		fetchType = FetchType.EAGER)),
		@Result(column="state",property="state")
	})
	List<Order> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=OrderDAOProvider.class,method="countOrder")
	int count(Map<String, Object> params);

	@SelectProvider(type=OrderDAOProvider.class,method="insertOrder")
	void save(Order order);
	
	@SelectProvider(type=OrderDAOProvider.class,method="updateOrder")
	void update(Order order);
	
	@Select("select * from "+ORDERTABLE)
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="amount",property="amount"),
		@Result(column="createdate",property="createdate",javaType = java.util.Date.class),
		@Result(column = "id", property = "orderitems", 
		many = @Many(select = "cc.ileiwang.bookstore.dao.OrderItemDAO.selectByOrderId", 
		fetchType = FetchType.LAZY)),
		@Result(column = "user_id", property = "user", 
		one = @One(select = "cc.ileiwang.bookstore.dao.UserDAO.selectById", 
		fetchType = FetchType.EAGER)),
		@Result(column="state",property="state")
	})
	List<Order> selectAll();
	
	@Select("select * from "+ORDERTABLE+" where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="amount",property="amount"),
		@Result(column="createdate",property="createdate",javaType = java.util.Date.class),
		@Result(column = "id", property = "orderitems", 
		many = @Many(select = "cc.ileiwang.bookstore.dao.OrderItemDAO.selectByOrderId", 
		fetchType = FetchType.LAZY)),
		@Result(column = "user_id", property = "user", 
		one = @One(select = "cc.ileiwang.bookstore.dao.UserDAO.selectById", 
		fetchType = FetchType.EAGER)),
		@Result(column="state",property="state")
	})
	Order selectById(int id);
	
	@Select("select * from "+ORDERTABLE+" where user_id = #{user_id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="amount",property="amount"),
		@Result(column="createdate",property="createdate",javaType = java.util.Date.class),
		@Result(column = "id", property = "orderitems", 
		many = @Many(select = "cc.ileiwang.bookstore.dao.OrderItemDAO.selectByOrderId", 
		fetchType = FetchType.LAZY)),
		@Result(column = "user_id", property = "user", 
		one = @One(select = "cc.ileiwang.bookstore.dao.UserDAO.selectById", 
		fetchType = FetchType.EAGER)),
		@Result(column="state",property="state")
	})
	List<Order> selectByUserId(int user_id);
	
	@Select("select * from "+ORDERTABLE+" where user_id = ${user_id} and amount = ${amount}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="amount",property="amount"),
		@Result(column="createdate",property="createdate",javaType = java.util.Date.class),
		@Result(column = "id", property = "orderitems", 
		many = @Many(select = "cc.ileiwang.bookstore.dao.OrderItemDAO.selectByOrderId", 
		fetchType = FetchType.LAZY)),
		@Result(column = "user_id", property = "user", 
		one = @One(select = "cc.ileiwang.bookstore.dao.UserDAO.selectById", 
		fetchType = FetchType.EAGER)),
		@Result(column="state",property="state")
	})
	Order selectByUserIdAndAmount(@Param("user_id")int user_id,@Param("amount")int amount);
	
	
	
	//删除操作
	@Delete("delete from "+ORDERTABLE+" where id = #{id} ")
	void deleteById(int id);
	@Delete("delete from "+ORDERTABLE+" where user_id = #{user_id} ")
	void deleteByUserId(int user_id);
	
	@Update("update "+ORDERTABLE+" set state = "+BSConstants.ORDERSTATE_YFHWQS+" where id = #{id}")
	void send(int id);
	
	@Update("update "+ORDERTABLE+" set state = "+BSConstants.ORDERSTATE_YTH+" where id = #{id}")
	void approveback(int id);
	
	@Update("update "+ORDERTABLE+" set state = "+BSConstants.ORDERSTATE_YQS+" where id = #{id}")
	void receive(int id);
	
	@Update("update "+ORDERTABLE+" set state = "+BSConstants.ORDERSTATE_ZZTH+" where id = #{id}")
	void applyback(int id);

}
