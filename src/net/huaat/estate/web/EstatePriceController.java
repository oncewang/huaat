/**
 * 
 */
package net.huaat.estate.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.huaat.common.service.BaseGridService;
import net.huaat.common.service.BaseService;
import net.huaat.common.util.LngAndLatUtil;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.web.BaseController;
import net.huaat.estate.pojo.EstatePrice;
import net.huaat.estate.service.EstateService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-25 上午10:07:43
 * @version V1.0  
 */
@Controller
@RequestMapping("/estatePrice")
public class EstatePriceController   extends BaseController{
	@Resource
	BaseService baseService;
	
	@Resource
	BaseGridService baseGridService;
	@Resource
	EstateService estateService;
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String assessInit(HttpServletRequest request,HttpSession session) {
		
		return "estate/price/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,String queryParams) {
		Pagination pagination = new Pagination();
		pagination.setPageNumber(Integer.valueOf(request.getParameter("page")));
		pagination.setPageSize(Integer.valueOf(request.getParameter("rows")));
		
		List<QueryParam> querList =null;
		
		if (!StringUtil.isEmpty(queryParams)) {
			querList=JSONArray.toList(JSONArray.fromObject(queryParams),new QueryParam(),new JsonConfig()); 
		}
		
		List<EstatePrice> list = baseGridService.getList("EstatePrice", pagination,querList);

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", pagination.getAllCount());
		map.put("rows", list);
		return map ; 
	}
	
	/**
	 * 打开明细
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtil.isEmpty(id)) {
			EstatePrice obj = (EstatePrice)getEntityById(EstatePrice.class,id);
			request.setAttribute("o", obj);
		}
		return "estate/price/detail";
	}
	/**
	 * 保存或者新增
	 * @param pojo
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public Map save(EstatePrice obj,BindingResult errorResult,HttpServletRequest request) {
		Map<String,String> msg = new HashMap<String, String>();
		obj.setDtaDate(new Date());
		Map<String,Double> map=LngAndLatUtil.getLngAndLat(obj.getProvince()+obj.getCity()+obj.getArea()+obj.getAddress());
		if(map!=null){
			obj.setLongitude(map.get("lng"));
			obj.setLatitude(map.get("lat"));
		}
		if (StringUtil.isEmpty(obj.getId())) {
			obj.setId(UUID.randomUUID().toString());
			add(obj);
			msg.put("success", "新增成功");
		}else {
			update(obj);
			msg.put("success", "修改成功");
		}
		return  msg; 
	}
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Map delete(@PathVariable("id") String id ) {
		Map<String,String> msg = new HashMap<String, String>();
		
		if (deleteEntityById(EstatePrice.class, id)) {
			msg.put("success", "删除成功");
		}else {
			msg.put("error", "删除失败");
		}
		return  msg; 
	}	

}
