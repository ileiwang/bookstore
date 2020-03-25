package cc.ileiwang.bookstore.dao.provider;

import static cc.ileiwang.bookstore.util.common.BSConstants.ORDERITEMTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cc.ileiwang.bookstore.domain.OrderItem;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018年7月11日 下午3:52:11
*/
public class OrderItemDAOProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(ORDERITEMTABLE);
				if (params.get("orderitem") != null) {
					OrderItem orderitem = (OrderItem) params.get("orderitem");
					if (orderitem.getBook()!=null&&orderitem.getBook().getId()!=0) {
						WHERE("book_id LIKE CONCAT ('%',#{orderitem.book.id},'%') ");
					}
					if (orderitem.getOrder() != null && orderitem.getOrder().getId()!=0) {
						WHERE("order_id LIKE CONCAT ('%',#{orderitem.order.id},'%') ");
					}
					if (orderitem.getQuantity()!=0) {
						WHERE("quantity LIKE CONCAT ('%',#{orderitem.quantity},'%') ");
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
	public String countOrderItem(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(ORDERITEMTABLE);
				if (params.get("orderitem") != null) {
					OrderItem orderitem = (OrderItem) params.get("orderitem");
					if (orderitem.getBook()!=null&&orderitem.getBook().getId()!=0) {
						WHERE("book_id LIKE CONCAT ('%',#{orderitem.book.id},'%') ");
					}
					if (orderitem.getOrder() != null && orderitem.getOrder().getId()!=0) {
						WHERE("order_id LIKE CONCAT ('%',#{orderitem.order.id},'%') ");
					}
					if (orderitem.getQuantity()!=0) {
						WHERE("quantity LIKE CONCAT ('%',#{orderitem.quantity},'%') ");
					}
				}
			}
		}.toString();
	}

	// 动态插入
	public String insertOrderItem(OrderItem orderitem) {

		return new SQL() {
			{
				INSERT_INTO(ORDERITEMTABLE);
				if (orderitem.getBook()!=null&&orderitem.getBook().getId()!=0) {
					VALUES("book_id", "#{book.id}");
				}
				if (orderitem.getOrder() != null && orderitem.getOrder().getId()!=0) {
					VALUES("order_id", "#{order.id}");
				}
				if (orderitem.getQuantity()!=0) {
					VALUES("quantity", "#{quantity}");
				}
			}
		}.toString();
	}

	// 动态更新
	public String updateOrderItem(OrderItem orderitem) {

		return new SQL() {
			{
				UPDATE(ORDERITEMTABLE);
				if (orderitem.getBook()!=null&&orderitem.getBook().getId()!=0) {
					SET("book_id = #{book.id}");
				}
				if (orderitem.getOrder() != null && orderitem.getOrder().getId()!=0) {
					SET("order_id = #{order.id}");
				}
				if (orderitem.getQuantity()!=0) {
					SET("quantity = #{quantity}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
