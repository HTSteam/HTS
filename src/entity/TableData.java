package entity;

import java.util.ArrayList;

public class TableData {
	private String TableName;
	private ArrayList<TableRow> rows;
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}
	public ArrayList<TableRow> getRows() {
		return rows;
	}
	public void setRows(ArrayList<TableRow> rows) {
		this.rows = rows;
	}
}
