package org.hisand.bible;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;
import org.hisand.db.DBHelper;

public class BibleResourceManager extends AbstractMapDAO {
	
	private String fieldLine = "parent_code,code,child_count,sort_order,enname,cnname,encontent,cncontent";
	private String tablename = "resource";
	
	private String buildBasicSql() {
		String sql = "select " + fieldLine + " from " + tablename;
		return sql;
	}
	
	public Map<String, Object> getItemByCode(String code) throws SQLException {
		String sql = buildBasicSql();
		sql = sql + " where code=?";
		Object[] values = new Object[]{code};
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getParentIdItemById(int id) throws SQLException {
		String sql = buildBasicSql();
		sql = sql + " where id in (select parent_id from " + tablename + " where id=?)";
		Object[] values = new Object[]{id};
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getParentIdItemByCode(String code) throws SQLException {
		int id = getIdByCode(code);
		return getParentIdItemById(id);
	}
	
	public List<Map<String, Object>> getChildsById(int id) throws SQLException {
		String sql = buildBasicSql();
		sql = sql + " where parent_id=? order by sort_order";
		Object[] values = new Object[]{id};
		return query(sql, values);
	}
	
	public List<Map<String, Object>> getChildsByCode(String code) throws SQLException {
		int id = getIdByCode(code);
		return getChildsById(id);
	}
	
	public int getIdByCode(String code) throws SQLException {
		String sql = "select id from " + tablename + " where code=?";
		Object[] values = new Object[]{code};
		Object object = scalar(sql, values);
		if (object == null) return -1;
		return (Integer) object;
	}
	
	public String getChapterChildHtml(String code)
			throws SQLException {
		int id = getIdByCode(code);
		String sql = buildBasicSql();
		sql = sql + " where parent_id=?";
		Object[] values = new Object[]{id};
		ConnResult rt = getConnResult();
		ChapterHtmlHandler handler = new ChapterHtmlHandler();
		String r = DBHelper.query(rt.conn, sql, rt.isNewconn, handler, values);
		return r;
	}
}
