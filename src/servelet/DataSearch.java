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
import utils.HbaseConnection;

/**
 * Servlet implementation class DataSearch
 */
//by wdz
//@WebServlet("/DataSearch")
public class DataSearch extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		String Term = request.getParameter("Term");
		System.out.println(Term);
		String tablename = (String)request.getSession().getAttribute("tablename");
		String[] SplitTerm = Term.split(":");
		if(SplitTerm.length==1) {
			HbaseConnection main = new HbaseConnection();		
			ArrayList<RowCell> cells = main.getResultByRowkey(tablename, SplitTerm[0]);
			request.getSession().setAttribute("cells",cells);
		}
		if(SplitTerm.length==2) {
			HbaseConnection main = new HbaseConnection();		
			ArrayList<RowCell> cells = main.getResultByRowkey(tablename, SplitTerm[0],SplitTerm[1]);
			request.getSession().setAttribute("cells",cells);		
		}
		if(SplitTerm.length==3) {
			HbaseConnection main = new HbaseConnection();		
			ArrayList<RowCell> cells = main.getResultByRowkey(tablename, SplitTerm[0],SplitTerm[1],SplitTerm[2]);
			request.getSession().setAttribute("cells",cells);	
		}
		response.sendRedirect(request.getContextPath()+"/pages/DisplayData.jsp");
	}

}
