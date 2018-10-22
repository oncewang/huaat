package net.huaat.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-10-8 下午3:54:38
 * @version V1.0
 */
public class StringUtil {
	/**
	 * 去掉字符串中空格
	 * 
	 * @param sourceSrt
	 * @return
	 */
	public static String removeAllSpaces(String sourceSrt) {
		StringBuffer targetStr = new StringBuffer();
		char c = ' ';
		for (int i = 0; i < sourceSrt.length(); i++) {
			if (sourceSrt.charAt(i) != c) {
				targetStr.append(sourceSrt.charAt(i));
			}
		}
		return targetStr.toString();
	}

	/**
	 * 为""或null 返回true
	 * @param sourceStr
	 * @return
	 */
	public static boolean isEmpty(Object sourceStr) {
		if (sourceStr == null) {
			return true;
		}
		return sourceStr.toString().trim().equals("");
	}
	/**
	 * 将目标字符串首字母转换成大写
	 * @param sourceStr
	 * @return
	 */
	public static String upperCaseFirstCharacter(String sourceStr){
		
		String target=String.valueOf(sourceStr.charAt(0)).toUpperCase();
		if(sourceStr.length()>1){
			target+=sourceStr.substring(1);
		}
		return target;
	}
	/**
	 * 将目标字符串首字母转换成小写
	 * @param sourceStr
	 * @return
	 */
	public static String lowerCaseFirstCharacter(String sourceStr){
		
		String target=String.valueOf(sourceStr.charAt(0)).toLowerCase();
		if(sourceStr.length()>1){
			target+=sourceStr.substring(1);
		}
		return target;
	}
	/**
	 * 去掉字符串最后一位
	 */
	public static StringBuilder removeLastStr(StringBuilder sourceStr) {
		
		return  new StringBuilder(sourceStr.substring(0, sourceStr.length()-1));
	}
	/**
	 * 判断一个字符串是否在字符串数组中
	 * @param strs
	 * @param sourceStr
	 * @return
	 */
	public static boolean isInArrays(String[] strs,String sourceStr) {
		for(String str:strs){
			if (sourceStr.equals(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 划分千分位
	 * @param strs
	 * @param sourceStr
	 * @return
	 */
	public static String getThousandSeparator(Object o){
		String str="";
		if(o!=null){
			str=o.toString();
			String suffix="";
			if(str.contains(".")){
				suffix=str.substring(str.indexOf("."));
				str=str.substring(0, str.indexOf("."));
			}
			for(int i=3;i<str.length();i+=4){
				str=str.substring(0,str.length()-i)+","+str.substring(str.length()-i);
			}
			str=str+suffix;
		}
		
		return  str;
	}
	
	//获取随机颜色码
	public static String getColor(){
			 String r,g,b;    
	         Random random = new Random();    
	         r = Integer.toHexString(random.nextInt(256)).toUpperCase();    
	         g = Integer.toHexString(random.nextInt(256)).toUpperCase();    
	         b = Integer.toHexString(random.nextInt(256)).toUpperCase();    
	             
	         r = r.length()==1 ? "0" + r : r ;    
	         g = g.length()==1 ? "0" + g : g ;    
	         b = b.length()==1 ? "0" + b : b ;    
			
			return r+g+b;
	}
	
	public static String getDefaultStrIsNull(Object sourceStr){
		return sourceStr==null?"-":sourceStr.toString();
	}
	
		
	
	public  static String getDateSql(String queryType){
		String dim="";
		if(queryType.equals("day")){
			dim="to_char(dateid,'yyyyMMdd')||' '|| to_char(dateid,'day') ";
		}else if(queryType.equals("week")){
			dim=" to_char(dateid,'yyyy')||'年'||to_char(dateid,'fmww')|| '周' ";
		}else if(queryType.equals("month")){
			dim="to_char(dateid,'yyyy-MM')";
		}else if(queryType.equals("quarter")){
			dim=" to_char(dateid,'yyyy')||'年'||to_char(dateid,'Q')|| '季度' ";
		}else if(queryType.equals("year")){
			dim="to_char(dateid,'yyyy')";
		}
		return dim;
	}

}
