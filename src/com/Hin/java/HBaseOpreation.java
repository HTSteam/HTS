package com.Hin.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.BasicConfigurator;

import static java.sql.DriverManager.println;
import static org.apache.hadoop.hbase.client.HTable.isTableEnabled;
import static org.apache.hadoop.hbase.constraint.CheckConfigurationConstraint.getConfiguration;
import static org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.Scope.col;

/**
 * Created by yycq on 17-8-21.
 */
public class HBaseOpreation {
    private static Configuration conf =  HBaseConfiguration.create();

    public static ArrayList listTable() {
        ArrayList tables = new ArrayList();
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            HTableDescriptor[] tableDsc = admin.listTables();
            for (int i = 0; i < tableDsc.length; i++) {
                Table temp = new Table();
                temp.setTableName(tableDsc[i].getNameAsString());
                //temp.setEnable(true);
                tables.add(temp);
                System.out.println(temp.getTableName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }
    //获取一张表的所有数据
    public static Table InitialTable(String tableName) throws Exception, IOException{
        Table table = new Table();
        ArrayList rows = new ArrayList();
        HTable hTable = new HTable(conf, tableName);
        table.setEnable(isTableEnabled(tableName));
        Scan scan = new Scan();
        System.out.println(tableName);
        ResultScanner rs = hTable.getScanner(scan);
        int i = 0;
        for(Result result : rs){
            Row row = new Row();
            ArrayList cells = new ArrayList();
            row.setRowKey(Bytes.toString(result.getRow()));
            NavigableMap<byte[], NavigableMap<byte[], byte[]>> familyMap = result.getNoVersionMap();
            for(byte[] fByte : familyMap.keySet()){
                NavigableMap<byte[], byte[]> quaMap = familyMap.get(fByte);
                String familyName = Bytes.toString(fByte);
                for(byte[] quaByte : quaMap.keySet()){
                    byte[] valueByte = quaMap.get(quaByte);
                    String quaName = Bytes.toString(quaByte);
                    String value = Bytes.toString(valueByte);
                    Cell cell = new Cell();
                    cell.setFamily(familyName);
                    cell.setCol(quaName);
                    cell.setValue(value);
                    cells.add(cell);
                    System.out.println(i++);
                }
            }
           
            row.setCells(cells);
            rows.add(row);
        }
        table.setRows(rows);
        table.setTableName(tableName);
        return table;
    }
    //根据表的行健获取表的数据
    public static Table TableSearch(String tableName,String rowkey) throws Exception, IOException{
    	
    	Table table = new Table();
        ArrayList rows = new ArrayList();
        Get get = new Get(Bytes.toBytes(rowkey));
        HTable hTable = new HTable(conf, tableName);
        table.setEnable(isTableEnabled(tableName));
        Scan scan = new Scan(get);
        System.out.println(tableName);
        ResultScanner rs = hTable.getScanner(scan);
        
        int i = 0;
        for(Result result : rs){
            Row row = new Row();
            ArrayList cells = new ArrayList();
            row.setRowKey(Bytes.toString(result.getRow()));
            NavigableMap<byte[], NavigableMap<byte[], byte[]>> familyMap = result.getNoVersionMap();
            for(byte[] fByte : familyMap.keySet()){
                NavigableMap<byte[], byte[]> quaMap = familyMap.get(fByte);
                String familyName = Bytes.toString(fByte);
                for(byte[] quaByte : quaMap.keySet()){
                    byte[] valueByte = quaMap.get(quaByte);
                    String quaName = Bytes.toString(quaByte);
                    String value = Bytes.toString(valueByte);
                    Cell cell = new Cell();
                    cell.setFamily(familyName);
                    cell.setCol(quaName);
                    cell.setValue(value);
                    cells.add(cell);
                    System.out.println(i++);
                }
            }
            row.setCells(cells);
            rows.add(row);
        }
        table.setRows(rows);
        table.setTableName(tableName);
        return table;
    }
    //根据表的行健:列族获取表数据
    public static Table TableSearch(String tableName,String rowkey,String columnfamily) throws Exception, IOException{
    	
    	Table table = new Table();
        ArrayList rows = new ArrayList();
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addFamily(Bytes.toBytes(columnfamily));
        HTable hTable = new HTable(conf, tableName);
        table.setEnable(isTableEnabled(tableName));
        Scan scan = new Scan(get);
        System.out.println(tableName);
        ResultScanner rs = hTable.getScanner(scan);
        
        int i = 0;
        for(Result result : rs){
            Row row = new Row();
            ArrayList cells = new ArrayList();
            row.setRowKey(Bytes.toString(result.getRow()));
            NavigableMap<byte[], NavigableMap<byte[], byte[]>> familyMap = result.getNoVersionMap();
            for(byte[] fByte : familyMap.keySet()){
                NavigableMap<byte[], byte[]> quaMap = familyMap.get(fByte);
                String familyName = Bytes.toString(fByte);
                for(byte[] quaByte : quaMap.keySet()){
                    byte[] valueByte = quaMap.get(quaByte);
                    String quaName = Bytes.toString(quaByte);
                    String value = Bytes.toString(valueByte);
                    Cell cell = new Cell();
                    cell.setFamily(familyName);
                    cell.setCol(quaName);
                    cell.setValue(value);
                    cells.add(cell);
                    System.out.println(i++);
                }
            }
            row.setCells(cells);
            rows.add(row);
        }
        table.setRows(rows);
        table.setTableName(tableName);
        return table;
    }
    //根据表的行健:列族:列标识符获取表数据
    public static Table TableSearch(String tableName,String rowkey,String columnfamily,String qualifier) throws Exception, IOException{
    	
    	Table table = new Table();
        ArrayList rows = new ArrayList();
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addColumn(Bytes.toBytes(columnfamily), Bytes.toBytes(qualifier));
        HTable hTable = new HTable(conf, tableName);
        table.setEnable(isTableEnabled(tableName));
        Scan scan = new Scan(get);
        System.out.println(tableName);
        ResultScanner rs = hTable.getScanner(scan);
        
        int i = 0;
        for(Result result : rs){
            Row row = new Row();
            ArrayList cells = new ArrayList();
            row.setRowKey(Bytes.toString(result.getRow()));
            NavigableMap<byte[], NavigableMap<byte[], byte[]>> familyMap = result.getNoVersionMap();
            for(byte[] fByte : familyMap.keySet()){
                NavigableMap<byte[], byte[]> quaMap = familyMap.get(fByte);
                String familyName = Bytes.toString(fByte);
                for(byte[] quaByte : quaMap.keySet()){
                    byte[] valueByte = quaMap.get(quaByte);
                    String quaName = Bytes.toString(quaByte);
                    String value = Bytes.toString(valueByte);
                    Cell cell = new Cell();
                    cell.setFamily(familyName);
                    cell.setCol(quaName);
                    cell.setValue(value);
                    cells.add(cell);
                    System.out.println(i++);
                }
            }
            row.setCells(cells);
            rows.add(row);
        }
        table.setRows(rows);
        table.setTableName(tableName);
        return table;
    }
    public static void deleteTable(String tableName)throws Exception{
        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists(tableName)){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);

        }

    }

