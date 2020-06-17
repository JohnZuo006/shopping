package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ContextFilter
 */
@WebFilter("/ContextFilter")
public class ContextFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ContextFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse resp = (HttpServletResponse) response;
		// 添加参数，允许任意domain访问
		resp.setHeader("Access-Control-Allow-Origin", "*");
		// 这个allow-headers要配为*，这样才能允许所有的请求头 --- update by zxy in 2018-10-19
		resp.setHeader("Access-Control-Allow-Headers", "*");
		resp.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		resp.setHeader("Access-Control-Max-Age",  "3600");
		chain.doFilter(request, resp);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
