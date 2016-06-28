package com.superman.common.annotation;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/28.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * bean中文名注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldName {

    String value();
}
