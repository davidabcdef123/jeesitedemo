package com.superman.common.supcan.annotation.common.properties;

import java.lang.annotation.*;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/13.</p>
 *  硕正Background注解
 * @author Super.Sun
 * @version 1.0
 */

@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupBackground {

    /**
     * 背景颜色
     * @return
     */
    String bgColor() default "";

}
