package net.huaat.system.web.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.huaat.common.service.BaseGridService;
import net.huaat.common.util.StringUtil;
import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.common.web.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.huaat.system.pojo.TUser;
import net.huaat.system.service.sys.UserService;

/**  
 * @Description: 
 * @author  generator
 * @date 2012-11-07 05:09:53
 * @version V1.0  
 */
@Controller
@RequestMapping("/user/user")
public class UserController extends BaseController{

	@Resource
	BaseGridService baseGridService;
	
	@Resource
	UserService userService;
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String init() {
		return "system/user/userList";
	}
	/**
	 * 根据条件分页查询
	 * @param queryParams 查询参数,JSON格式
	 * @return
	 */
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
		
		List<TUser> list = baseGridService.getList("TUser", pagination,querList);
	
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", pagination.getAllCount());
		map.put("rows", list);
		return map ; 
	}
	
	/**
	 * 保存或者新增
	 * @param pojo
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map save(HttpServletRequest req) {
		String inserted = req.getParameter("inserted");
		String updated = req.getParameter("updated");	
		Map<String,String> msg = new HashMap<String, String>();
		if(inserted != null){
		    List<TUser> listInserted = JSONArray.toList(JSONArray.fromObject(inserted),new TUser(),new JsonConfig());
		    for(int i=0;listInserted!=null&&i<listInserted.size();i++){
		    	listInserted.get(i).setId(UUID.randomUUID().toString());
				add(listInserted.get(i));
			}
		}
		if(updated != null){
			List<TUser> listUpdated =JSONArray.toList(JSONArray.fromObject(updated),new TUser(),new JsonConfig());
			for(int i=0;listUpdated!=null&&i<listUpdated.size();i++){
				update(listUpdated.get(i));
			}
		}
		msg.put("success", "保存成功");
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
		if (deleteEntityById(TUser.class, id)) {
			msg.put("success", "删除成功");
		}else {
			msg.put("error", "error");
		}
		return  msg; 
	}
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modify() {
		return "system/user/modify";
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifySave(TUser tUser,HttpSession session) {
		TUser oldTUser = (TUser) getEntityById(TUser.class, session.getAttribute("userId").toString());
		oldTUser.setPassword(tUser.getPassword());
		update(oldTUser);
		map.put("success", "修改成功");
		return map;
	}
	
}

