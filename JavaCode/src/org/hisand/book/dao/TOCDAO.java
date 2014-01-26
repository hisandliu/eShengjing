package org.hisand.book.dao;

import java.sql.SQLException;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;
import org.hisand.db.DBHelper;

public class TOCDAO extends AbstractMapDAO {

	public int deleteTOCByTreeId(int tree_id) throws SQLException {
		return deleteTOC0(tree_id, -1, null, -1);
	}
	
	public int deleteTOCByContentId(int content_id) throws SQLException {
		return deleteTOC0(-1, content_id, null, -1);
	}
	
	public int deleteTOCByRefId(String ref_id, int parent_id) throws SQLException {
		return deleteTOC0(-1, -1, ref_id, parent_id);
	}
	
	public Map<String, Object> getTreeByRefId(String ref_id, int parent_id) throws SQLException {
		String sql = "select t.id tree_id,t.* from toc_tree t where parent_id = ? " +
				"and content_id in (select id from toc_content where ref_id = ?)";
		Object[] values = new Object[] { parent_id, ref_id };
		Map<String, Object> item = queryItem(sql, values);
		return item;
	}
	
	public Map<String, Object> getTreeContentByRefId(String ref_id, int parent_id) throws SQLException {
		String sql = "select " +
				"t.id tree_id,t.parent_id,t.depth,t.path_string,t.content_id," +
				"t.sort_order,t.is_hidden,c.class_id, c.ref_id,c.name,c.owner_id," +
				"c.status " +
				"from toc_tree t inner join toc_content c " +
				"on t.content_id=c.id where t.parent_id = ? " +
				"and c.ref_id = ?)";
		Object[] values = new Object[] { parent_id, ref_id };
		Map<String, Object> item = queryItem(sql, values);
		return item;
	}
	
	public boolean existByRefId(String ref_id, int parent_id) throws SQLException {
		Map<String, Object> item = getTreeByRefId(ref_id, parent_id);
		return item != null;
	}

	private String getPathString(int tree_id) throws SQLException {
		String sql = "select path_string from toc_tree where id = ?";
		Object[] values = new Object[] { tree_id };
		Object object = scalar(sql, values);
		if (object == null) return null;
		return (String) object;
	}
	
	private int getTreeIdByContentId(int content_id) throws SQLException {
		String sql = "select id from toc_tree where content_id = ?";
		Object[] values = new Object[] { content_id };
		Object object = scalar(sql, values);
		if (object == null) return -1;
		return (Integer) object;
	}
	
	private int getTreeIdByRefId(String ref_id, int parent_id) throws SQLException {
		String sql = "select id from toc_tree where parent_id = ? " +
				"and content_id in (select content_id from toc_content where ref_id = ?)";
		Object[] values = new Object[] { parent_id, ref_id };
		Object object = scalar(sql, values);
		if (object == null) return -1;
		return (Integer) object;
	}

	private int deleteTOC0(int tree_id, int content_id, String ref_id, int parent_id) throws SQLException {
		ConnResult ct = null;
		try {
			ct = getConnResult();
			if (ct.isNewconn) {
				ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			
			if (tree_id < 0) {
				if (content_id > 0) {
					tree_id = getTreeIdByContentId(content_id);
				}
				else {
					tree_id = getTreeIdByRefId(ref_id, parent_id);
				}
			}
			
			if (tree_id < 0) {
				return 0;
			}
			
			String path_string = getPathString(tree_id);
			String like_path_string = "%" + path_string + "%";

			int r = 0;
			{
				String sql = "delete from toc_contentvalue where content_id in " +
						"(select content_id from toc_tree where path_string like ?)";
				Object[] values = new Object[] { like_path_string };
				update(sql, values);
			}

			{
				String sql = "delete from toc_content where content_id in " +
						"(select content_id from toc_tree where path_string like ?)";
				Object[] values = new Object[] { like_path_string };
				update(sql, values);
			}

			{
				String sql = "delete from toc_tree where path_string like ?";
				Object[] values = new Object[] { like_path_string };
				r = update(sql, values);
			}

			return r;
		} catch (Exception e) {
			if (ct != null && ct.isNewconn) {
				DBHelper.rollback(ct.conn);
			}
			throw new SQLException(e);
		} finally {
			if (ct != null && ct.isNewconn) {
				DBHelper.close(ct.conn);
				setConnection(null, getUsedConnectionId());
			}
		}
	}
}
