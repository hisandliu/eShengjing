package org.hisand.bible.html;

import java.util.List;
import java.util.Map;

import org.hisand.bible.BibleResourceManager;
import org.hisand.core.FileHelper;

public class ArticleHtmlBuilder {

	private String rooturl = "";
	private String homepage = "";
	
	private String urllink = HtmlDefined.URL_LINK;
	private String urlsuffix = HtmlDefined.URL_SUFFIX;
	
	private String code = "";
	private Map<String, Object> currentItem = null;
	private Map<String, Object> parentItem = null;
	private Map<String, Object> parentParentItem = null;
	
	public ArticleHtmlBuilder(String homepage, String rooturl, String code) {
		super();
		this.rooturl = rooturl;
		this.code = code;
		this.homepage = homepage;
	}
	
	public ArticleHtmlBuilder(String homepage, String rooturl, Map<String, Object> currentItem, 
			Map<String, Object> parentItem) {
		super();
		this.rooturl = rooturl;
		this.currentItem = currentItem;
		this.parentItem = parentItem;
		this.code = (String) currentItem.get("code");
		this.homepage = homepage;
	}
	
	public String build() throws Exception {
		Map<String, Object> item = getCurrentItem();
		if (item == null) return null;
		String template = FileHelper.readStringFromClassFile(ArticleHtmlBuilder.class, "article.tmp");
		if (template == null) return null;
		
		template = template.replaceAll("\\$\\{ROOT_URL\\}", rooturl);
		template = template.replaceAll("\\$\\{HOME_PAGE\\}", homepage);
		
		template = template.replaceAll("\\$\\{TITLE\\}", buildTitle());
		template = template.replaceAll("\\$\\{DESCRIPTION\\}", buildDescription());
		template = template.replaceAll("\\$\\{KEYWORDS\\}", buildKeywords());
		
		template = template.replaceAll("\\$\\{ARTICLE_TITLE\\}", buildArticleTitle());
		
		String nav = buildNav();
		template = template.replaceAll("\\$\\{ARTICLE_NAV_TOP\\}", nav);
		template = template.replaceAll("\\$\\{ARTICLE_NAV_BOTTOM\\}", nav);
		
		template = template.replaceAll("\\$\\{ARTICLE_LIST\\}", buildArticleList());
		
		Map<String, Object> ppitem = getParentParentItem();
		String ppitemcode = (String) ppitem.get("code");
		String top = "";
		String bottom = "";
		if ("Old_Testament".equals(ppitemcode)) {
			top = buildColumnSide(false);
			bottom = buildColumnSide(true);
		}
		else {
			top = buildColumnSide(true);
			bottom = buildColumnSide(false);
		}
		template = template.replaceAll("\\$\\{TOP_SIDECOLUMN\\}", top);
		template = template.replaceAll("\\$\\{BOTTOM_SIDECOLUMN\\}",bottom);
		
		return template;
	}
	
	private String buildTitle() throws Exception {
		Map<String, Object> item = getCurrentItem();
		Map<String, Object> pitem = getParentItem();
		String name = (String) item.get("cnname");
		String pname = (String) pitem.get("cnname");
		String r = pname + "(" + name + ")" + " - 圣经在线阅读";
		return r;
	}
	
	private String buildDescription() throws Exception {
		return "";
	}
	
	private String buildKeywords() throws Exception {
		return "";
	}
	
	//旧约 - 创世记(Genesis) - 2/50
	private String buildArticleTitle() throws Exception {
		Map<String, Object> item = getCurrentItem();
		Map<String, Object> pitem = getParentItem();
		Map<String, Object> ppitem = getParentParentItem();
		String ppname = (String) ppitem.get("cnname");
		String pcnname = (String) pitem.get("cnname");
		String prenname = (String) pitem.get("enname");
		int sort_order = (Integer) item.get("sort_order");
		
 		String r = "" + ppname + " - " + pcnname + "(" + prenname + ") - 第 " + sort_order + " 章";
		return r;
	}
	
