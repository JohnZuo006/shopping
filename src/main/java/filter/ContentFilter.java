package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ContentFilter
 */
@WebFilter("/ContentFilter")
public class ContentFilter implements Filter {

    public ContentFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("------------contentFilter");
        String origin = req.getHeader("Origin");
        //if (!org.springframework.util.StringUtils.isEmpty(origin)) {
            //带cookie的时候，origin必须是全匹配，不能使用*
            res.addHeader("Access-Control-Allow-Origin", origin);            
        //}
        res.addHeader("Access-Control-Allow-Methods", "*");
        String headers = req.getHeader("Access-Control-Request-Headers");
        // 支持所有自定义头
        //if (!org.springframework.util.StringUtils.isEmpty(headers)) {
            res.addHeader("Access-Control-Allow-Headers", headers);            
        //}
        res.addHeader("Access-Control-Max-Age", "3600");
        // enable cookie
        res.addHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
