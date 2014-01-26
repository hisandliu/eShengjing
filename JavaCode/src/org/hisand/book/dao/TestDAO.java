package org.hisand.book.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hisand.bible.BibleResourceManager;
import org.hisand.db.AbstractMapDAO;
import org.hisand.db.ConnectionHelper;
import org.hisand.db.DBHelper;

public class TestDAO extends AbstractMapDAO {
	public int getCountThis(int tree_id) throws SQLException {
		String sql = "select child_count from toc_tree where id=?";
		Object[] values = new Object[] { tree_id };
		Object object = scalar(sql, values);
		if (object == null)
			return 0;
		return (Integer) object;
	}
	
	public int getCount(int tree_id) throws SQLException {
		String sql = "select count(*) from toc_tree where parent_id=?";
		Object[] values = new Object[] { tree_id };
		Object object = scalar(sql, values);
		if (object == null)
			return 0;
		return (Integer) object;
	}

	public void updateChildCount(int tree_id) throws SQLException {
		int count = getCount(tree_id);
		String sql = "update toc_tree set child_count=? where id=?";
		Object[] values = new Object[] { count, tree_id };
		update(sql, values);
	}
	
	public void updateChildCount(String ref_id, int count) throws SQLException {
		String sql = "update toc_tree set child_count=? where " +
				"content_id in (select id from toc_content where ref_id=?)";
		Object[] values = new Object[] { count, ref_id };
		update(sql, values);
	}

	public List<Map<String, Object>> getListByDepth(int depth)
			throws SQLException {
		String sql = "select toc_tree.id tree_id,toc_tree.child_count,toc_content.* from toc_tree"
				+ " inner join toc_content on toc_tree.content_id=toc_content.id"
				+ " where toc_tree.depth=? ";
		Object[] values = new Object[] { depth };
		return query(sql, values);
	}
	
	public List<Map<String, Object>> getTreeList()
			throws SQLException {
		String sql = "select * from toc_tree";
		Object[] values = new Object[] {  };
		return query(sql, values);
	}

