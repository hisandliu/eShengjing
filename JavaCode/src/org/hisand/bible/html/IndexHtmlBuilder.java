package org.hisand.bible.html;

import java.util.List;
import java.util.Map;

import org.hisand.bible.BibleResourceManager;
import org.hisand.core.FileHelper;
import org.hisand.core.SimpleLogger;

public class IndexHtmlBuilder {
	
	private String rooturl = "";
	private String homepage = "";
	
	private String urllink = HtmlDefined.URL_LINK;
	private String urlsuffix = HtmlDefined.URL_SUFFIX;
	
	public IndexHtmlBuilder(String homepage, String rooturl) {
		super();
		this.rooturl = rooturl;
		this.homepage = homepage;
	}
	
	SimpleLogger log = new SimpleLogger("c:/test.txt");
	private void writeLog(String message) throws Exception {
		log.write(message);
	}
	
	public String build() throws Exception {
		//writeLog("begin");
		
		String template = FileHelper.readStringFromClassFile(IndexHtmlBuilder.class, "index.tmp");
		if (template == null) return null;
		
		template = template.replaceAll("\\$\\{ROOT_URL\\}", rooturl);
		template = template.replaceAll("\\$\\{HOME_PAGE\\}", homepage);
		
		template = template.replaceAll("\\$\\{OLD_COLUMN_1\\}", buildColumnCenter(false, 0));
		template = template.replaceAll("\\$\\{OLD_COLUMN_2\\}", buildColumnCenter(false, 1));
		template = template.replaceAll("\\$\\{OLD_COLUMN_3\\}", buildColumnCenter(false, 2));
		
		template = template.replaceAll("\\$\\{NEW_COLUMN_1\\}", buildColumnCenter(true, 0));
		template = template.replaceAll("\\$\\{NEW_COLUMN_2\\}", buildColumnCenter(true, 1));
		template = template.replaceAll("\\$\\{NEW_COLUMN_3\\}", buildColumnCenter(true, 2));
		
		template = template.replaceAll("\\$\\{TOP_SIDECOLUMN\\}", buildColumnSide(false));
		template = template.replaceAll("\\$\\{BOTTOM_SIDECOLUMN\\}", buildColumnSide(true));
		
		//writeLog("end");
		
		return template;
	}
	
//	<li><a href="#" style="links">创世记-Genesis</a></li>
	private String buildColumnCenter(boolean isnew, int column) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Map<String, Object>> list = null;
		if (isnew) {
			list = getNewSectionList();
		}
		else {
			list = getOldSectionList();
		}
		for (int i = 0; i < list.size(); i++) {
			if (i % 3 == column) {
				Map<String, Object> item = list.get(i);
				String code = (String)item.get("code");
				String enname = (String)item.get("enname");
				String cnname = (String)item.get("cnname");
				String url = rooturl + code + urllink + "1" + urlsuffix;
				String name = cnname + " - " + enname;
				String line = "<li><a href=\"" + url + "\">" + name + "</a></li>";
				sb.append(line + "\n");
			}
		}
		return sb.toString().trim();
	}
	
	private String buildColumnSide(boolean isnew) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Map<String, Object>> list = null;
		if (isnew) {
			list = getNewSectionList();
		}
		else {
			list = getOldSectionList();
		}
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> item = list.get(i);
			String code = (String)item.get("code");
			String enname = (String)item.get("enname");
			String cnname = (String)item.get("cnname");
			String url = rooturl + code + urllink + "1" + urlsuffix;
			String name = cnname + " - " + enname;
			String line = "<li><a href=\"" + url + "\">" + name + "</a></li>";
			sb.append(line + "\n");
		}
		return sb.toString().trim();
	}

	private List<Map<String, Object>> oldSectionList;
	private List<Map<String, Object>> getOldSectionList() throws Exception {
		if (oldSectionList == null) {
			writeLog("begin getOldSectionList");
			oldSectionList = new BibleResourceManager().getChildsByCode("Old_Testament");
			writeLog("end getOldSectionList");
		}
		return oldSectionList;
	}
	
	private List<Map<String, Object>> newSectionList;
	private List<Map<String, Object>> getNewSectionList() throws Exception {
		if (newSectionList == null) {
			writeLog("begin getNewSectionList");
			newSectionList = new BibleResourceManager().getChildsByCode("New_Testament");
			writeLog("end getNewSectionList");
		}
		return newSectionList;
	}
}
