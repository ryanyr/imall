package com.czwx.imall.manage.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Description:service 的父类，封装了slf4j
 * @author  Robin
 * @version V1.0 
 * @createDateTime：2014-10-30  11:35:26 
 * 
 **/
public class GenericService { 
	protected Logger	logger	= LoggerFactory.getLogger(this.getClass());
}
