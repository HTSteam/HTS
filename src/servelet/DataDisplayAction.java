package servelet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.RowCell;
import entity.Table;
import entity.TableData;
import utils.HbaseConnection;

/**
 * Servlet implementation class DataDisplay
 */
//@WebServlet("/DataDisplay")
public class DataDisplayAction extends HttpServlet {
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
		String tablename = request.getParameter("tablename");
		HbaseConnection main = new HbaseConnection();		
		ArrayList<RowCell> cells = main.getResultScann(tablename);
		request.getSession().setAttribute("cells",cells);
		request.getSession().setAttribute("tablename", tablename);
		response.sendRedirect(request.getContextPath()+"/pages/DisplayData.jsp");
	}

}
