package cc.ileiwang.bookstore.dao.provider;

import static cc.ileiwang.bookstore.util.common.BSConstants.ORDERTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cc.ileiwang.bookstore.domain.Order;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:51:37
*/
public class OrderDAOProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(ORDERTABLE);
				if (params.get("order") != null) {
					Order order = (Order) params.get("order");
					if (order.getId() != 0) {
						WHERE("id LIKE CONCAT ('%',#{order.id},'%') ");
					}
					if (order.getAmount()!=0) {
						WHERE("amount LIKE CONCAT ('%',#{order.amount},'%') ");
					}
					if (order.getCreatedate() != null) {
						WHERE("createdate LIKE CONCAT ('%',#{order.createdate},'%') ");
					}
					if (order.getUser() != null && order.getUser().getId()!=0) {
						WHERE("user_id LIKE CONCAT ('%',#{order.user.id},'%') ");
					}
/*					if (order.getState()<5) {
						WHERE("state LIKE CONCAT ('%',#{order.state},'%') ");
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
	public String countOrder(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(ORDERTABLE);
				if (params.get("order") != null) {
					Order order = (Order) params.get("order");
					if (order.getId() != 0) {
						WHERE("id LIKE CONCAT ('%',#{order.id},'%') ");
					}
					if (order.getAmount()!=0) {
						WHERE("amount LIKE CONCAT ('%',#{order.amount},'%') ");
					}
					if (order.getCreatedate() != null) {
						WHERE("createdate LIKE CONCAT ('%',#{order.createdate},'%') ");
					}
					if (order.getUser() != null && order.getUser().getId()!=0) {
						WHERE("user_id LIKE CONCAT ('%',#{order.user.id},'%') ");
					}
/*					if (order.getState()<5) {
						WHERE("state LIKE CONCAT ('%',#{order.state},'%') ");
					}*/
				}
			}
		}.toString();
	}

	// 动态插入
	public String insertOrder(Order order) {

		return new SQL() {
			{
				INSERT_INTO(ORDERTABLE);
				if (order.getAmount() != -1) {
					VALUES("amount", "#{amount}");
				}
				if (order.getUser() != null && order.getUser().getId()!=0) {
					VALUES("user_id", "#{user.id}");
				}
				if (order.getState()<5) {
					VALUES("state", "#{state}");
				}
			}
		}.toString();
	}

	// 动态更新
	public String updateOrder(Order order) {

		return new SQL() {
			{
				UPDATE(ORDERTABLE);
				if (order.getAmount()!=0) {
					SET("amount = #{amount}");
				}
				if (order.getUser() != null && order.getUser().getId()!=0) {
					SET("user_id = #{user.id}");
				}
				if (order.getState()<5) {
					SET("state = #{state}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
