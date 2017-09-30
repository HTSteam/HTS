package com.yw.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

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
		response.setContentType("text/json; charset=utf-8");
		String tableName = request.getParameter("tableName");
		String tableInfo = request.getParameter("tableInfo");

		String message;
		if(TableDao.createTable(tableName, tableInfo).equals("success")){
			message="建表成功！";
			System.out.println(message);

			String success = "{\"status\":\"success\"}";
			response.getWriter().write(success);

			System.out.println(message);
		}

	}
}
