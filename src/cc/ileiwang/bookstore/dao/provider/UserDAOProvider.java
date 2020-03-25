package cc.ileiwang.bookstore.dao.provider;

import static cc.ileiwang.bookstore.util.common.BSConstants.USERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cc.ileiwang.bookstore.domain.User;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:51:26
*/
public class UserDAOProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(USERTABLE);
				if (params.get("user") != null) {
					User user = (User) params.get("user");
					if (user.getId() != 0) {
						WHERE(" id LIKE CONCAT ('%',#{user.id},'%') ");
					}
					if (user.getUsername() != null && !user.getUsername().equals("")) {
						WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
					}

					if (user.getPassword() != null && !user.getPassword().equals("")) {
						WHERE(" password LIKE CONCAT ('%',#{user.password},'%') ");
					}

					if (user.getEmail() != null && !user.getEmail().equals("")) {
						WHERE(" email LIKE CONCAT ('%',#{user.email},'%') ");
					}

/*					if (user.getIslocked() == 0||user.getIslocked() == 1) {
						WHERE(" islocked LIKE CONCAT ('%',#{user.islocked},'%') ");
					}
					if (user.getErrorcount() <=3) {
						WHERE(" errorcount LIKE CONCAT ('%',#{user.errorcount},'%') ");
					}*/
				}
			}
		}.toString();

		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}

	// 动态查询总数量
	public String countUser(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if (params.get("user") != null) {
					User user = (User) params.get("user");
					if (user.getId() != 0) {
						WHERE(" id LIKE CONCAT ('%',#{user.id},'%') ");
					}

					if (user.getUsername() != null && !user.getUsername().equals("")) {
						WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
					}

					if (user.getPassword() != null && !user.getPassword().equals("")) {
						WHERE(" password LIKE CONCAT ('%',#{user.password},'%') ");
					}

					if (user.getEmail() != null && !user.getEmail().equals("")) {
						WHERE(" email LIKE CONCAT ('%',#{user.email},'%') ");
					}

/*					if (user.getIslocked() == 0||user.getIslocked() == 1) {
						WHERE(" islocked LIKE CONCAT ('%',#{user.islocked},'%') ");
					}
					if (user.getErrorcount() <=3) {
						WHERE(" errorcount LIKE CONCAT ('%',#{user.errorcount},'%') ");
					}*/
				}
			}
		}.toString();
	}

	// 动态插入
	public String insertUser(User user) {

		return new SQL() {
			{
				INSERT_INTO(USERTABLE);
				if (user.getUsername() != null && !user.getUsername().equals("")) {
					VALUES("username", "#{username}");
				}

				if (user.getPassword() != null && !user.getPassword().equals("")) {
					VALUES("password", "#{password}");
				}

				if (user.getEmail() != null && !user.getEmail().equals("")) {
					VALUES("email", "#{email}");
				}

				if(user.getIslocked() == 0||user.getIslocked() == 1) {
					VALUES("islocked", "#{islocked}");
				}
				if (user.getErrorcount() <=3) {
					VALUES("errorcount","#{errorcount}");
				}
			}
		}.toString();
	}
	
	// 动态更新
	public String updateUser(User user) {

		return new SQL() {
			{
				UPDATE(USERTABLE);
				if (user.getUsername() != null && !user.getUsername().equals("")) {
					SET("username = #{username}");
				}

				if (user.getPassword() != null && !user.getPassword().equals("")) {
					SET("password = #{password}");
				}


				if (user.getEmail() != null && !user.getEmail().equals("")) {
					SET("email = #{email}");
				}

/*				if(user.getIslocked() == 0||user.getIslocked() == 1) {
					SET("islocked = #{islocked}");
				}*/
				if (user.getErrorcount() <=3) {
					if(user.getErrorcount() <3) {
						SET("errorcount = #{errorcount}");
						SET("islocked = 0");
					}
					else {
						SET("errorcount = #{errorcount}");
						SET("islocked = 1");
					}

				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
