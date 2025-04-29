package com.online.store.online.store.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class ZuulCorsFilter implements Filter {

    @Value("${cors.url}")
    private String corsUrl;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        log.debug("*** ZuulCorsFilter.doFilter ***");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        log.debug("CORS Filter: Allowed origins: {}", corsUrl);

        List<String> headerNameList = Collections.list(request.getHeaderNames());

        if (headerNameList.contains("origin")) {
            String originHeader = request.getHeader("origin");
            if (corsUrl.contains(originHeader)) {
                log.debug("CORS Filter: Allowed origins contains origin header!!");
                response.setHeader("Access-Control-Allow-Origin", originHeader);
            }
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Client-ID");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "Authorization, access_token, refresh_token");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }


	public void destroy() {
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}