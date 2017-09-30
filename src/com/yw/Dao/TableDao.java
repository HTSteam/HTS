package com.yw.Dao;

import com.yw.util.*;

import static org.apache.hadoop.hbase.constraint.CheckConfigurationConstraint.getConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.yw.bean.FamilyBean;
import com.yw.bean.TableBean;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.mortbay.util.ajax.JSON;

public class TableDao {
	private static Configuration conf = getConfiguration();
//	public static boolean createTable(TableBean table) {
//		Connection con = HbaseUtil.getCon();
//		boolean status = false;
//		if(con == null)
//		{
//			System.out.println("获取数据库连接失败");
//		}else {
//			try {
//				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
//				HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(table.getTableName()));
//				String[] cols = table.getCols();
//				for(String col:cols) {
//					if(col != "")
//					{
//						HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
//						desc.addFamily(hColumnDescriptor);
//					}
//
//				}
//				if(hbaseadmin.tableExists(table.getTableName())) {
//					System.out.println("table is exists!");
//					System.exit(0);
//				}else {
//					hbaseadmin.createTable(desc);
//					status = true;
//					System.out.println("成功建表！");
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				System.out.println("建表失败！");
//				e.printStackTrace();
//			}finally {
//				if(con != null) {
//					try {
//						con.close();
//					}catch(Exception e){
//						System.out.println("链接关闭失败！");
//						e.printStackTrace();
//					}
//				}
//			}
//
//		}
//		return status;
//	}
	public static String createTable(String tableName, String cols){
		Connection con = HbaseUtil.getCon();
		if(con == null)
		{
			System.out.println("获取数据库连接失败");
			return "error";
		}else {
			try {
				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
				if(hbaseadmin.tableExists(tableName)){
					System.out.println("table is already exist");
					return "table is already exist";
				}

				HTableDescriptor htdesc = new HTableDescriptor(tableName);
				JSONArray cols_json = new JSONArray(cols);
				for(int i = 0; i < cols_json.length(); i++) {


					JSONObject job = cols_json.getJSONObject(i);
					Iterator it = job.keys();
					String key = (String)it.next();
					String value = job.getString(key);
					HColumnDescriptor hcdesc = new HColumnDescriptor(value);
//					while(it.hasNext()){
//						key = (String)it.next();
//						value = job.getString(key);
//						switch(key){
//							case "MaxVersion":
//								hcdesc.setMaxVersions(Integer.parseInt(value));
//								break;
//							case "MinVersion":
//								hcdesc.setMinVersions(Integer.parseInt(value));
//								break;
//							case "TimeToLive":
//								hcdesc.setTimeToLive(18000);
//								break;
//							case "Inmemory":
//								hcdesc.setInMemory(Boolean.getBoolean(value));
//								break;
//							case "BlockSize":
//								hcdesc.setBlocksize(Integer.parseInt(value));
//							case "ComressionType":
////								hcdesc.setCompressionType();
//								break;
//						}
//					}
					htdesc.addFamily(hcdesc);
				}
				hbaseadmin.createTable(htdesc);
				return "success";
			}catch (IOException e){
				e.printStackTrace();
				return "error";
			}catch (JSONException e){
				e.printStackTrace();
				return "error";
			}

		}
	}


