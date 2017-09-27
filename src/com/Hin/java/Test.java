package com.Hin.java;

import java.util.ArrayList;


/**
 * Created by yycq on 17-8-22.
 */
public class Test {
    public static void main(String args[])throws Exception{
        ArrayList test = HBaseOpreation.listTable();
        System.out.println(test.size());
    }
}
