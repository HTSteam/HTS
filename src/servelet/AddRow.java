package servelet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.RowCell;
import utils.HbaseConnection;

/**
 * Servlet implementation class AddRow
 */
//@WebServlet("/AddRow")
public class AddRow extends HttpServlet {
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
		HbaseConnection main = new HbaseConnection();
		String rowkey = request.getParameter("rowkey");
		String RowNum = request.getParameter("RowNum");
		String tablename = (String)request.getSession().getAttribute("tablename");
		System.out.println(RowNum);
		String columnfamily;
		String value;
		for(int i = 1;i <= Integer.parseInt(RowNum);i++) {
			columnfamily = request.getParameter("columnfamily"+i);
			String[] familycolumn = columnfamily.split(":");
			value = request.getParameter("cell_value"+i);
			if(columnfamily!=null && value!=null) {
				try {
					main.putData(tablename, rowkey, familycolumn[0], familycolumn[1], value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ArrayList<RowCell> cells = main.getResultScann(tablename);
		request.getSession().setAttribute("cells",cells);
		response.sendRedirect(request.getContextPath()+"/pages/DisplayData.jsp");
	}

}
