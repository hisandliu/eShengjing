package org.hisand.book.dataexchange;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hisand.db.AbstractMapDAO;

public class DataExchangeDAO extends AbstractMapDAO {
	
	public int createResource(int depth, int parent_id, int type, int child_count, int sort_order, String code,
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
	
	public int getResourceIdByCode(String code) throws SQLException {
		String sql = "select id from resource where code=?";
		Object[] values = new Object[]{code};
		Object object = scalar(sql, values);
		if (object == null) return -1;
		return (Integer) object;
	}
	
	public List<Map<String, Object>> getExtendList(int id) throws SQLException {
		String sql = "select * from resource where id>=?";
		Object[] values = new Object[]{id};
		return query(sql, values);
	}
	
	public String getResourceCodeById(int id) throws SQLException {
		String sql = "select code from resource where id=?";
		Object[] values = new Object[]{id};
		Object object = scalar(sql, values);
		if (object == null) return null;
		return (String) object;
	}
	
	public Map<String, Object> getResouceByCode(String code) throws SQLException {
		String sql = "select * from resource where code=?";
		Object[] values = new Object[]{code};
		return queryItem(sql, values);
	}
	
	public Map<String, Object> getResouceById(int id) throws SQLException {
		String sql = "select * from resource where id=?";
		Object[] values = new Object[]{id};
		return queryItem(sql, values);
	}

	public int getMaxResourceId() throws SQLException {
		String sql = "select max(id) from resource";
		Object[] values = null;
		Object object = scalar(sql, values);
		if (object == null) return -1;
		return (Integer) object;
	}
	
	public int getMinResourceId() throws SQLException {
		String sql = "select min(id) from resource";
		Object[] values = null;
		Object object = scalar(sql, values);
		if (object == null) return -1;
		return (Integer) object;
	}
	
	public String getMaxResourceCode() throws SQLException {
		int id = getMaxResourceId();
		if (id == -1) {
			return null;
		}
		String code = getResourceCodeById(id);
		return code;
	}
	
	public void updateParentId(int depth) throws SQLException {
		String sql = "select id,code from resource where depth=?";
		Object[] values = new Object[] {depth};
		List<Map<String, Object>> list = query(sql, values);
		for (Map<String, Object> item : list) {
			int id = (Integer) item.get("id");
			String code = (String) item.get("code");
			sql = "update resource set parent_id=? where parent_code=?";
			values = new Object[] {id, code};
			update(sql, values);
			System.out.println("updateParentId,parent_code is:" + code);
		}
	}
	
	public void updateTitleDepth1and2() throws SQLException {
		String sql = "update resource set cntitle=cnname,entitle=enname,twtitle=twname where depth=1";
		Object[] values = null;
		update(sql, values);
		
		sql = "select * from resource where depth=1";
		values = null;
		List<Map<String, Object>> list1 = query(sql, values);
		for (Map<String, Object> item1 : list1) {
			int id1 = (Integer) item1.get("id");
			String cntitle1 = (String) item1.get("cntitle");
			String entitle1 = (String) item1.get("entitle");
			String twtitle1 = (String) item1.get("twtitle");
			
			sql = "select * from resource where parent_id=?";
			values = new Object[] {id1};
			List<Map<String, Object>> list2 = query(sql, values);
			for (Map<String, Object> item2 : list2) {
				int id2 = (Integer) item2.get("id");
				
				String cntitle2 = cntitle1 + " - " + (String)item2.get("cnname");
				String entitle2 = entitle1 + " - " + (String)item2.get("enname");
				String twtitle2 = twtitle1 + " - " + (String)item2.get("twname");
				
				sql = "update resource set cntitle=?,entitle=?,twtitle=? where id=?";
				values = new Object[] {cntitle2, entitle2, twtitle2, id2};
				update(sql, values);
			}
		}
	}
	
	public void updateTitleDepth3and4() throws SQLException {
		String sql = "select * from resource where depth=2";
		Object[] values = null;
		List<Map<String, Object>> list2 = query(sql, values);
		for (Map<String, Object> item2 : list2) {
			int id2 = (Integer) item2.get("id");
			String cntitle2 = (String) item2.get("cntitle");
			String entitle2 = (String) item2.get("entitle");
			String twtitle2 = (String) item2.get("twtitle");
			
			sql = "select * from resource where parent_id=?";
			values = new Object[] {id2};
			List<Map<String, Object>> list3 = query(sql, values);
			
			for (Map<String, Object> item3 : list3) {
				int id3 = (Integer) item3.get("id");
				int sort_order3 = (Integer) item3.get("sort_order");
				
				String cntitle3 = cntitle2 + " " + sort_order3;
				String entitle3 = entitle2 + " " + sort_order3;
				String twtitle3 = twtitle2 + " " + sort_order3;
				
				sql = "update resource set cntitle=?,entitle=?,twtitle=? where id=?";
				values = new Object[] {cntitle3, entitle3, twtitle3, id3};
				update(sql, values);
				
				System.out.println(cntitle3);
				
				sql = "select * from resource where parent_id=?";
				values = new Object[] {id3};
				List<Map<String, Object>> list4 = query(sql, values);
				for (Map<String, Object> item4 : list4) {
					int id4 = (Integer) item4.get("id");
					int sort_order4 = (Integer) item4.get("sort_order");
					
					String cntitle4 = cntitle3 + ":" + sort_order4;
					String entitle4 = entitle3 + ":" + sort_order4;
					String twtitle4 = twtitle3 + ":" + sort_order4;
					
					sql = "update resource set cntitle=?,entitle=?,twtitle=? where id=?";
					values = new Object[] {cntitle4, entitle4, twtitle4, id4};
					update(sql, values);
					
					System.out.println(cntitle4);
				}
			}
		
		}
	}
	
}
