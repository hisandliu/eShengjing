package org.hisand.bible.html;

public class HtmlBuilder {
	
	private String rooturl;
	private String homepage;
	private String parturl;
	
	public HtmlBuilder(String homepage, String rooturl, String parturl) {
		this.rooturl = rooturl;
		this.homepage = homepage;
		this.parturl = parturl;
	}
	
	public String build() throws Exception {
		String html = "";
		if ("index".equals(parturl)) {
			html = new IndexHtmlBuilder(homepage, rooturl).build();
		}
		else if ("about".equals(parturl)) {
			html = new AboutHtmlBuilder(homepage, rooturl).build();
		}
		else {
			html = new ArticleHtmlBuilder(homepage, rooturl, parturl).build();
		}
		return html;
	}
	
}
