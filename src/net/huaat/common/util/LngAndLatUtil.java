/**
 * 
 */
package net.huaat.common.util;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import net.sf.json.JSONObject;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-17 下午1:32:01
 * @version V1.0  
 */
public class LngAndLatUtil {
	
	public static Map<String,Double> getLngAndLat(String address){
		Map<String,Double> map=new HashMap<String, Double>();
		 String url = "http://api.map.baidu.com/geocoder/v2/?address="+address.trim().replace(" ", "")+"&output=json&ak=735188ce66f222510cb74c97e63ab8a8";
	        String json = loadJSON(url);
	        System.out.println("++json+++:"+json);
	        try{
		        JSONObject obj = JSONObject.fromObject(json);
		        if(obj.get("status").toString().equals("0")){
		        	double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
		        	double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
		        	map.put("lng", lng);
		        	map.put("lat", lat);
		        	//System.out.println("经度："+lng+"---纬度："+lat);
		        }else{
		        	//System.out.println("未找到相匹配的经纬度！");
		        }
	        }catch(Exception e){
	        	   System.out.println("经纬度获取失败！");
	        	  // e.printStackTrace();
	        }
		return map;
	}
	
	 public static String loadJSON (String url) {
	        StringBuilder json = new StringBuilder();
	        try {
	            URL oracle = new URL(url);
	            URLConnection yc = oracle.openConnection();
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                                        yc.getInputStream()));
	            String inputLine = null;
	            while ( (inputLine = in.readLine()) != null) {
	                json.append(inputLine);
	            }
	            in.close();
	        } catch (MalformedURLException e) {
	        } catch (IOException e) {
	        }
	        return json.toString();
	    }

}
