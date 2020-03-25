package cc.ileiwang.bookstore.dao;

import static cc.ileiwang.bookstore.util.common.BSConstants.USERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import cc.ileiwang.bookstore.dao.provider.UserDAOProvider;
import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.util.common.BSConstants;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:50:11
*/
public interface UserDAO {
	// 根据页码选择学生（分页使用）
	@SelectProvider(type = UserDAOProvider.class, method = "selectWhitParam")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email"),
		@Result(column = "id", property = "orders", 
		many = @Many(
				select = "cc.ileiwang.bookstore.dao.OrderDAO.selectByUserId", 
				fetchType = FetchType.LAZY)),
		@Result(column = "islocked", property = "islocked"),
		@Result(column = "errorcount", property = "errorcount")
		})
	List<User> selectByPage(Map<String, Object> params);

	// 统计学生数
	@SelectProvider(type = UserDAOProvider.class, method = "countUser")
	int count(Map<String, Object> params);

	// 添加
	@SelectProvider(type = UserDAOProvider.class, method = "insertUser")
	void save(User user);

	// 更新
	@SelectProvider(type = UserDAOProvider.class, method = "updateUser")
	void update(User user);
	// 选择全部用户
	@Select("select * from " + USERTABLE)
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email"),
		@Result(column = "id", property = "orders", 
		many = @Many(
				select = "cc.ileiwang.bookstore.dao.OrderDAO.selectByUserId", 
				fetchType = FetchType.LAZY)),
		@Result(column = "islocked", property = "islocked"),
		@Result(column = "errorcount", property = "errorcount")
		})
	List<User> selectAll();

	// 根据ID选择用户
	@Select("select * from " + USERTABLE + " where id = #{id}")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email"),
		@Result(column = "id", property = "orders", 
		many = @Many(
				select = "cc.ileiwang.bookstore.dao.OrderDAO.selectByUserId", 
				fetchType = FetchType.LAZY)),
		@Result(column = "islocked", property = "islocked"),
		@Result(column = "errorcount", property = "errorcount")
		})
	User selectById(int id);
	
	// 根据订单号选择用户
	@Select("select * from " + USERTABLE + " where id in (select user_id from order where id = #{id})")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email"),
		@Result(column = "id", property = "orders", 
		many = @Many(
				select = "cc.ileiwang.bookstore.dao.OrderDAO.selectByUserId", 
				fetchType = FetchType.LAZY)),
		@Result(column = "islocked", property = "islocked"),
		@Result(column = "errorcount", property = "errorcount")
		})
	User selectByOrderId(@Param("id") int id);
	
	// 根据姓名选择用户
	@Select("select * from " + USERTABLE + " where username = '${username}'")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email"),
		@Result(column = "id", property = "orders", 
		many = @Many(
				select = "cc.ileiwang.bookstore.dao.OrderDAO.selectByUserId", 
				fetchType = FetchType.LAZY)),
		@Result(column = "islocked", property = "islocked"),
		@Result(column = "errorcount", property = "errorcount")
		})
	User selectByUsername(@Param("username") String username);

	// 根据学号密码查询
	@Select("select * from " + USERTABLE + " where username = '${username}' and password = '${password}'")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email"),
		@Result(column = "id", property = "orders", 
		many = @Many(
				select = "cc.ileiwang.bookstore.dao.OrderDAO.selectByUserId", 
				fetchType = FetchType.LAZY)),
		@Result(column = "islocked", property = "islocked"),
		@Result(column = "errorcount", property = "errorcount")
		})
	User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	// 根据ID删除用户
	@Delete("delete from " + USERTABLE + " where id = #{id} ")
	void deleteById(int id);

	
	@Delete("delete from " + USERTABLE + " where username = '#{username}' ")
	void deleteByUsername(String username);
	
	//解锁
	@Update("update "+USERTABLE+" set islocked = "+BSConstants.USERSTATE_UNLOCKED+" where id = #{id}")
	void unlock(int id);
	
	//加锁
	@Update("update "+USERTABLE+" set islocked = "+BSConstants.USERSTATE_LOCKED+" where id = #{id}")
	void lock(int id);
	
	@Update("update " + USERTABLE + " set password = '${password}' where id = ${user_id}")
	void changepasswd(@Param("user_id")int user_id, @Param("password")String password);

	@Select("select password from " + USERTABLE + " where username = '${username}' and email = '${email}'")
	String findpasswd(@Param("username") String username,@Param("email")String email);

}
