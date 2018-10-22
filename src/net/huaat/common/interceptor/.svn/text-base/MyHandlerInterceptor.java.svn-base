package net.huaat.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-9-26 下午12:45:21
 * @version V1.0
 */
public class MyHandlerInterceptor extends HandlerInterceptorAdapter {
	protected final Log logger = LogFactory.getLog(getClass());

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();

		logger.info(request.getSession().getAttribute("userSessionName") + "  "
				+ uri);
		logger.info(request.getRemoteAddr());
		logger.debug(request.getRemoteAddr());
		if (true) {
			return true;
		}
		if (uri.endsWith("/login/in")) {
			return true;
		}
		if ((request.getSession() != null)
				&& (request.getSession().getAttribute("userSessionId") != null)) {
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/login.jsp");

		return false;
	}

}