	//<span class='pages'>Page 1 of 7</span><span class='current'>1</span><a href='' class='page larger'>2</a>
//	<a href='' class='page larger'>3</a><a href='' class='page larger'>4</a>
//	<a href='' class='page larger'>5</a><span class='extend'>...</span>
//	<a href='' class='nextpostslink'>»</a>
//	<a href='' class='last'>Last »</a>
	private String buildNav() throws Exception {
		Map<String, Object> item = getCurrentItem();
		Map<String, Object> pitem = getParentItem();
		int sort_order = (Integer) item.get("sort_order");
		int count = (Integer) pitem.get("child_count");
		String pcode = (String) pitem.get("code");
		int size = 5;
		
		StringBuffer sb = new StringBuffer();
		sb.append("<span class=\"pages\">共 " + count + " 章</span>");
		
		String line = "";
		String url = "";
	
		if (count <= 1) {
			return sb.toString().trim();
		}
		
		int lastsizeindex = (count - 1) / size;
		int sizeindex = (sort_order - 1) / size;
		int beginindex = sizeindex * size + 1; 
		int endindex = sizeindex * size + size;
		if (sizeindex >= lastsizeindex) {
			endindex = count;
		}
		
		if (sort_order > size) {
			url = rooturl + pcode + urllink + "1" + urlsuffix;
			line = "<a href=\"" + url + "\" class='first'>首</a>";
			sb.append(line);
		}
		
		if (sort_order > 1) {
			url = rooturl + pcode + urllink + (sort_order - 1) + urlsuffix;
			line = "<a href=\"" + url + "\" class='previouspostslink'>«</a>";
			sb.append(line);
		}
		
		for (int i = beginindex; i <= endindex; i++) {
			url = rooturl + pcode + urllink + i + urlsuffix;
			if (i == sort_order) {
				line = "<span class=\"current\">" + sort_order + "</span>";
			}
			else if (i < sort_order) {
				line = "<a href=\"" + url + "\" class=\"page smaller\">" + i + "</a>";
			}
			else {
				line = "<a href=\"" + url + "\" class=\"page larger\">" + i + "</a>";
			}
			sb.append(line);
		}
		
		if (sort_order < count) {
			url = rooturl + pcode + urllink + (sort_order + 1) + urlsuffix;
			line = "<a href=\"" + url + "\" class='nextpostslink'>»</a>";
			sb.append(line);
		}
		
		if (sizeindex < lastsizeindex) {
			url = rooturl + pcode + urllink + count + urlsuffix;
			line = "<a href=\"" + url + "\" class='last'>尾</a>";
			sb.append(line);
		}
		
		return sb.toString().trim();
	}
	
	/*
	<div >
		<span>99:99</span>
		<div>
			<p class="cn">中文</p>
			<p class="en">英文</p>
		</div>
	</div>
	*/
	private String buildArticleList() throws Exception {
		String r = new BibleResourceManager().getChapterChildHtml(code);
		return r;
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
	
	private Map<String, Object> getCurrentItem() throws Exception {
		if (currentItem == null) {
			currentItem = new BibleResourceManager().getItemByCode(code);
		}
		return currentItem;
	}
	
	private Map<String, Object> getParentItem() throws Exception {
		if (parentItem == null) {
			parentItem = new BibleResourceManager().getParentIdItemByCode(code);
		}
		return parentItem;
	}
	
	private Map<String, Object> getParentParentItem() throws Exception {
		if (parentParentItem == null) {
			Map<String, Object> item = getParentItem();
			String code = (String) item.get("code");
			parentParentItem = new BibleResourceManager().getParentIdItemByCode(code);
		}
		return parentParentItem;
	}

	private List<Map<String, Object>> oldSectionList;
	private List<Map<String, Object>> getOldSectionList() throws Exception {
		if (oldSectionList == null) {
			oldSectionList = new BibleResourceManager().getChildsByCode("Old_Testament");
		}
		return oldSectionList;
	}
	
	private List<Map<String, Object>> newSectionList;
	private List<Map<String, Object>> getNewSectionList() throws Exception {
		if (newSectionList == null) {
			newSectionList = new BibleResourceManager().getChildsByCode("New_Testament");
		}
		return newSectionList;
	}
	
}
