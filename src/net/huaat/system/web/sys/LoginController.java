package net.huaat.system.web.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.huaat.common.web.BaseController;
import net.huaat.system.pojo.TMenu;
import net.huaat.system.pojo.TRole;
import net.huaat.system.pojo.TUser;
import net.huaat.system.service.sys.MenuService;
import net.huaat.system.service.sys.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * @Description: 用户登录安全、模块权限验证
 * @author zhiqiang yang
 * @date 2012-9-26 上午9:44:35
 * @version V1.0
 */
@Controller("systemLogin")
@RequestMapping("/system/login")  //前台传过来的数据
public class LoginController  extends BaseController{

	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;
	

	/**
	 * Get method login
	 */
	@RequestMapping(value = { "/in" }, method = RequestMethod.GET)
	public String loginInByGet(HttpSession session,Model model) {
		TUser tUser=(TUser) session.getAttribute("tUser");
			if(tUser==null){
				return "system/login";
			}else{
				model.addAttribute("menu",getTabsByRole(tUser,session)); 
				return "system/index";
			}
	}
	@RequestMapping(value = { "/in" }, method = RequestMethod.POST)  //post方法，从控制层到后台调用方法使用
	public String loginIn(HttpSession session,TUser tUser,String kaptcha,Model model) {
		
		//验证码
	//	String kaptchaExpected = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	
		
//		if(kaptcha!=null&&kaptcha.equals(kaptchaExpected)){
			if(tUser==null||tUser.getName()==null||tUser.getPassword()==null){
				model.addAttribute("msg", "用户名和密码不能为空");
				return "system/login";
			}else{
				if (userService.loginValidate(tUser.getName(), tUser.getPassword())!=1) { 
						model.addAttribute("msg", "密码错误或用户不存在");
						return "system/login";
					}else {
						tUser = userService.getUserByUserName(tUser.getName());
					}
			}
//		}
		//去掉对界面验证码的校验
//		else{
//			model.addAttribute("msg", "验证码错误");
//			return "system/login";
//		}
		//控制层
		TRole role=(TRole) getEntityById(TRole.class,tUser.getRoleIds()); //
		session.setAttribute("tUser", tUser);
		session.setAttribute("userName", tUser.getName());
		session.setAttribute("userId", tUser.getId());
		session.setAttribute("role", role);
		model.addAttribute("menu",getTabsByRole(tUser,session)); 
	//	return "system/index2"; //默认返回的为jsp后缀
		return "system/test1/index2";
		
	}

	
	@RequestMapping(value = { "/out" }, method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("tUser");
		session.removeAttribute("userSessionId");
		session.removeAttribute("userSessionName");
		return "redirect:/login.jsp";
	}

	/**
	 * 验证用户登录信息
	 * @param tUser
	 * @return
	 * @throws Exception
	 */
	private String validateLoginInfo(TUser tUser,HttpSession session){
		/** 得到用户有权限访问的功能 */
		List<String> list=menuService.getMenuByRoleId(tUser.getRoleIds());
		String menu=menuService.getUserMenuHTML(list,session.getServletContext().getContextPath());
		return menu; 
	}
	
	/**
	 * 根据角色获取tabs及菜单
	 * @param tUser
	 * @return
	 * @throws Exception
	 */
	private String getTabsByRole(TUser tUser,HttpSession session){
		/** 得到用户有权限访问的功能 */
		List<String> list=menuService.getMenuByRoleId(tUser.getRoleIds());
		String menu=menuService.getUserTabsHTML(list,session.getServletContext().getContextPath());
		return menu; 
	}
	

	
	@RequestMapping(value = "/sysIndex", method = RequestMethod.GET)
	public String sysIndex(Model model, HttpSession session) {
		//TUser user = (TUser) session.getAttribute(SessionConstant.USER);
		model.addAttribute("menu", userService.getUserMenuHTML(22));
		return "system/index";
	}
}