    public static void deleteRow(String tableName, String rowKey)throws Exception{
        try {
            HTable table = new HTable(conf, tableName);
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);
            table.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void deleteCol(String tableName, String rowKey, String family, String col)throws Exception{
        try {
            HTable table = new HTable(conf, tableName);
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));
            table.delete(delete);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void put(String tableName, String rowKey, String family, String col, String value)throws Exception{
        try{
            HTable table = new HTable(conf, tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(col), Bytes.toBytes(value));
            table.put(put);
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public static ArrayList<Cell> GetCellTimestamp(String tableName, String rowKey, String family, String col)throws Exception{
        HTable table = new HTable(conf, tableName);
        table.setAutoFlush(true);
        Get get = new Get(Bytes.toBytes(rowKey));
        get.setMaxVersions(10);
        get.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));

            Result result = table.get(get);
        ArrayList cells = new ArrayList();
        System.out.println("sss");

        for(KeyValue kv : result.list()){
            Cell cell = new Cell();
            println(String.format("row:%s, family:%s, qualifier:%s, qualifiervalue:%s, timestamp:%s.",
                    Bytes.toString(kv.getRow()),
                    Bytes.toString(kv.getFamily()),
                    Bytes.toString(kv.getQualifier()),
                    Bytes.toString(kv.getValue()),
                    kv.getTimestamp()));
            cell.setRowKey(Bytes.toString(kv.getRow()));
            cell.setFamily(Bytes.toString(kv.getFamily()));
            cell.setCol(Bytes.toString(kv.getQualifier()));
            cell.setValue(Bytes.toString(kv.getValue()));
            cell.setTimeStamp(kv.getTimestamp());
            cells.add(cell);
            System.out.println("add");
        }
        return cells;
    }

//    public static int CreateTable(String table){
//
//    }

   
    
}
