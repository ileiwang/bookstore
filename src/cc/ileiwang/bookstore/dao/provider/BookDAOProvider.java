package cc.ileiwang.bookstore.dao.provider;

import static cc.ileiwang.bookstore.util.common.BSConstants.BOOKTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cc.ileiwang.bookstore.domain.Book;

/**
* @author Lei Wang
* @email ileiwang@live.com
* @blog www.ileiwang.cc
* @version 2018��7��11�� ����3:43:34
*/
public class BookDAOProvider {
	// ��ҳ��̬��ѯ
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(BOOKTABLE);
				if (params.get("book") != null) {
					Book book = (Book) params.get("book");
					if (book.getId() != 0) {
						WHERE("id LIKE CONCAT ('%',#{book.id},'%') ");
					}
					if (book.getName() != null && !book.getName().equals("")) {
						WHERE("name LIKE CONCAT ('%',#{book.name},'%') ");
					}
					if (book.getPrice() != 0) {
						WHERE("price LIKE CONCAT ('%',#{book.price},'%') ");
					}
					if (book.getQuantity() != 0) {
						WHERE("quantity LIKE CONCAT ('%',#{book.quantity},'%') ");
					}
					if (book.getIsdel() != 2) {
						WHERE("isdel LIKE CONCAT ('%',#{book.isdel},'%') ");
					}
				}
			}
		}.toString();

		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}

	// ��̬��ѯ������
	public String countBook(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(BOOKTABLE);
				if (params.get("book") != null) {
					Book book = (Book) params.get("book");
					if (book.getId() != 0) {
						WHERE("id LIKE CONCAT ('%',#{book.id},'%') ");
					}
					if (book.getName() != null && !book.getName().equals("")) {
						WHERE("name LIKE CONCAT ('%',#{book.name},'%') ");
					}
					if (book.getPrice() != 0) {
						WHERE("price LIKE CONCAT ('%',#{book.price},'%') ");
					}
					if (book.getQuantity() != 0) {
						WHERE("quantity LIKE CONCAT ('%',#{book.quantity},'%') ");
					}
					if (book.getIsdel() != 2) {
						WHERE("isdel LIKE CONCAT ('%',#{book.isdel},'%') ");
					}
				}
			}
		}.toString();
	}

	// ��̬����
	public String insertBook(Book book) {

		return new SQL() {
			{
				INSERT_INTO(BOOKTABLE);
				if (book.getName() != null && !book.getName().equals("")) {
					VALUES("name", "#{name}");
				}

				if (book.getPrice() != 0) {
					VALUES("price", "#{price}");
				}
				
				if (book.getQuantity() != 0) {
					VALUES("quantity","#{quantity}");
				}
				if (book.getImg() != null&&!book.getImg().equals("")) {
					VALUES("img","#{img}");
				}
			}
		}.toString();
	}

	// ��̬����
	public String updateBook(Book book) {

		return new SQL() {
			{
				UPDATE(BOOKTABLE);
				if (book.getName() != null && !book.getName().equals("")) {
					SET("name = #{name}");
				}
				if (book.getPrice() != 0) {
					SET("price = #{price}");
				}
				if (book.getQuantity()>=0) {
					SET("quantity = #{quantity}");
				}
				if (book.getIsdel() == 0||book.getIsdel() == 1) {
					SET("isdel = #{isdel}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
