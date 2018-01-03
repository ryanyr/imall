package com.czwx.imall.manage.common.mybatisextend;

/** 
 * Description
 * @author  Robin
 * @version V1.0 
 * @createDateTime：2014-10-30  11:35:26 
 * @Company: 
 * @Copyright: Copyright (c) 2014
 **/
public abstract class Dialect {
	 public static enum Type{MYSQL,ORACLE}
	 public abstract String getLimitString(String sql, int skipResults, int maxResults);  

}
 