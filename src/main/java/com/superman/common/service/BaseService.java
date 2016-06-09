package com.superman.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/2.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */

@Transactional(readOnly = true)
public abstract class BaseService {

    /**
     * 日志对象
     * getClass  v extend BaseService;
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
