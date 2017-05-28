package net.huaat.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**  
 * @Description: 
 * @author zhiqiang yang 
 * @date 2012-10-19 下午1:59:10
 * @version V1.0  
 */
public class OuterModuleHandlerIterceptor extends HandlerInterceptorAdapter{
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		log.info(uri);
		if (uri.contains("exterior_intf")) {
			uri = uri.replace("/exterior_intf/","/");
			response.sendRedirect(uri); 
			return false;
		}
		log.info(uri);
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "127.0.0.1:8080/exterior_intf/portal/system/login/sysIndex";
		if (url.contains("exterior_intf")) { 
			 System.out.println(url.replace("/exterior_intf/","/"));
		}

	}

}

