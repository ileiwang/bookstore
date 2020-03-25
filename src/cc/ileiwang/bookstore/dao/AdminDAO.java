package cc.ileiwang.bookstore.dao;

import static cc.ileiwang.bookstore.util.common.BSConstants.ADMINTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cc.ileiwang.bookstore.domain.Admin;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:50:18
*/
public interface AdminDAO {
	// 选择全部管理员
	@Select("select * from " + ADMINTABLE)
	@Results({ 
			@Result(id = true, column = "id", property = "id"), 
			@Result(column = "username", property = "username"),
			@Result(column = "password", property = "password"), 
			@Result(column = "email", property = "email")
			})
	List<Admin> selectAll();

	// 根据ID选择管理员
	@Select("select * from " + ADMINTABLE + " where id = #{id}")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email")
		})
	Admin selectById(int id);

	// 根据用户名选择管理员
	@Select("select * from " + ADMINTABLE + " where username = '${username}'")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email")
		})
	Admin selectByUsername(@Param("username") String username);

	// 根据用户名密码查询
	@Select("select * from " + ADMINTABLE + " where username = '${username}' and password = '${password}'")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email")
		})
	Admin selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	// 根据ID删除管理员
	@Delete("delete from " + ADMINTABLE + " where id = #{id} ")
	void deleteById(int id);
	
	//根据用户名删除管理员
	@Delete("delete from " + ADMINTABLE + " where username = '#{username}' ")
	void deleteByUsername(String username);

}
