package com.yw.util;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;


public class HbaseUtil {
	static Configuration conf =null;
	private static Connection con;
	public static void init(){  
        conf = HBaseConfiguration.create();  
        conf.set("hbase.zookeeper.quorum", "localhost"); 
    }
//	static {
//		conf = HBaseConfiguration.create();
//		conf.set("hbase.zookeeper.quorum", "localhost");
//	}
	public static Connection getCon()
	{
		init();
		try {
			con=ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
