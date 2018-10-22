package net.huaat.system.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN_PAGE_URL = "/login.jsp";
	private static final String LOGIN_URL = "/login/in";
	private static final String LOGIN_URL02 = "/login/init";
	private static final String ERROR_URL = "/jsp/error.jsp";
	private static final String EXP_DF = "/downloadFile";
	private static final String EXP_EXCSV = "/expCSVFile";
	private static final String EXP_QS = "/queryByStr";
	private static final String EXP_QIF = "/queryInfoMeta";
	private static final String EXP_TC = "/targetCustomers";
	private static final String EXP_MT = "/mergeTable";
	private static final String EXP_UT = "/unionTable";
	private static final String EXP_EN = "/endNode";
	private static final String EXP_QV = "/queryView";
	private static final String EXP_upT = "/updateTempTable";
	private static final String EXP_DT = "/deleteTempTable";
	protected final Log logger = LogFactory.getLog(getClass());

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		//System.out.println("++++++++++++++++SessionInterceptor+++++++++++++++++++++");
		logger.info(request.getSession().getAttribute("userName") + "" + uri);
		logger.info(request.getRemoteAddr());
		logger.debug(request.getRemoteAddr());
		if (uri.endsWith(EXP_DF) || uri.endsWith(EXP_EXCSV)
				|| uri.endsWith(EXP_QS) || uri.endsWith(EXP_QIF)
				|| uri.endsWith(EXP_TC) || uri.endsWith(EXP_MT)
				|| uri.endsWith(EXP_UT) || uri.endsWith(EXP_EN)
				|| uri.endsWith(EXP_QV)|| uri.endsWith(EXP_upT)|| uri.endsWith(EXP_DT)) {
			return true;
		}
	//System.out.println(uri.endsWith(suffix));
		if(uri.endsWith(LOGIN_URL02)||uri.endsWith(LOGIN_URL)||uri.endsWith(LOGIN_PAGE_URL)){
			return true;
		}
		
		if(request.getSession().getAttribute("userId") != null){
			return true;
		}
		
		 if (request.getHeader("x-requested-with") != null&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))//如果是ajax请求响应头会有，x-requested-with；  
         {  
             response.setHeader("sessionstatus", "timeout");//在响应头设置session状态 
             response.getWriter().print("timeout"); //打印一个返回值，没这一行，在tabs页中无法跳出（导航栏能跳出），具体原因不明
             return false;  
         }  
		
		response.sendRedirect(request.getContextPath() + LOGIN_PAGE_URL);
		return false;

	}
}