	public static ArrayList<TableBean> tabList() {
		HTableDescriptor[] list=null;
		Connection con = HbaseUtil.getCon();
		if(con == null){
			System.out.println("获取数据库连接失败！");
		}else {
			try {
				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
				list=hbaseadmin.listTables();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(con != null) {
					try {
						con.close();
					}catch(Exception e){
						System.out.println("链接关闭失败！");
						e.printStackTrace();
					}
				}
			}
		}
		ArrayList<TableBean> tablist = new ArrayList<TableBean>();
		for(HTableDescriptor tab: list) {
			TableBean table = new TableBean();
	         table.setTableName(tab.getNameAsString());
	         if(isTableEnable(tab.getNameAsString())) {
	        	 table.setStatus("Enable");
	         }else {
	        	 table.setStatus("Disable");
	         }
	         tablist.add(table);
		}
		return tablist;
	}
	/**
	 * 功能：验证表是否为不可用，true：表是可用的，false:表是不可用的
	 * @param tableName 表名
	 * @return 表的状态
	 */
	public static boolean isTableEnable(String tableName) {
		Connection con = HbaseUtil.getCon();
		boolean status=false;
		if(con == null) {
			System.out.println("连接获取失败！");
		}else {
			try {
				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
				status=hbaseadmin.isTableEnabled(tableName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(con != null) {
					try {
						con.close();
					}catch(Exception e){
						System.out.println("链接关闭失败！");
						e.printStackTrace();
					}
				}
			}
		}
		return status;
	}
	public static void setEnable(String tableName) {
		Connection con = HbaseUtil.getCon();
		if(con == null) {
			System.out.println("连接获取失败！");
		}else {
			try {
				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
				hbaseadmin.enableTable(tableName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(con != null) {
					try {
						con.close();
					}catch(Exception e){
						System.out.println("链接关闭失败！");
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static void setDisable(String tableName) {
		Connection con = HbaseUtil.getCon();
		if(con == null) {
			System.out.println("连接获取失败！");
		}else {
			try {
				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
				hbaseadmin.disableTable(tableName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(con != null) {
					try {
						con.close();
					}catch(Exception e){
						System.out.println("链接关闭失败！");
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 功能：删除表
	 * @param tableName 表名
	 * @return 返回true表示删除成功
	 */
	public static boolean dropTable(String tableName) {
		Connection con = HbaseUtil.getCon();
		boolean status=false;
		if(con == null) {
			System.out.println("连接获取失败！");
		}else {
			try {
				HBaseAdmin hbaseadmin = (HBaseAdmin)con.getAdmin();
				if(hbaseadmin.tableExists(tableName)) {
					if(hbaseadmin.isTableEnabled(tableName))
					{
						hbaseadmin.disableTable(tableName);
					}
					hbaseadmin.deleteTable(tableName);
					System.out.println("删表成功");
					status=true;
				}else {
					System.out.println("表不存在！");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("删表失败！");
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return status;
	}
	public static ArrayList<TableBean> querybykey(String key){
		ArrayList<TableBean> result = new ArrayList<TableBean>();
		ArrayList<TableBean> tablelist = tabList();
		for (TableBean table : tablelist) {
			String tablename = table.getTableName();
			boolean status = StringUtil.strContain(tablename, key);
			if(status == true) {
				result.add(table);
			}
		}
		return result;
	}
	//获取一张表的所有列镞
	public static ArrayList<FamilyBean> getAllFamilys(String tablename) throws IOException{
		ArrayList<FamilyBean> result = new ArrayList<FamilyBean>();
		
		Configuration conf = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor Tdescriptor = admin.getTableDescriptor(Bytes.toBytes(tablename));
		HColumnDescriptor[] Cdescriptor= Tdescriptor.getColumnFamilies();		
		for(int i = 0;i < Cdescriptor.length;i++) {
			FamilyBean family = new FamilyBean();
			family.setFamilyName(new String(Cdescriptor[i].getName()));
			family.setMaxVersion(Cdescriptor[i].getMaxVersions());
			family.setCompression(new String(Cdescriptor[i].getCompression().toString()));
			family.setTimeToLive(Cdescriptor[i].getTimeToLive());
			System.out.println(new String(Cdescriptor[i].getName()));
			result.add(family);
		}
		return result;
	}
	//删除指定列镞
	public static void RemoveFamily(String tablename,String familyname) throws IOException{
		Configuration conf = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(conf);
		admin.deleteColumn(tablename, familyname);
	}
	//修改表名
	public static void ChangeTableName(String Old_tablename,String New_tablename)throws IOException, InterruptedException{
		Configuration conf = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(conf);
		String snapshotName = "snapshot";
		admin.disableTable(Old_tablename);
		admin.snapshot(snapshotName, Old_tablename);
		admin.cloneSnapshot(snapshotName, New_tablename);
		admin.deleteSnapshot(snapshotName);
		admin.deleteTable(Old_tablename);
		
	}
}
