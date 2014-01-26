package org.hisand.bible.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hisand.bible.html.HtmlBuilder;

import com.sun.jndi.toolkit.url.UrlUtil;

public class BibleServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private PrintWriter out = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8"); 
		String contextPath = request.getContextPath();
		if (contextPath == null) contextPath = "";
	
		String path = request.getRequestURI();
		path = UrlUtil.decode(path);

		String link = path.replaceAll("[/]+", "/");
		if (link.startsWith(contextPath)) {
			link = link.substring(contextPath.length());
		}
		if (link.startsWith("/")) {
			link = link.substring(1);
		}
		if (link.endsWith("/")) {
			link = link.substring(0, link.length() - 1);
		}
		if (link.length() == 0) {
			response.sendRedirect(contextPath + "/index.html");
			return;
		}
		if (link.length() < 6) {
			return;
		}
		
		link = link.substring(0, link.length() - 5);
		
		int port = request.getServerPort();
		String rooturl = "http://" + request.getServerName() + ":" + port + contextPath + "/";
		String homepage = "http://" + request.getServerName() + ":" + port + contextPath + "/";
		
		String html = "";
		try {
			html = new HtmlBuilder(homepage, rooturl, link).build();
		} catch (Exception e) {
			html = "Error!";
		}
		
		out = response.getWriter();
		
		/*
		out.println("root_url:" + rooturl);
		out.println("home_page:" + homepage);
		out.println("getContextPath:" + request.getContextPath());
		out.println("getLocalName:" + request.getLocalName());
		out.println("getLocalAddr:" + request.getLocalAddr());
		out.println("getRequestURI:" + request.getRequestURI());
		*/
		out.print(html);
		
		out.flush();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