	public Map<String, Object> getItemByRefId(String ref_id)
			throws SQLException {
		String sql = "select toc_tree.id tree_id,toc_tree.child_count,toc_tree.depth,toc_tree.sort_order,toc_content.* from toc_tree"
				+ " inner join toc_content on toc_tree.content_id=toc_content.id"
				+ " where toc_content.ref_id=? ";
		Object[] values = new Object[] { ref_id };
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getItemByTreeId(int tree_id)
			throws SQLException {
		String sql = "select toc_tree.id tree_id,toc_tree.child_count,toc_tree.depth,toc_tree.parent_id,toc_tree.sort_order,toc_content.* from toc_tree"
				+ " inner join toc_content on toc_tree.content_id=toc_content.id"
				+ " where toc_tree.id=? ";
		Object[] values = new Object[] { tree_id };
		return queryItem(sql, values);
	}
	
	public List<Map<String, Object>> getChildByTreeId(int tree_id)
			throws SQLException {
		String sql = "select toc_tree.id tree_id,toc_tree.child_count,toc_tree.depth,toc_tree.parent_id,toc_tree.sort_order,toc_content.* from toc_tree"
				+ " inner join toc_content on toc_tree.content_id=toc_content.id"
				+ " where toc_tree.parent_id=? ";
		Object[] values = new Object[] { tree_id };
		return query(sql, values);
	}
	
	public List<Map<String, Object>> getContentValue(int content_id)
			throws SQLException {
		String sql = "select * from toc_contentvalue where content_id=? ";
		Object[] values = new Object[] { content_id };
		return query(sql, values);
	}

	public Map<String, Object> getItemByRefIdRefId(String ref_id,
			String parent_ref_id) throws SQLException {
		String sql = "select toc_tree.id tree_id,toc_tree.child_count,toc_content.* from toc_tree"
				+ " inner join toc_content on toc_tree.content_id=toc_content.id"
				+ " where toc_content.ref_id=? and toc_tree.id in (select toc_tree.id from toc_tree"
				+ " inner join toc_content on toc_tree.content_id=toc_content.id"
				+ " where toc_content.ref_id=?) ";
		Object[] values = new Object[] { ref_id, parent_ref_id };
		return queryItem(sql, values);
	}
	
	public String[] getContentByRefId(String ref_id) throws SQLException {
		String sql = "select cn.content cncontent, en.content encontent " +
				"from toc_content " + 
				"inner join toc_contentvalue cn on toc_content.id=cn.content_id and cn.translate_id='zh_CN' " +
				"inner join toc_contentvalue en on toc_content.id=en.content_id and en.translate_id='en_US' " +
				"where toc_content.ref_id=? ";
		Object[] values = new Object[] { ref_id };
		Map<String, Object> item = queryItem(sql, values);
		if (item == null) return null;
		String cncontent = (String) item.get("cncontent");
		String encontent = (String) item.get("encontent");
		return new String[]{cncontent, encontent};
	}

	public void updateDepth3Count() throws SQLException {
		ConnResult ct = null;
		try {
			ct = getConnResult();
			if (ct.isNewconn) {
				ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			int depth = 3;
			List<Map<String, Object>> list = getListByDepth(depth);
			for (Map<String, Object> item : list) {
				int tree_id = (Integer) item.get("tree_id");
				updateChildCount(tree_id);
			}
			if (ct.isNewconn) {
				ct.conn.commit();
			}

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
	
	
	public void updateAllDepthCount() throws SQLException {
		ConnResult ct = null;
		try {
			ct = getConnResult();
			if (ct.isNewconn) {
				ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			List<Map<String, Object>> list = getTreeList();
			for (Map<String, Object> item : list) {
				int tree_id = (Integer) item.get("id");
				updateChildCount(tree_id);
			}
			if (ct.isNewconn) {
				ct.conn.commit();
			}

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
	
	private int createResource(int depth, int parent_id, int type, int child_count, int sort_order, String code,
			String cnname, String enname, String cncontent, String encontent) throws SQLException {
		String sql = "insert into resource"
				+ "(depth, parent_id, type, child_count, sort_order, " +
				"code, cnname, enname, cncontent, encontent) "
				+ "values(?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?)";
		Object[] values = new Object[] { depth, parent_id, type, child_count, sort_order, 
				code, cnname, enname,cncontent,encontent };
		int id = updateWithReturnId(sql, values);
		return id;
	}
	
	private int createResource(int depth, int parent_id, int type, int child_count, int sort_order, String code,
			String cnname, String enname, String cncontent, String encontent, String parent_code) throws SQLException {
		String sql = "insert into resource"
				+ "(depth, parent_id, type, child_count, sort_order, " +
				"code, cnname, enname, cncontent, encontent, parent_code) "
				+ "values(?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?, ?)";
		Object[] values = new Object[] { depth, parent_id, type, child_count, sort_order, 
				code, cnname, enname,cncontent,encontent, parent_code };
		int id = updateWithReturnId(sql, values);
		return id;
	}
	
	public void insertResouce() throws SQLException {
		ConnResult ct = null;
		try {
			ct = getConnResult();
			if (ct.isNewconn) {
				//ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			insertResouceFrom(2, 1);
			insertResouceFrom(3, 1);
			if (ct.isNewconn) {
				//ct.conn.commit();
			}

		} catch (Exception e) {
			if (ct != null && ct.isNewconn) {
				//DBHelper.rollback(ct.conn);
			}
			throw new SQLException(e);
		} finally {
			if (ct != null && ct.isNewconn) {
				DBHelper.close(ct.conn);
				setConnection(null, getUsedConnectionId());
			}
		}
	}
	
	private void insertResouceFrom(int tree_id, int parent_id) throws SQLException {
		System.out.println("begin:" + tree_id);
		Map<String, Object> item = getItemByTreeId(tree_id);
		if (item == null) return;
		System.out.println("deal:" + tree_id);
		int depth = (Integer) item.get("depth");
		int type = depth;
		int content_id = (Integer) item.get("id");
		String code = (String) item.get("ref_id");
		int sort_order = (Integer) item.get("sort_order");
		
		String cnname = null;
		String enname = null;
		String cncontent = null;
		String encontent = null;
		
		List<Map<String, Object>> sublist = getChildByTreeId(tree_id);
		int child_count = sublist.size();
		
		List<Map<String, Object>> valuelist = getContentValue(content_id);
		for (Map<String, Object> valueitem : valuelist) {
			String translate_id = (String) valueitem.get("translate_id");
			if ("zh_CN".equals(translate_id)) {
				cnname = (String) valueitem.get("name");
				cncontent = (String) valueitem.get("content");
			}
			else if ("en_US".equals(translate_id)) {
				enname = (String) valueitem.get("name");
				encontent = (String) valueitem.get("content");
			}
		}
		
		int id = createResource(depth, parent_id, type, child_count, sort_order, 
				code, cnname, enname, cncontent, encontent);
		
		for (Map<String, Object> subitem : sublist) {
			int sub_tree_id = (Integer) subitem.get("tree_id");
			insertResouceFrom(sub_tree_id, id);
		}
		
	}
	
	private void updateParentCode0(int id) throws SQLException {
		String sql = "select code from resource where " +
				"id in (select parent_id from resource where id=?)";
		Object[] values = new Object[]{id};
		String parent_code = (String)scalar(sql, values);
		if (parent_code != null) {
			sql = "update resource set parent_code=? where id=?";
			values = new Object[]{parent_code, id};
			update(sql, values);
		}
	}
	
	public void updateParentCode() throws SQLException {
		ConnResult ct = null;
		try {
			ct = getConnResult();
			if (ct.isNewconn) {
				//ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			
			String sql = "select id from resource";
			Object[] values = null;
			List<Map<String, Object>> list = query(sql, values);
			
			for (Map<String, Object> item : list) {
				int id = (Integer) item.get("id");
				updateParentCode0(id);
				System.out.println(id);
			}
			
			if (ct.isNewconn) {
				//ct.conn.commit();
			}

		} catch (Exception e) {
			if (ct != null && ct.isNewconn) {
				//DBHelper.rollback(ct.conn);
			}
			throw new SQLException(e);
		} finally {
			if (ct != null && ct.isNewconn) {
				DBHelper.close(ct.conn);
				setConnection(null, getUsedConnectionId());
			}
		}
	}
	
	private BibleResourceManager bm = new BibleResourceManager();
	public void localToAliyun() throws SQLException {
		setConnectionId("aliyun");
		Connection bmconn = ConnectionHelper.getConnection();
		bm.setConnection(bmconn, null);
		ConnResult ct = null;
		try {
			ct = getConnResult();
			if (ct.isNewconn) {
				//ct.conn.setAutoCommit(false);
				setConnection(ct.conn, getUsedConnectionId());
			}
			localToAliyun("Old_Testament", 0);
			localToAliyun("New_Testament", 0);
			if (ct.isNewconn) {
				//ct.conn.commit();
			}

		} catch (Exception e) {
			if (ct != null && ct.isNewconn) {
				//DBHelper.rollback(ct.conn);
			}
			throw new SQLException(e);
		} finally {
			if (ct != null && ct.isNewconn) {
				DBHelper.close(ct.conn);
				DBHelper.close(bmconn);
				setConnection(null, getUsedConnectionId());
			}
		}
	}
	
	private void localToAliyun(String localcode, int parent_id) throws SQLException {
		System.out.println("begin:" + localcode);
		Map<String, Object> item = bm.getItemByCode(localcode);
		if (item == null) return;
		System.out.println("deal:" + localcode);
		int depth = (Integer) item.get("depth");
		int type = (Integer) item.get("type");
		String code = (String) item.get("code");
		String parent_code = (String) item.get("parent_code");
		int sort_order = (Integer) item.get("sort_order");
		int child_count = (Integer) item.get("child_count");
		
		String cnname = (String) item.get("cnname");
		String enname = (String) item.get("enname");
		String cncontent = (String) item.get("cncontent");
		String encontent = (String) item.get("encontent");
		
		List<Map<String, Object>> sublist = bm.getChildsByCode(code);
		
		int id = createResource(depth, parent_id, type, child_count, sort_order, 
				code, cnname, enname, cncontent, encontent, parent_code);
		
		for (Map<String, Object> subitem : sublist) {
			String sub_code = (String) subitem.get("code");
			localToAliyun(sub_code, id);
		}
		
	}
	
	public void localToAliyun222() throws SQLException {
		
	}
	
}
