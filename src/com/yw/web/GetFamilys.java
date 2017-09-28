package com.yw.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yw.Dao.TableDao;
import com.yw.bean.FamilyBean;

/**
 * Servlet implementation class GetFamilys
 */
//@WebServlet("/GetFamilys")
//WDZ 9/28
public class GetFamilys extends HttpServlet {

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

		String tablename = request.getParameter("tablename");
		ArrayList<FamilyBean> Familys = TableDao.getAllFamilys(tablename);
		request.getSession().setAttribute("familys", Familys);
		request.getSession().setAttribute("tablename", tablename);
		response.sendRedirect("index.jsp");
	}

}
