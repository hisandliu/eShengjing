package org.hisand.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;

/**
 * operate table toc_content
 * 
 * @author Hisand Liu
 * @TableStructure id integer primary key autoincrement not null, <br/>
 *                 class_id int(11) not null, -- toc_class.id,类编号 <br/>
 *                 ref_id varchar(255) null, -- 参考id <br/>
 *                 name varchar(255) not null, -- 名称 <br/>
 *                 owner_id int(11) not null, -- toc_user.id,所属用户 <br/>
 *                 status int(11) not null -- 状态 <br/>
 */
public class ContentDAO extends AbstractMapDAO {
	public int createItem(int class_id, String ref_id,
			String name, int owner_id, int status) throws SQLException {
		String sql = "insert into toc_content"
				+ "(class_id, ref_id, name, owner_id, status) "
				+ "values(?, ?, ?, ?, ?)";
		Object[] values = new Object[] { class_id, ref_id, name, owner_id,
				status };
		int id = updateWithReturnId(sql, values);
		return id;
	}
	
	public List<Map<String, Object>> getList(int content_id) throws SQLException {
		String sql = "select * from toc_content where id=?";
		Object[] values = new Object[] { content_id };
		return query(sql, values);
	}
	public List<Map<String, Object>> getList(String ref_id) throws SQLException {
		String sql = "select * from toc_content where ref_id=?";
		Object[] values = new Object[] { ref_id };
		return query(sql, values);
	}
	
}
