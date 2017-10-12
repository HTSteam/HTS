package com.yw.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yw.Dao.*;
import com.yw.bean.TableBean;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

public class IndexServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<TableBean> list = TableDao.tabList();
		String p = request.getParameter("page");
		//当前页数
		int page;
		try{
			page = Integer.valueOf(p);
		}catch(NumberFormatException e) {
			page = 1;
		}

		//每页显示多少条数据
		int pageShowNum = 10;
		//总数据
		int tableNum = list.size();
		int pageNum = tableNum % pageShowNum == 0 ? tableNum / pageShowNum : tableNum / pageShowNum + 1;
		if(page <= 0){
			page = 1;
		}
		if(page >= pageNum){
			page = pageNum;
		}
		//本页起始数据序列号
		int beginIndex = (page - 1) * pageShowNum;
		//本页末尾序号
		int endIndex = beginIndex + pageShowNum;
		if(endIndex > tableNum)
			endIndex = tableNum;


		HttpSession session = request.getSession();
		session.setAttribute("tableNum", tableNum);
		session.setAttribute("pageShowNum", pageShowNum);
		session.setAttribute("pageNum", pageNum);
		session.setAttribute("beginIndex", beginIndex);
		session.setAttribute("endIndex", endIndex);
		session.setAttribute("page", page);
		session.setAttribute("tableList", list);
		response.sendRedirect("show.jsp");
	}

}
