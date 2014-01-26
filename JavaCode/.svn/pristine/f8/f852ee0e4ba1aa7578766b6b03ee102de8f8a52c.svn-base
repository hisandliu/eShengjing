package org.hisand.book.dataexchange;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hisand.db.ConnectionHelper;
import org.hisand.db.DBHelper;

public class DataExchangeManager {
	
	private String sourceConnectionId;
	private String targetConnectionId;
	DataExchangeDAO sdao;
	DataExchangeDAO tdao;
	int sMaxId = 0;
	int sMinId = 0;
	
	public DataExchangeManager(String sourceConnectionId, String targetConnectionId) {
		this.sourceConnectionId = sourceConnectionId;
		this.targetConnectionId = targetConnectionId;
	}
	
	public String exchange() throws SQLException {
		Connection sconn = null;
		Connection tconn = null;
		int sStartId = 0;
		try {
			sconn = ConnectionHelper.getConnection(sourceConnectionId);
			tconn = ConnectionHelper.getConnection(targetConnectionId);
			
			sdao = new DataExchangeDAO();
			sdao.setConnection(sconn, sourceConnectionId);
			
			tdao = new DataExchangeDAO();
			tdao.setConnection(tconn, targetConnectionId);
			
			sMaxId = sdao.getMaxResourceId();
			sMinId = sdao.getMinResourceId();
		
			String r = exchange1();
			if ("success".equals(r)) {
				updateParentId0();
			}
			
			return r;
		} catch (SQLException e) {
			throw new SQLException("current id is: " + sStartId + ".\n" + e.getMessage());
		}
		finally {
			DBHelper.close(sconn);
			DBHelper.close(tconn);
		}
	}
	
	public void updateParentId() throws SQLException {
		Connection tconn = null;
		int sStartId = 0;
		try {
			tconn = ConnectionHelper.getConnection(targetConnectionId);
			
			tdao = new DataExchangeDAO();
			tdao.setConnection(tconn, targetConnectionId);
			
			updateParentId0();

		} catch (SQLException e) {
			throw new SQLException("current id is: " + sStartId + ".\n" + e.getMessage());
		}
		finally {
			DBHelper.close(tconn);
		}
	}
	
	private void updateParentId0() throws SQLException {
		tdao.updateParentId(1);
		tdao.updateParentId(2);
		tdao.updateParentId(3);
	}
	
	@SuppressWarnings("unused")
	private String exchange0() throws SQLException {
		int sCurrentId = 0;
		try {
			int sStartId = 0;
			String tMaxcode = tdao.getMaxResourceCode();
			if (tMaxcode == null) {
				if (sMinId == -1) {
					return "no data process!";
				}
				else {
					sStartId = sMinId;
				}
			}
			else {
				int sId = sdao.getResourceIdByCode(tMaxcode);
				if (sId == -1) {
					return "target code not found in source!";
				}
				else {
					sStartId = sId + 1;
				}
			}
			
			List<Map<String, Object>> sList = sdao.getExtendList(sStartId);
			
			for (Map<String, Object> sItem : sList) {
				sCurrentId = (Integer) sItem.get("id");
				int depth = (Integer) sItem.get("depth");
				int type = (Integer) sItem.get("type");
				String code = (String) sItem.get("code");
				String parent_code = (String) sItem.get("parent_code");
				int sort_order = (Integer) sItem.get("sort_order");
				int child_count = (Integer) sItem.get("child_count");
				
				String cnname = (String) sItem.get("cnname");
				String enname = (String) sItem.get("enname");
				String cncontent = (String) sItem.get("cncontent");
				String encontent = (String) sItem.get("encontent");
				
				int parent_id = 0;
				
				tdao.createResource(depth, parent_id, type, child_count, sort_order, 
						code, cnname, enname, cncontent, encontent, parent_code);
				
				System.out.println(code);
			}
			
			return "success";
		} catch (SQLException e) {
			throw new SQLException("current id is: " + sCurrentId + ".\n" + e.getMessage());
		}
		finally {
		}
	}
	
	private String exchange1() throws SQLException {
		int sCurrentId = 0;
		try {
			int sStartId = 0;
			String tMaxcode = tdao.getMaxResourceCode();
			if (tMaxcode == null) {
				if (sMinId == -1) {
					return "no data process!";
				}
				else {
					sStartId = sMinId;
				}
			}
			else {
				int sId = sdao.getResourceIdByCode(tMaxcode);
				if (sId == -1) {
					return "target code not found in source!";
				}
				else {
					sStartId = sId + 1;
				}
			}
			
			for (int index = sStartId; index <= sMaxId; index++) {
				Map<String, Object> sItem = sdao.getResouceById(index);
				if (sItem == null) continue;
				sCurrentId = index;
				int depth = (Integer) sItem.get("depth");
				int type = (Integer) sItem.get("type");
				String code = (String) sItem.get("code");
				String parent_code = (String) sItem.get("parent_code");
				int sort_order = (Integer) sItem.get("sort_order");
				int child_count = (Integer) sItem.get("child_count");
				
				String cnname = (String) sItem.get("cnname");
				String enname = (String) sItem.get("enname");
				String cncontent = (String) sItem.get("cncontent");
				String encontent = (String) sItem.get("encontent");
				
				int parent_id = 0;
				
				tdao.createResource(depth, parent_id, type, child_count, sort_order, 
						code, cnname, enname, cncontent, encontent, parent_code);
				
				System.out.println(code);
			}
			
			return "success";
		} catch (SQLException e) {
			throw new SQLException("current id is: " + sCurrentId + ".\n" + e.getMessage());
		}
		finally {
		}
	}
	
	public void updateTitle() throws SQLException {
		Connection tconn = null;
		int sStartId = 0;
		try {
			tconn = ConnectionHelper.getConnection(targetConnectionId);
			
			tdao = new DataExchangeDAO();
			tdao.setConnection(tconn, targetConnectionId);
			
			tdao.updateTitleDepth1and2();
			tdao.updateTitleDepth3and4();

		} catch (SQLException e) {
			throw new SQLException("current id is: " + sStartId + ".\n" + e.getMessage());
		}
		finally {
			DBHelper.close(tconn);
		}
	}
	
}
