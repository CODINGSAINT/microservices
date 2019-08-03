package com.codingsaint.microservices.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreFilter  extends ZuulFilter{
	
	Logger logger= LoggerFactory.getLogger(PreFilter.class);

	public boolean shouldFilter() {
		return true;
	}

	public Object run() throws ZuulException {
	RequestContext context= RequestContext.getCurrentContext();
	HttpServletRequest request= context.getRequest();
	logger.info("PRE {} |{}" ,request.getRequestURI() ,request.getMethod());
		return null;
	}

	@Override
	public String filterType() {
			return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
