package com.Hin.Servlet;

import com.Hin.java.Cell;

import com.Hin.java.HBaseOpreation;
import com.Hin.java.Table;
import com.Hin.java.TableList;

import entity.RowCell;
import utils.HbaseConnection;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static java.io.FileDescriptor.out;
import static org.apache.hadoop.hbase.constraint.CheckConfigurationConstraint.getConfiguration;

/**
 * Created by yycq on 17-8-22.
 */
//@WebServlet(name = "HinServlet")
public class HinServlet extends HttpServlet {
    private static ArrayList tables = new ArrayList();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("initialize"))
            InitialTables(request, response);
        if (action.equals("showTable"))
            try {      
                InitialOneTable(request, response);
            } catch (Exception e) {

            }
        if (action.equals("deleteCol")) {
            try {
                DeleteCol(request, response);
            } catch (Exception e) {

            }
        }
        if (action.equals("deleteRow")) {
            try {
            DeleteRow(request, response);
            } catch (Exception e) {

            }
        }
        if(action.equals("put")){
            try{
                Put(request, response);
            }catch (Exception e){

            }
        }
        if(action.equals("getCellTimestamp")){
            try{
                GetCellTimestamp(request, response);
            }catch (Exception e){

            }
        }
        if(action.equals("Search")) {
        	try {
        		Search(request,response);
        	}catch(Exception e) {
        		
        	}
        }
        if(action.equals("AddRow")) {
        	try {
        		AddRow(request,response);
        	}catch(Exception e) {
        		
        	}
        }
        if(action.equals("AddColumn")) {
        	try {      		
        		AddColumn(request,response);
        	}catch(Exception e) {
        		
        	}
        }
        if(action.equals("createTable")){
            try{
                CreateTable(request, response);
            }catch (Exception e){

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void InitialTables(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        tables = HBaseOpreation.listTable();
        HttpSession session = request.getSession();
        session.setAttribute("tables", tables);
        response.sendRedirect(request.getContextPath()+"/pages/allTables.jsp");
    }

    protected void InitialOneTable(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	String tableName = request.getParameter("tableName");
        Table table = new Table();
        table = HBaseOpreation.InitialTable(tableName);
        HttpSession session = request.getSession();
        session.setAttribute("table", table);
        response.sendRedirect(request.getContextPath()+"/pages/table.jsp");
    }

    protected void DeleteCol(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String tableName = request.getParameter("tableName");
        String families_cols = request.getParameter("families_cols");

        String rowKey = request.getParameter("rowKey");
        String[] temp = families_cols.split(",");

        for(int i = 0; i < temp.length; i++){
            System.out.println(temp[i]);
            String[] temp1 = temp[i].split(":");
            HBaseOpreation.deleteCol(tableName, rowKey, temp1[0], temp1[1]);
        }
        
        response.sendRedirect(request.getContextPath()+"/hinServlet?action=showTable&tableName=" + tableName);
    }

    protected void DeleteRow(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String tableName = request.getParameter("tableName");
        String rows = request.getParameter("rows");
        String[] row = rows.split(",");
        for(String rowKey : row){
            HBaseOpreation.deleteRow(tableName, rowKey);
        }
        response.sendRedirect("/hinServlet?action=showTable&tableName=" + tableName);
    }

    protected void Put(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String tableName = request.getParameter("tableName");
        String rowKey = request.getParameter("rowKey");
        String[] family_col = request.getParameter("family_col").split(":");
        String family = family_col[0];
        String col = family_col[1];
        String value = request.getParameter("value");
        HBaseOpreation.put(tableName, rowKey, family, col, value);
        response.sendRedirect(request.getContextPath()+"/hinServlet?action=showTable&tableName=" + tableName);
    }

    protected void GetCellTimestamp(HttpServletRequest request, HttpServletResponse response)throws Exception{
    	System.out.println("start");
        String tableName = request.getParameter("tableName");
        String rowKey = request.getParameter("rowKey");
        String family_col = request.getParameter("family_col");
        String family = family_col.split(":")[0];
        String col = family_col.split(":")[1];
        ArrayList<Cell> cells = new ArrayList();
        System.out.println("start");
        cells = HBaseOpreation.GetCellTimestamp(tableName, rowKey, family, col);
        System.out.println("start");
        String jsonString = "{\"user\":{\"name\":\"lf\",\"address\":{\"province\":\"广东\",\"city\":\"深圳\"}}}";
        String json = "[";
        for(Cell cell : cells){
            json +=  "{\"rowKey\":\"" + cell.getRowKey() + "\",\"family\":\"" + cell.getFamily() +
                    "\",\"col\":\"" + cell.getCol()
                    + "\",\"value\":\"" + cell.getValue() + "\",\"timestamp\":\"" +
                    cell.getTimeStamp() + "\"},";
        }

        json = json.substring(0, json.length() - 1);
        json += "]";
        System.out.println(json);
        response.getWriter().write(json);
    }
    protected void Search(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
    	    	    	
    	request.setCharacterEncoding("UTF-8");
		String Term = request.getParameter("Term");
		Table oldtable = (Table)request.getSession().getAttribute("table");
		String tablename = oldtable.getTableName();
		if(Term=="") {			
	        Table table = HBaseOpreation.InitialTable(tablename);
	        HttpSession session = request.getSession();
	        session.setAttribute("table", table);
	        response.sendRedirect(request.getContextPath()+"/pages/table.jsp");
		}
		String[] SplitTerm = Term.split(":");
		Table table = new Table();
		if(SplitTerm.length==1) {
			 table = HBaseOpreation.TableSearch(tablename, SplitTerm[0]);	
			 request.getSession().setAttribute("table", table);
			 response.sendRedirect(request.getContextPath()+"/pages/table.jsp");
		}
		if(SplitTerm.length==2) {			
			 table = HBaseOpreation.TableSearch(tablename, SplitTerm[0],SplitTerm[1]);
			 request.getSession().setAttribute("table", table);
			 response.sendRedirect(request.getContextPath()+"/pages/table.jsp");		
		}
		if(SplitTerm.length==3) {
			 table = HBaseOpreation.TableSearch(tablename, SplitTerm[0],SplitTerm[1],SplitTerm[2]);
			 request.getSession().setAttribute("table", table);
			 response.sendRedirect(request.getContextPath()+"/pages/table.jsp");		
		}
		response.sendRedirect(request.getContextPath()+"/pages/table.jsp");
    }
    protected void AddRow(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		HbaseConnection main = new HbaseConnection();
		String rowkey = request.getParameter("rowkey");
		String RowNum = request.getParameter("RowNum");
		Table oldtable = (Table)request.getSession().getAttribute("table");
		String columnfamily;
		String value;
		for(int i = 1;i <= Integer.parseInt(RowNum);i++) {
			columnfamily = request.getParameter("columnfamily"+i);
			String[] familycolumn = columnfamily.split(":");
			value = request.getParameter("cell_value"+i);
			if(columnfamily!=null && value!=null) {
				try {
					HBaseOpreation.put(oldtable.getTableName(), rowkey, familycolumn[0], familycolumn[1], value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}			 
	        Table newtable = new Table();
	        newtable = HBaseOpreation.InitialTable(oldtable.getTableName());
	        HttpSession session = request.getSession();
	        session.setAttribute("table", newtable);
	        response.sendRedirect(request.getContextPath()+"/pages/table.jsp");
    }
    protected void AddColumn(HttpServletRequest request, HttpServletResponse response)throws Exception{
    	String rowkey = request.getParameter("rowkey");
    	String FamilyColumn = request.getParameter("FamilyColumn");
    	String Value = request.getParameter("Value");
    	Table oldtable = (Table)request.getSession().getAttribute("table");
        String[] familycolumn = FamilyColumn.split(":");
    	if(familycolumn.length==2) {
        	HBaseOpreation.put(oldtable.getTableName(), rowkey, familycolumn[0], familycolumn[1], Value);
            Table newtable = new Table();
            newtable = HBaseOpreation.InitialTable(oldtable.getTableName());
            HttpSession session = request.getSession();
            session.setAttribute("table", newtable);
            response.sendRedirect(request.getContextPath()+"/pages/table.jsp");
    	}
    	else {
    		System.out.println("输入列族格式不符合规范");
    	}
    }
    protected void CreateTable(HttpServletRequest request, HttpServletResponse response)throws Exception{
        System.out.println("test");
        String tableName = request.getParameter("tableName");
        String cols = request.getParameter("cols");
        JSONObject cols_json = new JSONObject(cols);


    }
}
