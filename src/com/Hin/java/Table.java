package com.Hin.java;

import org.apache.hadoop.hbase.TableName;

import java.util.ArrayList;

/**
 * Created by yycq on 17-8-22.
 */
public class Table {

    private String tableName;
    boolean enable;
    private ArrayList<Row> rows;
    private ArrayList<String> families;

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    private ArrayList<String> columns;
    private ArrayList<String> rowKeys;
    private ArrayList<Cell> cells;

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public ArrayList<String> getRowKeys() {
        return rowKeys;
    }

    public void setRowKeys(ArrayList<String> rowKeys) {
        this.rowKeys = rowKeys;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    public ArrayList<String> getFamilies() {
        return families;
    }

    public void setFamilies(ArrayList<String> families) {
        this.families = families;
    }

    public boolean isEnable() {
        return enable;

    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
