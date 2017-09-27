package com.yw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yw.Dao.TableDao;

/**
 * Servlet implementation class EditTable
 */
//@WebServlet("/EditTable")
public class EditTable extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Edit");
		String NewTablename = request.getParameter("New_TableName");
		String OldTablename = (String)request.getSession().getAttribute("tablename");
		System.out.println(NewTablename);
		System.out.println(OldTablename);
		if(NewTablename!=OldTablename) {
			try {
				TableDao.ChangeTableName(OldTablename,NewTablename);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("1");
        out.close();
	}

}
