package com.czwx.imall.api.annotation;

import java.lang.annotation.*;

/**
 * Created by xingyongshan on 15/8/4.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String desc() default "";
}
