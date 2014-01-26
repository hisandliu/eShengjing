package org.hisand.bible;

import java.util.Map;

import org.hisand.book.manager.TOCManager;

public class BibleCreator {
	
//	private String connectionId = null;
	
	public BibleCreator() {
		super();
		//this(null);
	}
//	
//	public BibleCreator(String connectionId) {
//		super();
//		this.connectionId = connectionId;
//	}
//	
	private void writeLog(Throwable message) {
	}
	
	public int[] createOldTestament() throws Exception {
		return createBook0(1, "旧约", "Old Testament", null, null, "Old Testament", BibleClassType.BOOK);
	}
	
	public int[] createNewTestament() throws Exception {
		return createBook0(1, "新约", "New Testament", null, null, "New Testament", BibleClassType.BOOK);
	}
	
	public int[] createFolder(int parent_id, String cnname, String enname, String ref_id) throws Exception {
		String cncontent = null; 
		String encontent = null;
		return createBook0(parent_id, cnname, enname, cncontent, encontent, ref_id, BibleClassType.FOLDER);
	}
	
	public int[] createArticle(int parent_id, String cnname, String enname, 
			String cncontent, String encontent, String ref_id) throws Exception {
		return createBook0(parent_id, cnname, enname, cncontent, encontent, ref_id, BibleClassType.ARTICLE);
	}
	
	private int[] createTOC(int parent_id, int class_id, String name, String ref_id)
			throws Exception {
		try {
			TOCManager mgr = new TOCManager();
			Map<String, Object> item = mgr.getTreeByRefId(ref_id, parent_id);
			int[] arr = null;
			if (item != null) {
				int tree_id = (Integer)item.get("id");
				int content_id = (Integer)item.get("content_id");
				arr = new int[]{tree_id, content_id};
			}
			else {
				arr = mgr.createTOC(parent_id, class_id, name, ref_id);
			}
			return arr;
		} catch (Exception e) {
			writeLog(e);
			e.printStackTrace();
			throw e;
		}
	}

	private int createContentValue(int content_id, String translate_id,
			String name, String content) throws Exception {
		try {
			TOCManager mgr = new TOCManager();
			Map<String, Object> item = mgr.getContentValue(content_id, translate_id);
			int id = 0;
			if (item != null) {
				id = (Integer)item.get("id");
			}
			else {
				id = mgr.createContentValue(content_id, translate_id, name, content);
			}
			return id;
		} catch (Exception e) {
			writeLog(e);
			e.printStackTrace();
			throw e;
		}
	}
	
	private int[] createBook0(int parent_id, String cnname, String enname, 
			String cncontent, String encontent, String ref_id, int class_id) throws Exception {
		try {
			BibleCreator mgr = new BibleCreator();

			int[] arr = mgr.createTOC(parent_id, class_id, cnname, ref_id);
			int content_id = arr[1];

			mgr.createContentValue(content_id, LanguageType.ZH_CN, cnname,
					cncontent);

			mgr.createContentValue(content_id, LanguageType.EN_US, enname,
					encontent);

			return arr;
		} catch (Exception e) {
			writeLog(e);
			e.printStackTrace();
			throw e;
		}
	}
}
