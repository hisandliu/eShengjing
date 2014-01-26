package org.hisand.book.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hisand.book.dao.TOCQueryDAO;
import org.hisand.book.dao.ContentValueDAO;
import org.hisand.book.dao.TreeDAO;
import org.hisand.db.ConnectionHelper;
import org.hisand.db.DBHelper;

public class TOCQueryManager {
	
	public List<Map<String, Object>> getChildContentListWithValue(int content_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			TOCQueryDAO cdao = new TOCQueryDAO();
			cdao.setConnection(conn, null);
			
			ContentValueDAO cvdao = new ContentValueDAO();
			cvdao.setConnection(conn, null);
			
			List<Map<String, Object>> list = cdao.getChildContentList(content_id);
			for (Map<String, Object> item : list) {
				int sub_content_id = (Integer)item.get("id");
				item.put("contentvalue", cvdao.getList(sub_content_id));
			}
			
			return list;
		} catch (SQLException e) {
			throw e;
		}
		finally {
			DBHelper.close(conn);
		}
	}
	
	public List<Map<String, Object>> getChildContentListWithValue(String ref_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			TOCQueryDAO cdao = new TOCQueryDAO();
			cdao.setConnection(conn, null);
			
			ContentValueDAO cvdao = new ContentValueDAO();
			cvdao.setConnection(conn, null);
			
			List<Map<String, Object>> list = cdao.getChildContentList(ref_id);
			for (Map<String, Object> item : list) {
				int sub_content_id = (Integer)item.get("id");
				item.put("contentvalue", cvdao.getList(sub_content_id));
			}
			
			return list;
		} catch (SQLException e) {
			throw e;
		}
		finally {
			DBHelper.close(conn);
		}
	}
	
	public Map<String, Object> getContentWithValue(String ref_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			TOCQueryDAO cdao = new TOCQueryDAO();
			cdao.setConnection(conn, null);
			
			ContentValueDAO cvdao = new ContentValueDAO();
			cvdao.setConnection(conn, null);
			
			Map<String, Object> item = cdao.getContent(ref_id);
			if (item != null) {
				int content_id = (Integer)item.get("id");
				item.put("contentvalue", cvdao.getList(content_id));
			}
			
			return item;
		} catch (SQLException e) {
			throw e;
		}
		finally {
			DBHelper.close(conn);
		}
	}
	
	public Map<String, Object> getParentContent(String ref_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			TOCQueryDAO cdao = new TOCQueryDAO();
			cdao.setConnection(conn, null);
			
			Map<String, Object> item = cdao.getParentContent(ref_id);
			
			return item;
		} catch (SQLException e) {
			throw e;
		}
		finally {
			DBHelper.close(conn);
		}
	}
	
	public Map<String, Object> getPrevContent(String ref_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			TOCQueryDAO cdao = new TOCQueryDAO();
			cdao.setConnection(conn, null);
			
			Map<String, Object> item = cdao.getPrevContent(ref_id);
			
			return item;
		} catch (SQLException e) {
			throw e;
		}
		finally {
			DBHelper.close(conn);
		}
	}
	
	public Map<String, Object> getNextContent(String ref_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			TOCQueryDAO cdao = new TOCQueryDAO();
			cdao.setConnection(conn, null);
			
			Map<String, Object> item = cdao.getNextContent(ref_id);
			
			return item;
		} catch (SQLException e) {
			throw e;
		}
		finally {
			DBHelper.close(conn);
		}
	}
	
	public int getChildCount(int tree_id) throws SQLException {
		int count = new TreeDAO().getChildCount(tree_id);
		return count;
	}
	
}
