package org.hisand.book.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;
import org.hisand.db.DBType;
import org.hisand.db.SqliteHelper;

/**
 * operate table toc_contentvalue
 * 
 * @author Hisand Liu
 * @TableStructure id integer primary key autoincrement not null, <br/>
 *                 content_id int(11) not null, -- toc_content.id,对应的内容编号 <br/>
 *                 translate_id varchar(10) not null, -- 语言,(zh_TW,zh_CN,en_US) <br/>
 *                 name varchar(255) null, -- 名称 <br/>
 *                 title varchar(255) null, -- 标题 <br/>
 *                 intro varchar(255) null, -- 介绍 <br/>
 *                 content text null, -- 内容 <br/>
 *                 data_float float null, -- 浮点数 <br/>
 *                 data_int int(11) null, -- 整数 <br/>
 *                 data_text text null, -- 文本 <br/>
 *                 data_date datetime null, -- 日期 <br/>
 *                 modified datetime null, -- 修改日期 <br/>
 *                 modifier_id int(11) null, -- toc_user.id,修改用户 <br/>
 *                 created datetime null, -- 新建日期 <br/>
 *                 creator_id int(11) null -- toc_user.id,新建用户 <br/>
 */
public class ContentValueDAO extends AbstractMapDAO {
	public int createItem(int content_id, String translate_id, String name,
			String title, String intro, String content, double data_float,
			int data_int, String data_text, Date data_date, Date modified,
			int modifier_id, Date created, int creator_id) throws SQLException {
		String sql = "insert into toc_contentvalue"
				+ "(content_id, translate_id, name, title, intro, "
				+ "content,data_float,data_int,data_text,data_date,"
				+ "modified,modifier_id,created,creator_id) "
				+ "values(?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?)";
		
		Object obj_date_date = data_date;
		Object obj_modified = modified;
		Object obj_created = created;
		if (DBType.SQLITE.equals(getDialect().getDbType())) {
			if (data_date != null) {
				obj_date_date = SqliteHelper.getDateTimeString(data_date);
			}
			if (modified != null) {
				obj_modified = SqliteHelper.getDateTimeString(modified);
			}
			if (created != null) {
				obj_created = SqliteHelper.getDateTimeString(created);
			}
		}
		Object[] values = new Object[] { content_id, translate_id, name, title,
				intro, content, data_float, data_int, data_text, obj_date_date,
				obj_modified, modifier_id, obj_created, creator_id };
		int id = updateWithReturnId(sql, values);
		return id;
	}

	public Map<String, Object> getItem(int content_id, String translate_id)
			throws SQLException {
		String sql = "select * from toc_contentvalue where content_id=? and translate_id=?";
		Object[] values = new Object[] { content_id, translate_id };
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getItem(int id)
			throws SQLException {
		String sql = "select * from toc_contentvalue where id=?";
		Object[] values = new Object[] { id };
		return queryItem(sql, values);
	}
	
	public List<Map<String, Object>> getList(int content_id) throws SQLException {
		String sql = "select * from toc_contentvalue where content_id=?";
		Object[] values = new Object[] { content_id };
		return query(sql, values);
	}
	
	
}
