package org.hisand.book.dao;

import java.sql.SQLException;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;
import org.hisand.db.DBHelper;

/**
 * operate table toc_tree
 * 
 * @author Hisand Liu
 * @TableStructure id integer primary key autoincrement not null,<br/>
 *                 parent_id int(11) not null,<br/>
 *                 depth int(11) not null,<br/>
 *                 path_string varchar(255) not null,<br/>
 *                 content_id int(11) not null,<br/>
 *                 sort_order int(11) not null,<br/>
 *                 is_hidden int(11) not null<br/>
 */
public class TreeDAO extends AbstractMapDAO {

	/**
	 * 创建一笔 toc_tree 记录
	 * 
	 * @param parent_id
	 * @param content_id
	 * @param sort_order 排序的编号，如果传入 0，将取所有父节点的子节点的最大 sort_order
	 * @param is_hidden
	 * @return 新纪录的 id
	 * @throws SQLException
	 */
	public int createItem(int parent_id, int content_id, int sort_order,
			int is_hidden) throws SQLException {
		
		ConnResult ct = null;
		try {
			createRoot();
			ct = getConnResult();
			if (ct.isNewconn) {
				ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			Map<String, Object> parentItem = getItem(parent_id);
			if (parentItem == null) {
				throw new SQLException("parent _id {" + parent_id
						+ "} is not exist!");
			}
			
			if (sort_order <= 0) {
				sort_order = getMaxSortOrder(parent_id) + 1;
			}

			int depth = 0;
			String path_string = "/";

			String sql = "insert into toc_tree"
					+ "(parent_id, content_id, sort_order, is_hidden, depth, path_string) "
					+ "values(?, ?, ?, ?, ?, ?)";
			Object[] values = new Object[] { parent_id, content_id, sort_order,
					is_hidden, depth, path_string };
			int id = updateWithReturnId(sql, values);

			String parent_path_string = (String) parentItem.get("path_string");
			path_string = parent_path_string + id + "/";

			int parent_depth = (Integer) parentItem.get("depth");
			depth = parent_depth + 1;

			updatePathString(id, path_string, depth);

			if (ct.isNewconn) {
				ct.conn.commit();
			}

			return id;
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

	/**
	 * 根据 id 更新 toc_tree.path_string
	 * 
	 * @param id
	 * @param path_string
	 * @return 更新影响到的行数，返回 1 为正常
	 * @throws SQLException
	 */
	public int updatePathString(int id, String path_string, int depth)
			throws SQLException {
		String sql = "update toc_tree set path_string=?, depth=? where id=?";
		Object[] values = new Object[] { path_string, depth, id };
		return update(sql, values);
	}
	
	/**
	 * 获取该节点的子节点的最大 sort_order
	 * @param parent_id
	 * @return
	 * @throws SQLException
	 */
	public int getMaxSortOrder(int parent_id) throws SQLException {
		String sql = "select max(sort_order) from toc_tree where parent_id=?";
		Object[] values = new Object[] { parent_id };
		Object obj = scalar(sql, values);
		if (obj == null) return 0;
		return (Integer) obj;
	}

	/**
	 * 根据 id 取得一笔记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getItem(int id) throws SQLException {
		String sql = "select * from toc_tree where id=?";
		Object[] values = new Object[] { id };
		return queryItem(sql, values);
	}

	/**
	 * 根据 id 取得一笔记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getRootItem() throws SQLException {
		String sql = "select * from toc_tree where id=?";
		Object[] values = new Object[] { 1 };
		Map<String, Object> item = queryItem(sql, values);
		return item;
	}

	/**
	 * 根据 id 删除一笔记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteItem(int id) throws SQLException {
		String sql = "delete from toc_tree where id=?";
		Object[] values = new Object[] { id };
		return update(sql, values);
	}

	private int createRoot() throws SQLException {
		Map<String, Object> root = getItem(1);
		if (root != null)
			return 1;
		String sql = "insert into toc_tree(id, parent_id, depth, path_string, "
				+ "content_id, sort_order, is_hidden) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		Object[] values = new Object[] { 1, 1, 0, "/1/", 0, 0, 0 };
		update(sql, values);
		return 1;
	}
	
	public int getChildCount(int tree_id) throws SQLException {
		String sql = "select count(id) from toc_tree where parent_id=?";
		Object[] values = new Object[] { tree_id };
		Object object = scalar(sql, values);
		if (object == null)
			return 0;
		return (Integer) object;
	}
}
