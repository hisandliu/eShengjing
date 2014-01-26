package org.hisand.book.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.hisand.book.dao.ContentDAO;
import org.hisand.book.dao.ContentValueDAO;
import org.hisand.book.dao.TOCDAO;
import org.hisand.book.dao.TreeDAO;
import org.hisand.db.ConnectionHelper;
import org.hisand.db.DBHelper;

public class TOCManager {
	private void writeLog(Throwable message) {
	}
	
	public Map<String, Object> getTreeByRefId(String ref_id, int parent_id) throws Exception {
		return new TOCDAO().getTreeByRefId(ref_id, parent_id);
	}
	
	public Map<String, Object> getTreeContentByRefId(String ref_id, int parent_id) throws Exception {
		return new TOCDAO().getTreeContentByRefId(ref_id, parent_id);
	}
	
	public Map<String, Object> getContentValue(int content_id, String translate_id) throws Exception {
		return new ContentValueDAO().getItem(content_id, translate_id);
	}

	public int[] createTOC(int parent_id, int class_id, String name)
			throws Exception {
		int sort_order = 0;
		String ref_id = null;
		return createTOC(parent_id, class_id, name, sort_order, ref_id);
	}

	public int[] createTOC(int parent_id, int class_id, String name,
			String ref_id) throws Exception {
		int sort_order = 0;
		return createTOC(parent_id, class_id, name, sort_order, ref_id);
	}

	public int deleteTOCByTreeId(int tree_id) throws SQLException {
		return new TOCDAO().deleteTOCByTreeId(tree_id);
	}

	public int deleteTOCByContentId(int content_id) throws SQLException {
		return new TOCDAO().deleteTOCByContentId(content_id);
	}

	public int deleteTOCByRefId(String ref_id, int parent_id)
			throws SQLException {
		return new TOCDAO().deleteTOCByRefId(ref_id, parent_id);
	}

	public int[] createTOC(int parent_id, int class_id, String name,
			int sort_order, String ref_id) throws Exception {
		int owner_id = 1;
		int status = 0;
		int is_hidden = 0;
		return createTOC(parent_id, class_id, name, sort_order, ref_id,
				owner_id, status, is_hidden);
	}

	public int[] createTOC(int parent_id, int class_id, String name,
			int sort_order, String ref_id, int owner_id, int status,
			int is_hidden) throws Exception {
		return createTOC0(parent_id, class_id, name, sort_order, ref_id,
				owner_id, status, is_hidden);
	}

	private int[] createTOC0(int parent_id, int class_id, String name,
			int sort_order, String ref_id, int owner_id, int status,
			int is_hidden) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionHelper.getConnection();
			conn.setAutoCommit(false);
			TreeDAO treeDAO = new TreeDAO();
			treeDAO.setConnection(conn, null);
			ContentDAO contentDAO = new ContentDAO();
			contentDAO.setConnection(conn, null);

			int content_id = contentDAO.createItem(class_id, ref_id, name,
					owner_id, status);
			int tree_id = treeDAO.createItem(parent_id, content_id, sort_order,
					is_hidden);

			conn.commit();

			int[] arr = new int[] { tree_id, content_id };

			return arr;
		} catch (Exception e) {
			DBHelper.rollback(conn);
			writeLog(e);
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.close(conn);
		}
	}

	public int createContentValue(int content_id, String translate_id,
			String name, String content) throws Exception {
		String title = name;
		String intro = null;
		double data_float = 0;
		int data_int = 0;
		String data_text = null;
		Date data_date = null;
		Date modified = null;
		int modifier_id = 1;
		Date created = null;
		int creator_id = 1;
		return createContentValue(content_id, translate_id, name, title, intro,
				content, data_float, data_int, data_text, data_date, modified,
				modifier_id, created, creator_id);
	}

	public int createContentValue(int content_id, String translate_id,
			String name, String title, String intro, String content,
			double data_float, int data_int, String data_text, Date data_date,
			Date modified, int modifier_id, Date created, int creator_id)
			throws Exception {
		try {
			ContentValueDAO cvDAO = new ContentValueDAO();
			int r = cvDAO.createItem(content_id, translate_id, name, title,
					intro, content, data_float, data_int, data_text, data_date,
					modified, modifier_id, created, creator_id);
			return r;
		} catch (Exception e) {
			writeLog(e);
			e.printStackTrace();
			throw e;
		}
	}
}
