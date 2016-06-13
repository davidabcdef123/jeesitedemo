package com.superman.common.supcan.annotation.common.properties;

import java.lang.annotation.*;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/13.</p>
 * 硕正Express注解
 * @author Super.Sun
 * @version 1.0
 */

@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupExpress {

    /**
     * 是否自动按列的引用关系优化计算顺序  默认值true
     */
    String isOpt() default "";

    /**
     * 文本
     */
    String text() default "";
}
