package org.hisand.bible;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hisand.db.ResultSetHandler;

public class ChapterHtmlHandler implements ResultSetHandler<String> {
	
	public ChapterHtmlHandler() {
		super();
	}
	
	@Override
	public String handle(ResultSet rs) throws SQLException {
		StringBuffer sb = new StringBuffer();
		while (rs.next()) {
			String line = toOne(rs);
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString().trim();
	}
	
//<div >
//	<span>99:99</span>
//	<div>
//		<p class="cn">中文</p>
//		<p class="en">英文</p>
//	</div>
//</div>
	private String toOne(ResultSet rs) throws SQLException {
		String name = rs.getString("cnname");
		String cncontent = rs.getString("cncontent");
		String encontent = rs.getString("encontent");
		
        StringBuffer sb = new StringBuffer();
        sb.append("<div>\n");
        sb.append("\t<span>" + name + "</span>\n");
        sb.append("\t<div>\n");
        sb.append("\t\t<p class=\"cn\">" + cncontent + "</p>\n");
        sb.append("\t\t<p class=\"en\">" + encontent + "</p>\n");
        sb.append("\t</div>\n");
        sb.append("</div>");
        
        return sb.toString();
    }
	
	
}