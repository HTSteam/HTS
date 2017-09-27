package entity;

import java.util.ArrayList;

public class TableRow {
	private String tablename;
	private String rowkey;
	private ArrayList<RowCell> cells;
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getRowkey() {
		return rowkey;
	}
	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}
	public ArrayList<RowCell> getCells() {
		return cells;
	}
	public void setCells(ArrayList<RowCell> cells) {
		this.cells = cells;
	}
}
