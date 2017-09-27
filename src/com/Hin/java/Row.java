package com.Hin.java;

import java.util.ArrayList;

/**
 * Created by yycq on 17-8-22.
 */
public class Row {
    private String tableName;
    private String rowKey;
    private ArrayList<Cell> cells;


    public ArrayList<Cell> getCells() {return cells;}

    public void setCells(ArrayList cells) {
        this.cells = cells;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
