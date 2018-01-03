package com.czwx.imall.manage.common.exception;
/** 
 * Description
 * @author  Robin
 * @version V1.0 
 * @createDateTime：2014-10-30  11:35:26 
 **/
public class ServiceException extends Exception{
	private static final long serialVersionUID = -7523238768040633894L;

	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
