package cc.ileiwang.bookstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cc.ileiwang.bookstore.domain.Admin;
import cc.ileiwang.bookstore.domain.User;
import cc.ileiwang.bookstore.util.common.BSConstants;

/**
 * @author Lei Wang
 * @email ileiwang@live.com
 * @blog www.ileiwang.cc
 * @version 2018年7月11日 下午3:54:52
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
	private static final String[] IGNORE_URI = { "/loginForm", "/login", "/404.html","register" };

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		String servletPath = request.getServletPath();
		for (String s : IGNORE_URI) {
			if (servletPath.contains(s)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			User user = (User) request.getSession().getAttribute(BSConstants.USERSESSION);
			Admin admin = (Admin) request.getSession().getAttribute(BSConstants.ADMINSESSION);
			if (user == null && admin == null) {
				request.setAttribute("message", "请先登录再访问网站!");
				request.getRequestDispatcher("loginForm").forward(request, response);
				return flag;
			} else {
				flag = true;
			}
		}
		return flag;
	}
}
