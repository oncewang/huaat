/**
 * 
 */
package net.huaat.estate.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.huaat.common.service.BaseGridService;
import net.huaat.common.service.BaseService;
import net.huaat.common.util.DateUtil;
import net.huaat.common.util.LngAndLatUtil;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.web.BaseController;
import net.huaat.estate.pojo.EstateTemp;
import net.huaat.estate.service.EstateService;
import net.huaat.system.pojo.TMenu;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-4 上午10:15:59
 * @version V1.0  
 */
@Controller
@RequestMapping("/estate")
public class EstateController  extends BaseController{
	@Resource
	BaseService baseService;
	
	@Resource
	BaseGridService baseGridService;
	@Resource
	EstateService estateService;
	private ServletContext sc;
	public String batchId=UUID.randomUUID().toString();
	public String bat_batchId=UUID.randomUUID().toString();
	private String savePath;
	
	@RequestMapping(value="/home/init",method=RequestMethod.GET)
	public String init(HttpServletRequest request) {
		
		return "estate/home";
	}
	
	@RequestMapping(value="/home/getData",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getMapData(HttpSession session,HttpServletRequest request,String province,String city){
		Map<String,Object> msg = new HashMap<String,Object>();
		String sql=" ";
		
		if(province!=null){
			if(province.equals("全国")){
				sql=" SELECT province,AVG(price) FROM estate_price  where province is not null AND province<>'' GROUP BY province  ";
				msg.put("address", "中国");
				msg.put("i",5);
			}else {
				if(city!=null){
					if(city.equals("全部")){
						sql=" SELECT city,AVG(price) FROM estate_price   WHERE province='"+province+"'  GROUP BY city ";
						msg.put("address", province);
						msg.put("i",8);
					}else {
						sql=" SELECT AREA,AVG(price) FROM estate_price   WHERE province='"+province+"' and city='"+city+"'  GROUP BY AREA ";
						msg.put("address", city);
						msg.put("i",10);
					}
				}
				
			}
		}
		
		
		List<Object[]> list=baseGridService.getListBySql(sql);
		String[] pros={"北京","天津","上海","重庆","新疆","广西","内蒙古","西藏","宁夏","香港","澳门"};
		Map map=new HashMap();
		map.put("北京", "北京市");
		map.put("天津", "天津市");
		map.put("上海", "上海市");
		map.put("重庆", "重庆市");
		map.put("新疆", "新疆维吾尔自治区");
		map.put("广西", "广西壮族自治区");
		map.put("内蒙", "内蒙古");
		map.put("西藏", "西藏自治区");
		map.put("宁夏", "宁夏回族自治区");
		map.put("香港", "香港特别行政区");
		map.put("澳门", "澳门特别行政区");
		for(Object[] obj:list){
			if(province.equals("全国")){
				if(map.get(obj[0]+"")!=null){
					obj[0]=map.get(obj[0]+"").toString()+"-"+StringUtil.getColor();
				}else{
					obj[0]=obj[0]+"省-"+StringUtil.getColor();
				}
			}else{
				obj[0]=obj[0]+"-"+StringUtil.getColor();
			}
			
		}
		msg.put("list", list);
		msg.put("success", "新增成功");
		return msg;
	}
	
	@RequestMapping(value="/assess/init",method=RequestMethod.GET)
	public String assessInit(HttpServletRequest request,HttpSession session) {
		request.setAttribute("start", DateUtil.getPreDay());
		request.setAttribute("end", DateUtil.getTomorrow());
		return "estate/assess";
	}
	
	@RequestMapping(value="/batchAssess/init",method=RequestMethod.GET)
	public String batchAssessInit(HttpServletRequest request,HttpSession session) {
		return "estate/batchAssess";
	}
	
	@RequestMapping(value="/assessResult/init",method=RequestMethod.GET)
	public String assessResultInit(HttpServletRequest request,HttpSession session) {
		request.setAttribute("start", DateUtil.getPreDay());
		request.setAttribute("end", DateUtil.getTomorrow());
		
		return "estate/assessResult";
	}
	
	@RequestMapping(value="/earlyWarning/init",method=RequestMethod.GET)
	public String earlyWarningInit(HttpServletRequest request,HttpSession session) {
		request.setAttribute("start", DateUtil.getPre2Day());
		request.setAttribute("end", DateUtil.getTomorrow());
		return "estate/earlyWarning";
	}
	
	@RequestMapping(value="/assess/save",method=RequestMethod.POST)
	@ResponseBody
	public Map save(EstateTemp obj,BindingResult errorResult,HttpSession session,HttpServletRequest request){
		Map<String,String> msg = new HashMap<String, String>();
		obj.setId(UUID.randomUUID().toString());
		obj.setUserId((session.getAttribute("userId")).toString());
		obj.setBatchId(batchId);
		obj.setPinggu(0);
		obj.setDtaDate(new Date());
		Map<String,Double> map=LngAndLatUtil.getLngAndLat(obj.getProvince()+obj.getCity()+obj.getArea()+obj.getAddress());
		if(map!=null){
			obj.setLongitude(map.get("lng"));
			obj.setLatitude(map.get("lat"));
		}
		add(obj);
		msg.put("success", "新增成功");
		return msg;
	}
	@RequestMapping(value="/assess/downLoad",method=RequestMethod.GET)
	public void download(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,String> msg = new HashMap<String, String>();
		 response.setContentType("text/html;charset=UTF-8");   
	        BufferedInputStream in = null;  
	        BufferedOutputStream out = null;  
	        request.setCharacterEncoding("UTF-8");  
	        String rootpath = request.getSession().getServletContext().getRealPath(  
	                "/");  
	        String fileName = "import.xls"; 
	        try {  
	            File f = new File( "D:/upload/download/"+ fileName); 
	            response.setContentType("application/x-excel");  
	            response.setCharacterEncoding("UTF-8");  
	              response.setHeader("Content-Disposition", "attachment; filename="+fileName);  
	            response.setHeader("Content-Length",String.valueOf(f.length()));  
	            in = new BufferedInputStream(new FileInputStream(f));  
	            out = new BufferedOutputStream(response.getOutputStream());  
	            byte[] data = new byte[1024];  
	            int len = 0;  
	            while (-1 != (len=in.read(data, 0, data.length))) {  
	                out.write(data, 0, len);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (in != null) {  
	                in.close();  
	            }  
	            if (out != null) {  
	                out.close();  
	            }  
	        }  
	}
	
	
	@RequestMapping(value="/assess/upLoad",method=RequestMethod.POST)
	@ResponseBody
	public Map upLoad(HttpSession session,HttpServletRequest request){
		Map<String,String> msg = new HashMap<String, String>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			   List items = upload.parseRequest(request);
			   Iterator itr = items.iterator();
			   while (itr.hasNext()) {
				    FileItem item = (FileItem) itr.next();
				    if (item.isFormField()) {
				    	System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
				    } else {
						 if (item.getName() != null && !item.getName().equals("")) {
				
						      File tempFile = new File(item.getName());
						      java.io.File fil = new java.io.File("D:/upload");
							  if(!fil.exists()) {
									fil.mkdir();
							  }
						      File file = new File("D:/upload", tempFile.getName());
						      item.write(file);
						      String filepath = "D:/upload/"+file.getName(); 
						        try {  
						            Workbook workbook = Workbook.getWorkbook(new File(filepath));  
						            Sheet sheet = workbook.getSheet(0);   
						            for (int i = 1, j = sheet.getRows(); i < j; i++) {  
						            	EstateTemp obj=new EstateTemp();
						            	obj.setId(UUID.randomUUID().toString());
						            	obj.setProvince(sheet.getCell(0, i).getContents());
						            	obj.setCity(sheet.getCell(1, i).getContents());
						            	obj.setArea(sheet.getCell(2, i).getContents());
						            	obj.setAddress(sheet.getCell(3, i).getContents());
						            	obj.setBuildingNo(Integer.valueOf(sheet.getCell(4, i).getContents()));
						            	obj.setAllFloor(Integer.valueOf(sheet.getCell(5, i).getContents()));
						            	obj.setRoomFloor(Integer.valueOf(sheet.getCell(6, i).getContents()));
						            	obj.setRoomArea(Double.parseDouble(sheet.getCell(7, i).getContents()));
						            	obj.setUserId((session.getAttribute("userId")).toString());
						            	obj.setPinggu(0);
						            	obj.setBuildDate(sheet.getCell(8, i).getContents());
						            	obj.setHousingProperty(Integer.valueOf(sheet.getCell(9, i).getContents()));
						            	obj.setEstateCard(sheet.getCell(10, i).getContents());
						            	obj.setBatchId(bat_batchId);
						            	obj.setDtaDate(new Date());
						            	Map<String,Double> map=LngAndLatUtil.getLngAndLat(obj.getProvince()+obj.getCity()+obj.getArea()+obj.getAddress());
						        		if(map!=null){
						        			obj.setLongitude(map.get("lng"));
						        			obj.setLatitude(map.get("lat"));
						        		}
						            	
						            	add(obj);
						            }  
						        } catch (BiffException e) {  
						            e.printStackTrace();  
						        } catch (IOException e) {  
						            e.printStackTrace();  
						        }  
						      
						      msg.put("success", "新增成功");
					     }else{
					    	 msg.put("success", "格式错误！");
					     }
				    }
			   }
		}catch(FileUploadException e){
		   e.printStackTrace();
		} catch (Exception e) {
		   e.printStackTrace();
		   msg.put("success", "格式错误！");
		}
		
		
		return msg;
	}
	
	@RequestMapping(value="/assess/templist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getTemplist(HttpServletRequest request,HttpSession session,String batch) {
		Pagination pagination = new Pagination();
		pagination.setPageNumber(Integer.valueOf(request.getParameter("page")));
		pagination.setPageSize(Integer.valueOf(request.getParameter("rows")));
		
		List<QueryParam> querList =new ArrayList<QueryParam>();
		QueryParam que=new QueryParam();
		que.setId("batchId");
		que.setRelation("1");
		que.setType("1");
		
		if(batch.equals("not_pinggu")){
			que.setValue(batchId);
			String up="UPDATE estate_temp SET batch_id='"+batchId+"' WHERE user_id='"+(session.getAttribute("userId")).toString()+"' AND pinggu=0";
			baseService.updateBySql(up);
			
		}else {
			
			
			if(batch.equals("batchId")){
				que.setValue(batchId);
				
			}else if(batch.equals("bat_batchId")){
				que.setValue(bat_batchId);
			}
		}
		querList.add(que);
		
		System.out.println("batchid+++++++++++++++:"+que.getValue());
		
		List<EstateTemp> list = baseGridService.getList("EstateTemp", pagination,querList);
	
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", pagination.getAllCount());
		map.put("rows", list);
		return map ; 
	
	}
	
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/assess/delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Map delete(@PathVariable("id") String id ) {
		Map<String,String> msg = new HashMap<String, String>();
		if (deleteEntityById(EstateTemp.class, id)) {
			msg.put("success", "删除成功");
		}else {
			msg.put("error", "删除失败");
		}
		return  msg; 
	}	
		
	
	@RequestMapping(value="/assess/pinggu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> assessDetail(HttpServletRequest request,HttpSession session,String batch) {
		Map<String,Object> map = new HashMap<String, Object>();
		String bat="";
		if(batch.equals("batchId")){
			bat=batchId;
			
		}else if(batch.equals("bat_batchId")){
			bat=bat_batchId;
		}
		System.out.println("++++++pinggu batchid+++++++:"+bat);
		int room_price=estateService.callAssessPinggu(bat);
		
		String up="UPDATE estate_temp SET pinggu=1 WHERE batch_id='"+bat+"'";
		baseService.updateBySql(up);
		
		batchId=UUID.randomUUID().toString();
		bat_batchId=UUID.randomUUID().toString();
		
		map.put("success", true);
		
		return map;	
	}
	
	@RequestMapping(value="/assessResult/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> assessResultquery(HttpServletRequest request,HttpSession session,String start_date,String end_date) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		
		Pagination pagination = new Pagination();
		pagination.setPageNumber(Integer.valueOf(request.getParameter("page")));
		pagination.setPageSize(Integer.valueOf(request.getParameter("rows")));
		
		String sql="SELECT u_dta_date,u_province,u_city,u_area,u_address,u_buildIng_no,u_all_floor,u_room_floor,u_room_area,u_build_date,u_housing_property,p_price,warning,id,u_longitude,u_latitude,epd_id,estate_card FROM estate_price_detail  WHERE user_id='"+session.getAttribute("userId")+"' and  u_dta_date BETWEEN '"+start_date+"'  and '"+end_date+"'  and is_auto='0'  ORDER BY u_dta_date DESC";
		
		List<Object[]>  list=baseGridService.getListByPagAndSql(pagination, sql);
		DecimalFormat df = new DecimalFormat( "0.0");
		JSONArray array = new JSONArray();
		for(Object[] obj:list){
			JSONObject row = new JSONObject();
			row.put("data0", StringUtil.getDefaultStrIsNull(obj[0]));
			row.put("data1", StringUtil.getDefaultStrIsNull(obj[1]));
			row.put("data2", StringUtil.getDefaultStrIsNull(obj[2]));
			row.put("data3", StringUtil.getDefaultStrIsNull(obj[3]));
			row.put("data4", StringUtil.getDefaultStrIsNull(obj[4]));
			row.put("data5", StringUtil.getDefaultStrIsNull(obj[5]));
			row.put("data6", StringUtil.getDefaultStrIsNull(obj[6]));
			row.put("data7", StringUtil.getDefaultStrIsNull(obj[7]));
			row.put("data8", StringUtil.getDefaultStrIsNull(obj[8]));
			row.put("data9", StringUtil.getDefaultStrIsNull(obj[9]));
			row.put("data10", StringUtil.getDefaultStrIsNull(obj[10]));
			row.put("data11", StringUtil.getDefaultStrIsNull(obj[11]));
			row.put("data12", StringUtil.getDefaultStrIsNull(obj[12]));
			row.put("data13", StringUtil.getDefaultStrIsNull(obj[13]));
			row.put("data14", StringUtil.getDefaultStrIsNull(obj[14]));
			row.put("data15", StringUtil.getDefaultStrIsNull(obj[15]));
			row.put("data16", StringUtil.getDefaultStrIsNull(obj[16]));
			row.put("data17", StringUtil.getDefaultStrIsNull(obj[17]));
			
			row.put("data04", row);
			row.put("data012", row);
			
			if(obj[11]==null){
				estateService.callPricenear(obj[13].toString());
				String nearSql="SELECT CONCAT(province,city,AREA,address1),p_price FROM estate_price_near  WHERE id='"+obj[13]+"' ";
				List<Object[]>  nearList=baseGridService.getListBySql(nearSql);
				JSONArray proRow = new JSONArray();
				for(Object[] nObj:nearList){
					JSONObject nRow = new JSONObject();
					nRow.put("address", nObj[0]);
					
					double pri=Double.parseDouble(nObj[1].toString())/10000;
					nRow.put("price", df.format(pri)+"万元");
					proRow.add(nRow);
				}
				System.out.println("+++++-1++++++");
				String ht="<a href='#' title='' onclick='openNearDig("+proRow+")'>查看附近估价</a> ";
				row.put("data011", ht);
			}else if(obj[11].toString().equals("-1")){
				String nearSql="SELECT CONCAT(province,city,AREA,address1),p_price FROM estate_price_near  WHERE id='"+obj[13]+"' ";
				List<Object[]>  nearList=baseGridService.getListBySql(nearSql);
				JSONArray proRow = new JSONArray();
				for(Object[] nObj:nearList){
					JSONObject nRow = new JSONObject();
					nRow.put("address", nObj[0]);
					double pri=Double.parseDouble(nObj[1].toString())/10000;
					nRow.put("price", df.format(pri)+"万元");
					
					proRow.add(nRow);
				}
				System.out.println("+++++-1++++++");
				String ht="<a href='#' title='' onclick='openNearDig("+proRow+")'>查看附近估价</a> ";
				row.put("data011", ht);
				
			}else{
				double pri=Double.parseDouble(obj[11].toString())/10000;
				row.put("data011", df.format(pri)+"万元");
			}
			
			array.add(row);
		}
		
		
		map.put("total", pagination.getAllCount());
		map.put("rows", array);
		
		return map;	
	}
	
	@RequestMapping(value="/assessResult/getAllList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getAssessResultAllList(HttpServletRequest request,HttpSession session,String start_date,String end_date) {
		Map<String,Object> msg = new HashMap<String, Object>();
		String sql="SELECT u_address,u_longitude,u_latitude FROM estate_price_detail  WHERE user_id='"+session.getAttribute("userId")+"' and  u_dta_date BETWEEN '"+start_date+"'  and '"+end_date+"'  and is_auto='0'  ORDER BY u_dta_date DESC";
		
		List<Object[]> list=baseGridService.getListBySql(sql);
		msg.put("list", list);
		msg.put("success",true);
		
		return msg;	
	}
	
	
	@RequestMapping(value="/earlyWarning/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> earlyWarningquery(HttpServletRequest request,HttpSession session,String start_date,String end_date) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		
		Pagination pagination = new Pagination();
		pagination.setPageNumber(Integer.valueOf(request.getParameter("page")));
		pagination.setPageSize(Integer.valueOf(request.getParameter("rows")));
		
		String sql="SELECT u_dta_date,u_province,u_city,u_area,u_address,u_buildIng_no,u_all_floor,u_room_floor,u_room_area,u_build_date,u_housing_property,p_price,warning,id FROM estate_price_detail  WHERE user_id='"+session.getAttribute("userId")+"' and  u_dta_date BETWEEN '"+start_date+"'  and '"+end_date+"'  and is_auto='0'  ORDER BY u_dta_date DESC";
		
		List<Object[]>  list=baseGridService.getListByPagAndSql(pagination, sql);
		
		JSONArray array = new JSONArray();
		for(Object[] obj:list){
			JSONObject row = new JSONObject();
			row.put("data0", StringUtil.getDefaultStrIsNull(obj[0]));
			row.put("data1", StringUtil.getDefaultStrIsNull(obj[1]));
			row.put("data2", StringUtil.getDefaultStrIsNull(obj[2]));
			row.put("data3", StringUtil.getDefaultStrIsNull(obj[3]));
			row.put("data4", StringUtil.getDefaultStrIsNull(obj[4]));
			row.put("data5", StringUtil.getDefaultStrIsNull(obj[5]));
			row.put("data6", StringUtil.getDefaultStrIsNull(obj[6]));
			row.put("data7", StringUtil.getDefaultStrIsNull(obj[7]));
			row.put("data8", StringUtil.getDefaultStrIsNull(obj[8]));
			row.put("data9", StringUtil.getDefaultStrIsNull(obj[9]));
			row.put("data10", StringUtil.getDefaultStrIsNull(obj[10]));
			row.put("data11", StringUtil.getDefaultStrIsNull(obj[11]));
			row.put("data15", StringUtil.getDefaultStrIsNull(obj[12]));
			row.put("data14", StringUtil.getDefaultStrIsNull(obj[13]));
			row.put("data12", row);
			
			row.put("data13", "<a href='#' onclick='bodong("+row+")'>波动分析</a>");
			array.add(row);
		}
			
		
		
		
		map.put("total", pagination.getAllCount());
		map.put("rows", array);
		
		return map;	
	}
	
	
	
	@RequestMapping(value="/earlyWarning/chart",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> earlyWarningChartquery(HttpServletRequest request,HttpSession session,String id) {
		Map<String,Object> msg = new HashMap<String, Object>();
		
		String sql="SELECT  u_dta_date,p_price FROM estate_price_detail WHERE user_id='"+session.getAttribute("userId")+"' and epd_id='"+id+"'    ORDER BY u_dta_date ";
		System.out.println("++++++sql:"+sql);
		List<Object[]> list=baseGridService.getListBySql(sql);
		
		List<String> date=new ArrayList<String>();
		List<Integer> value=new ArrayList<Integer>();
		if(list.size()>0){
			for(Object[] o :list){
				if(o[0]!=null&&o[1]!=null){
					date.add(o[0].toString());
					value.add(Integer.parseInt(o[1].toString()));
				}
			}
			msg.put("date", date);
			msg.put("value", value);
			msg.put("success",true);
		}else{
			msg.put("success",false);
			
		}
		
		return msg;
	}
	
	
	@RequestMapping(value="/assessResult/export",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> assessResultExport(HttpServletRequest request,HttpServletResponse response,HttpSession session,String start_date,String end_date) {
		Map<String,Object> map = new HashMap<String, Object>();
		String sql="SELECT u_dta_date,u_province,u_city,u_area,u_address,u_buildIng_no,u_all_floor,u_room_floor,u_room_area,u_build_date,u_housing_property,p_price,estate_card FROM estate_price_detail  WHERE user_id='"+session.getAttribute("userId")+"' and  u_dta_date BETWEEN '"+start_date+"'  and '"+end_date+"'  and is_auto='0'  ORDER BY u_dta_date DESC";
		
		List<Object[]> list=baseGridService.getListBySql(sql);
		long start=System.currentTimeMillis();
		try {
			BufferedOutputStream bos = null;  
			OutputStream fos = null;  
			fos = response.getOutputStream();  
			bos = new BufferedOutputStream(fos);  
			
			String nameStr = "";
			nameStr = new String("评估结果.csv".getBytes("GBK"),"ISO8859_1");
			
			response.setHeader("Content-disposition","attachment;filename="+nameStr);  
			response.setContentType("application/octet-stream");
			
			StringBuffer headStr = new StringBuffer();
			
			headStr.append("评估时间").append(",");
			headStr.append("省份").append(",");
			headStr.append("地市").append(",");
			headStr.append("区/县").append(",");
			headStr.append("地址").append(",");
			headStr.append("幢/栋").append(",");
			headStr.append("总楼层").append(",");
			headStr.append("楼层").append(",");
			headStr.append("房屋面积").append(",");
			headStr.append("建造日期").append(",");
			headStr.append("房屋用途").append(",");
			headStr.append("评估价格").append(",");
			headStr.append("房产证号").append(",");
			
			headStr.append("\n"); 
			bos.write(headStr.toString().getBytes("GBK"));  
			
			StringBuffer sb = new StringBuffer();
			
			
				int i=0;
				for(Object[] o:list){
					sb.delete(0, sb.length());
					for(i=0;i<o.length;i++){
						
						if(i==11){
							
							if(o[11]==null||o[11].toString().equals("-1")){
								sb.append("暂无评估价格").append(",");
							}else{
								sb.append(StringUtil.getDefaultStrIsNull(o[i])).append(",");
							}
							
						}else if(i==10){
							if(o[10].toString().equals("1")){
								sb.append("住宅").append(",");
							}else if(o[10].toString().equals("2")){
								sb.append("商用").append(",");
							}else if(o[10].toString().equals("3")){
								sb.append("商住两用").append(",");
							}else{
								sb.append("未知").append(",");
							}
						}else{
							sb.append(StringUtil.getDefaultStrIsNull(o[i])).append(",");
						}
					}
					sb.append("\n");
					bos.write((sb.toString()).getBytes("GBK")); 
				}
			
			bos.flush();  
			fos.close();  
			bos.close();  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("下载时间秒:"+(System.currentTimeMillis()-start)/1000);
		
		return map;
	}	
	
	@RequestMapping(value="/assess/UpdateLngAndLat",method=RequestMethod.GET)
	@ResponseBody
	public synchronized void  updateLngAndLat(HttpServletRequest request,String id) {
		String sql="SELECT CONCAT(province,city,AREA,address1) address FROM estate_price_log"+id+" WHERE longitude IS NULL   OR latitude IS NULL OR longitude=0 OR latitude=0 GROUP BY address";
		System.out.println("++++++sql:"+sql);
		List<Object[]> list=baseGridService.getListBySql(sql);
		for(Object o :list){
			if(o!=null&&o.toString()!=null){
				//System.out.println("+++address+++:"+o.toString().trim());
				Map<String,Double> map=LngAndLatUtil.getLngAndLat(o.toString());
				Double lng=map.get("lng");
				Double lat=map.get("lat");
        		if(lng!=null&&lat!=null){
        			String sq="UPDATE estate_price_log"+id+" SET longitude="+lng+" ,latitude="+lat+" WHERE  CONCAT(province,city,AREA,address1)='"+o.toString()+"'";
        			baseService.updateBySql(sq);
        		}
			}
		}
		
	 }
	
	
}
