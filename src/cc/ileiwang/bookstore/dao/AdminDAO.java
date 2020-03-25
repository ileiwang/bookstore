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
* @version 2018��7��11�� ����3:50:18
*/
public interface AdminDAO {
	// ѡ��ȫ������Ա
	@Select("select * from " + ADMINTABLE)
	@Results({ 
			@Result(id = true, column = "id", property = "id"), 
			@Result(column = "username", property = "username"),
			@Result(column = "password", property = "password"), 
			@Result(column = "email", property = "email")
			})
	List<Admin> selectAll();

	// ����IDѡ�����Ա
	@Select("select * from " + ADMINTABLE + " where id = #{id}")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email")
		})
	Admin selectById(int id);

	// �����û���ѡ�����Ա
	@Select("select * from " + ADMINTABLE + " where username = '${username}'")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email")
		})
	Admin selectByUsername(@Param("username") String username);

	// �����û��������ѯ
	@Select("select * from " + ADMINTABLE + " where username = '${username}' and password = '${password}'")
	@Results({ 
		@Result(id = true, column = "id", property = "id"), 
		@Result(column = "username", property = "username"),
		@Result(column = "password", property = "password"), 
		@Result(column = "email", property = "email")
		})
	Admin selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	// ����IDɾ������Ա
	@Delete("delete from " + ADMINTABLE + " where id = #{id} ")
	void deleteById(int id);
	
	//�����û���ɾ������Ա
	@Delete("delete from " + ADMINTABLE + " where username = '#{username}' ")
	void deleteByUsername(String username);

}
