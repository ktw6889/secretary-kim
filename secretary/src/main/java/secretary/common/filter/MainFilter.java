package secretary.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MainFilter implements Filter {

	/**
	 * 1. 세션에 사용자 정보가 있는지 체크 2. 세션에 사용자 정보가 없으면 id/pw 화면으로
	 */
	// DEBUG, INFO, WARN, ERROR, FATAL
	public Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// UTF-8로 Response 인코딩
		response.setCharacterEncoding("UTF-8");
		String requestUri = ((HttpServletRequest) request).getRequestURI();
		String contextPath = ((HttpServletRequest) request).getContextPath();
		// logger.info("contextPath : "+contextPath);
		if (!StringUtils.isEmpty(requestUri) && requestUri.indexOf(".do") > -1)
			logger.info("requestUri : " + requestUri);

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// logger.info("===== init filter =====");
	}

	@Override
	public void destroy() {
		// logger.info("===== destroy filter =====");
	}

}
