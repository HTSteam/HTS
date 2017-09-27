package servelet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Table;
import utils.HbaseConnection;


/**
 * Servlet implementation class test
 */
//@WebServlet("/TablesDisplayAction")
public class TablesDisplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		HbaseConnection main = new HbaseConnection();
		ArrayList<Table> tables = main.ListTables();
		for(int i=0;i < tables.size();i++) {
			System.out.println(tables.get(i).getTableName());
		}		
		request.getSession().setAttribute("tables", tables);
		response.sendRedirect(request.getContextPath()+"/pages/DisplayTables.jsp");
	}

}
