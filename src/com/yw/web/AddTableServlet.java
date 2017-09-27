package com.yw.web;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yw.util.StringUtil;
import com.yw.Dao.TableDao;
import com.yw.bean.TableBean;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.mortbay.util.ajax.JSON;

public class AddTableServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ttest");
		String tableName = request.getParameter("tableName");
		String cols = request.getParameter("cols");
		try{
			JSONObject cols_json = new JSONObject(cols);
			Iterator iterator = cols_json.keys();
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				System.out.println(key);
				String value = cols_json.getString(key);
				System.out.println(key);
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		String message;
		if(TableDao.createTable(tableName, cols).equals("success")){
			message="建表成功！";
			System.out.println(message);
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			message="建表失败！";
			System.out.println(message);
			request.getSession().setAttribute("message", message);
			//服务器端跳转
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

//		String[] collist= StringUtil.convertStrToArray(col_str);
//		request.setAttribute("tableName", tableName);
//		request.setAttribute("collist", collist);
//		String message = "";
//		if(StringUtil.isEmpty(tableName)||StringUtil.isEmpty(col_str))
//		{
//			//传递错误信息
//			message="表名或列族名为空！";
//			request.getSession().setAttribute("message", message);
//			//服务器端跳转
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//			return;
//		}
//		TableBean table = new TableBean();
//		table.setTableName(tableName);
//		table.setCols(collist);
//		if(TableDao.createTable(table)) {
//			message="建表成功！";
//			request.getSession().setAttribute("message", message);
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//		}else {
//			//传递错误信息
//			message="建表失败！";
//			request.getSession().setAttribute("message", message);
//			//服务器端跳转
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//			return;
//		}
	}
}
