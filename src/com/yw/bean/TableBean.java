package com.yw.bean;

import com.Hin.java.Row;
import org.apache.hadoop.hbase.HColumnDescriptor;

import java.util.ArrayList;

public class TableBean {
	//表名称
	private String tableName="";
	//表的状态
	private String status="";
	//列族
	private String[] cols=null;

	private ArrayList<Row> rows;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getCols() {
		return cols;
	}
	public void setCols(String[] cols) {
		this.cols = cols;
	}
	
}
