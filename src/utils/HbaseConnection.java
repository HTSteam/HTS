package utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import entity.RowCell;
import entity.Table;
import entity.TableData;
import entity.TableRow;


public class HbaseConnection {
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    
    public HbaseConnection() {
    	init();
    }
    public static void init() {
        configuration = HBaseConfiguration.create();       
        configuration.set("hbase.rootdir", "hdfs://localhost:9000/hbase");

        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();         	
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }
    //列出所有的表
    public static ArrayList<Table> ListTables() {
        TableName[] tablename = null;
        ArrayList<Table> tables = new ArrayList<Table>();
       
		try {
			/*获取每个表的表名*/
			tablename = admin.listTableNames();				
	        for(int i = 0;i <  tablename.length;i++) {
	        	System.out.println(tablename[i]);
	        	Table table = new Table();
	        	/*获取每个表是否可操作*/
	        	boolean enable = admin.isTableEnabled(tablename[i]);
	        	table.setTableName(tablename[i].toString());
	        	table.setEnable(enable);	        	
	        	tables.add(table);
	        }  
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
    }
    //列出一个表的所有数据
    public static ArrayList<RowCell> getResultScann(String tableName) throws IOException {
        Scan scan = new Scan();
        ResultScanner rs = null;
        HTable table = new HTable(configuration, Bytes.toBytes(tableName));
 
        ArrayList <RowCell> cells = new ArrayList<RowCell>();
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
       
                for (KeyValue kv : r.list()) {
                	
                	RowCell cell = new RowCell();
                	cell.setTablename(tableName);
                	cell.setRowkey(Bytes.toString(kv.getRow()));
                	cell.setFamily(Bytes.toString(kv.getFamily()));
                	cell.setQualifier(Bytes.toString(kv.getQualifier()));
                	cell.setValue(Bytes.toString(kv.getValue()));
                	cell.setTimestamp(kv.getTimestamp());
                	cells.add(cell);
                }
            }
        } finally {
            rs.close();
        }
        return cells;
    }
    //根据指定rowkey查找
    public static ArrayList<RowCell> getResultByRowkey(String tableName, String rowKey)
            throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = new HTable(configuration, Bytes.toBytes(tableName));// 获取表
        Result result = table.get(get);
        ArrayList <RowCell> cells = new ArrayList<RowCell>();
        for (KeyValue kv : result.list()) {
        	RowCell cell = new RowCell();
        	cell.setTablename(tableName);
        	cell.setRowkey(Bytes.toString(kv.getRow()));
        	cell.setFamily(Bytes.toString(kv.getFamily()));
        	cell.setQualifier(Bytes.toString(kv.getQualifier()));
        	cell.setValue(Bytes.toString(kv.getValue()));
        	cell.setTimestamp(kv.getTimestamp());
        	cells.add(cell);
        }
        return cells;
    }
    public static ArrayList<RowCell> getResultByRowkey(String tableName, String rowKey,String columnfamily)
            throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = new HTable(configuration, Bytes.toBytes(tableName));// 获取表
        get.addFamily(Bytes.toBytes(columnfamily));
        Result result = table.get(get);
        ArrayList <RowCell> cells = new ArrayList<RowCell>();
        for (KeyValue kv : result.list()) {
        	RowCell cell = new RowCell();
        	cell.setTablename(tableName);
        	cell.setRowkey(Bytes.toString(kv.getRow()));
        	cell.setFamily(Bytes.toString(kv.getFamily()));
        	cell.setQualifier(Bytes.toString(kv.getQualifier()));
        	cell.setValue(Bytes.toString(kv.getValue()));
        	cell.setTimestamp(kv.getTimestamp());
        	cells.add(cell);
        }
        return cells;
    }
    public static ArrayList<RowCell> getResultByRowkey(String tableName, String rowKey,String columnfamily,String qualifier)
            throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable table = new HTable(configuration, Bytes.toBytes(tableName));// 获取表
        get.addColumn(Bytes.toBytes(columnfamily), Bytes.toBytes(qualifier));
        Result result = table.get(get);
        ArrayList <RowCell> cells = new ArrayList<RowCell>();
        for (KeyValue kv : result.list()) {
        	RowCell cell = new RowCell();
        	cell.setTablename(tableName);
        	cell.setRowkey(Bytes.toString(kv.getRow()));
        	cell.setFamily(Bytes.toString(kv.getFamily()));
        	cell.setQualifier(Bytes.toString(kv.getQualifier()));
        	cell.setValue(Bytes.toString(kv.getValue()));
        	cell.setTimestamp(kv.getTimestamp());
        	cells.add(cell);
        }
        return cells;
    }
    //更新数据  插入数据
    //@Override
    public static void putData(String tableName, String rowKey, String familyName, String columnName, String value)
            throws Exception {
        HTable htable=new HTable(configuration, Bytes.toBytes(tableName));
        Put put=new Put(Bytes.toBytes(rowKey));
        put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
        htable.put(put);
         
    }
}
