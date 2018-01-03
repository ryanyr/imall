package com.czwx.imall.manage.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyException extends SimpleMappingExceptionResolver{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public MyException() {
		super();
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("error:"+ExceptionUtils.getFullStackTrace(ex));
		ex.printStackTrace();
		return super.doResolveException(request, response, handler, ex);
	}
}
