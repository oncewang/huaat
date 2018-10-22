/**
 * 
 */
package net.huaat.areas.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-10 下午4:50:22
 * @version V1.0  
 */
public class Test {
	 public static void main(String[] args) {
		 String[] pros={"北京","天津","上海","重庆","新疆","广西","内蒙古","西藏","宁夏","香港","澳门"};
		 Map map=new HashMap();
			map.put("北京", "北京市");
			map.put("天津", "天津市");
			map.put("上海", "上海市");
			map.put("重庆", "重庆市");
			map.put("新疆", "新疆维吾尔自治区");
			map.put("广西", "广西壮族自治区");
			map.put("内蒙古", "内蒙古自治区");
			map.put("西藏", "西藏自治区");
			map.put("宁夏", "宁夏回族自治区");
			map.put("香港", "香港特别行政区");
			map.put("澳门", "澳门特别行政区");
		// Arrays.asList(pros).contains("北京");
			System.out.println(map.get("北京"));
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
