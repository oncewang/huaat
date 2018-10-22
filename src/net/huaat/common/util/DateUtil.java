package net.huaat.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
public static DateFormat formatMonth = new SimpleDateFormat("yyyyMM");
public static DateFormat formatMonth2 = new SimpleDateFormat("yyyy-MM");

	
	/**
	 * 获取yyyy-MM-dd HH:mm:ss 格式字符串
	 * @return
	 */
	public static String getTimeStampStr(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	/**
	 * 获取yyyy-MM-dd格式字符串
	 * @return
	 */
	public static String getStringFromDate(Date source){
		return format.format(source);
	}
	/**
	 * 将String转换为yyyy-MM-dd格式的Date
	 * @return
	 */
	public static Date getDateFromStr(String source){ 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(source);
		} catch (ParseException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = new Date();
		}
		return date;
	}
	/**
	 * 将String转换为fo格式的Date
	 * @return
	 */
	public static Date getdate(String source,String fo){ 
		SimpleDateFormat format = new SimpleDateFormat(fo); 
		Date date = null;
		try {
			date = format.parse(source);
		} catch (ParseException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = new Date();
		}
		return date;
	}
	
	//获取当前日期
		public static String getPreDay(){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DATE,calendar.get(Calendar.DATE));
			return format.format(calendar.getTime());
		}
	//获取明天的时间
		public static String getTomorrow(){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+1);
				return format.format(calendar.getTime());
		}
	//获取当前日期前一天
	public static String getPre1Day(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-1);
		return format.format(calendar.getTime());
	}
	//获取当前日期前两天
	public static String getPre2Day(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-2);
		return format.format(calendar.getTime());
	}
	//获取当前日期的上一个月日期
	public static String getPre2Month(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
		return format.format(calendar.getTime());
	}
	//获取某个日期的上一个月日期
		public static String getOnedayPre2Month(Date date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
			return format.format(calendar.getTime());
		}
	
	public static String getMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
		return formatMonth.format(calendar.getTime());
	}
	
	public static String getMonth2(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
		return formatMonth2.format(calendar.getTime());
	}
	
	
	//获取当前日期a 天前的日期
		public static String getLstDay(int a){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-a);
			return format.format(calendar.getTime());
		}
		
		//获取某个日期a 天前的日期
		public static String getOnedayLstDay(Date date,int a){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-a);
			return format.format(calendar.getTime());
		}
	
	//获取当前月份a 个月前的月份
	public static String getLstMonth(int a){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-a);
		return formatMonth.format(calendar.getTime());
	}
	public static String getLstMonth2(int a){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-a);
		return formatMonth2.format(calendar.getTime());
	}
	
	//获取某个月份a 个月前的月份
	public static String getOneMonthLstMonth(String month,int a){
		Calendar calendar = Calendar.getInstance();
		Date date=getDateFromStr(month.substring(0, 4)+"-"+month.substring(4)+"-01");
		calendar.setTime(date);
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-a);
		return formatMonth.format(calendar.getTime());
	}
	
	/**
	* 取得当月天数
	* */
	public static int getCurrentMonthLastDay()
	{
	Calendar a = Calendar.getInstance();
	a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	int maxDate = a.get(Calendar.DATE);
	return maxDate;
	}
	 
	/**
	* 得到指定月的天数
	* */
	public static int getMonthLastDay(int year, int month)
	{
	Calendar a = Calendar.getInstance();
	a.set(Calendar.YEAR, year);
	a.set(Calendar.MONTH, month - 1);
	a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	int maxDate = a.get(Calendar.DATE);
	return maxDate;
	}
	/**
	* 计算两个日期之间的天数
	* */
	public static long getQuot(String time1, String time2){
		  long quot = 0;
		  SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		  try {
		   Date date1 = ft.parse( time1 );
		   Date date2 = ft.parse( time2 );
		   quot = date1.getTime() - date2.getTime();
		   quot = quot / 1000 / 60 / 60 / 24;
		  } catch (ParseException e) {
		   e.printStackTrace();
		  }
		  return quot;
	}
	
	
	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		System.out.println(getQuot("2014-05-29","2014-05-23"));
		
	}
	
	
}
