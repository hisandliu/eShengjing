package org.hisand.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;

public class TOCQueryDAO extends AbstractMapDAO {
	
	public List<Map<String, Object>> getChildContentList(int content_id) throws SQLException {
		String sql = "select t.id tree_id, t.sort_order, c.* from toc_content c inner join toc_tree t on c.id=t.content_id " +
				"where t.parent_id in (select id from toc_tree where content_id=?) order by t.sort_order";
		Object[] values = new Object[] { content_id };
		return query(sql, values);
	}
	
	public List<Map<String, Object>> getChildContentList(String ref_id) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "select id from toc_content where ref_id=?";
		Object[] values = new Object[] { ref_id };
		Object object = scalar(sql, values);
		if (object == null) return list;
		int content_id = (Integer) object;
		return getChildContentList(content_id);
	}
	
	public Map<String, Object> getContent(String ref_id) throws SQLException {
		String sql = "select id from toc_content where ref_id=?";
		Object[] values = new Object[] { ref_id };
		Object object = scalar(sql, values);
		if (object == null) return null;
		int content_id = (Integer) object;
		return getContent(content_id);
	}
	
	public Map<String, Object> getContent(int content_id) throws SQLException {
		String sql = "select t.id tree_id, t.sort_order, c.* from toc_content c " +
				"inner join toc_tree t on c.id=t.content_id where c.id=?";
		Object[] values = new Object[] { content_id };
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getParentContent(int content_id) throws SQLException {
		String sql = "select t.id tree_id, t.sort_order, c.* from toc_content c " +
				"inner join toc_tree t on c.id=t.content_id ";
		sql = sql+ " where t.id in (select parent_id from toc_tree where content_id=?)";
		Object[] values = new Object[] { content_id };
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getParentContent(String ref_id) throws SQLException {
		String sql = "select id from toc_content where ref_id=?";
		Object[] values = new Object[] { ref_id };
		Object object = scalar(sql, values);
		if (object == null) return null;
		int content_id = (Integer) object;
		return getParentContent(content_id);
	}
	
	public Map<String, Object> getPrevContent(int content_id) throws SQLException {
		String sql = "select t.id tree_id, t.sort_order, c.* from toc_content c " +
				" inner join toc_tree t on c.id=t.content_id ";
		sql = sql+ " where t.sort_order in (select sort_order - 1 from toc_tree where content_id=?)" +
				" and t.parent_id in (select parent_id from toc_tree where content_id=? )";
		Object[] values = new Object[] { content_id, content_id };
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getPrevContent(String ref_id) throws SQLException {
		String sql = "select id from toc_content where ref_id=?";
		Object[] values = new Object[] { ref_id };
		Object object = scalar(sql, values);
		if (object == null) return null;
		int content_id = (Integer) object;
		return getPrevContent(content_id);
	}
	
	public Map<String, Object> getNextContent(int content_id) throws SQLException {
		String sql = "select t.id tree_id, t.sort_order, c.* from toc_content c " +
				" inner join toc_tree t on c.id=t.content_id ";
		sql = sql+ " where t.sort_order in (select sort_order + 1 from toc_tree where content_id=?)" +
				" and t.parent_id in (select parent_id from toc_tree where content_id=? )";
		Object[] values = new Object[] { content_id, content_id };
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getNextContent(String ref_id) throws SQLException {
		String sql = "select id from toc_content where ref_id=?";
		Object[] values = new Object[] { ref_id };
		Object object = scalar(sql, values);
		if (object == null) return null;
		int content_id = (Integer) object;
		return getNextContent(content_id);
	}
}
