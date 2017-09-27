package com.yw.util;

public class StringUtil {
	/**
	 * 判断是否是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null || "".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断是否不是空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 把一段逗号分割的字符串转换成一个数组
	 * @param str
	 * @return
	 */
	public static String[] convertStrToArray(String str){   
        String[] strArray = null;   
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
        return strArray;
    }
	/**
	 * 功能：判断longStr是否包含shortStr字符串
	 * @param longStr 源字符串
	 * @param shortStr 字串
	 * @return true表示包含
	 */
	public static boolean strContain(String longStr, String shortStr)
	 {
		boolean status = false;
		if(longStr.indexOf(shortStr)!=-1){
		   status = true;
		}
		return status;
	 }
}
