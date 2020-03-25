package cc.ileiwang.bookstore.dao.provider;

import static cc.ileiwang.bookstore.util.common.BSConstants.ADMINTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cc.ileiwang.bookstore.domain.Admin;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:51:51
*/
public class AdminDAOProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(ADMINTABLE);
				if (params.get("admin") != null) {
					Admin admin = (Admin) params.get("admin");
					if (admin.getUsername() != null && !admin.getUsername().equals("")) {
						WHERE("username LIKE CONCAT ('%',#{admin.username},'%') ");
					}
				}
			}
		}.toString();

		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}

	// 动态查询总数量
	public String countAdmin(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(ADMINTABLE);
				if (params.get("admin") != null) {
					Admin admin = (Admin) params.get("admin");
					if (admin.getUsername() != null && !admin.getUsername().equals("")) {
						WHERE("username LIKE CONCAT ('%',#{admin.username},'%') ");
					}
				}
			}
		}.toString();
	}

	// 动态插入
	public String insertAdmin(Admin admin) {
		return new SQL() {
			{
				INSERT_INTO(ADMINTABLE);
				if (admin.getUsername() != null && !admin.getUsername().equals("")) {
					VALUES("username", "#{username}");
				}
				if (admin.getPassword() != null && !admin.getPassword().equals("")) {
					VALUES("password", "#{password}");
				}
				if (admin.getEmail() != null && !admin.getEmail().equals("")) {
					VALUES("email", "#{email}");
				}
			}
		}.toString();
	}

	// 动态更新
	public String updateAdmin(Admin admin) {

		return new SQL() {
			{
				UPDATE(ADMINTABLE);
				if (admin.getUsername() != null && !admin.getUsername().equals("")) {
					SET("username = #{username}");
				}
				if (admin.getPassword() != null && !admin.getPassword().equals("")) {
					SET("password = #{password}");
				}
				if (admin.getEmail() != null && !admin.getEmail().equals("")) {
					SET("email = #{email}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
