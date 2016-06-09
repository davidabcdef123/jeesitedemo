package com.superman.common.persistence.annotation;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/2.</p>
 *标识MyBatis的DAO,方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 * @author Super.Sun
 * @version 1.0
 */
public @interface MyBatisDao {

    String value() default "";
}
