package com.yw.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yw.Dao.*;
import com.yw.util.StringUtil;
import com.yw.bean.*;
import com.yw.Dao.TableDao;
public class TableServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action==null) 
			action="";
		if(action.equals("Enable"))
			Enable(request,response);
		if(action.equals("drop"))
			drop(request,response);
		if(action.equals("querykey"))
			querykey(request,response);
	}
	/**
	 * 功能：处理show.jsp的Enable的点击事件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void Enable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tablename = request.getParameter("tablename");
		if(TableDao.isTableEnable(tablename)) {
			TableDao.setDisable(tablename);
		}else {
			TableDao.setEnable(tablename);
		}
		response.sendRedirect("index.jsp");
	}
	protected void drop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tablestr = request.getParameter("tablename");
		String[] tablelist= StringUtil.convertStrToArray(tablestr);
		for(String table : tablelist) {
			TableDao.dropTable(table);
		}
		response.sendRedirect("index.jsp");
	}
	protected void querykey(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		ArrayList<TableBean> result = TableDao.querybykey(key);
		HttpSession session = request.getSession();
		session.setAttribute("tablist", result);
		response.sendRedirect("show.jsp");
	}
}
