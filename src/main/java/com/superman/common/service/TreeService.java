package com.superman.common.service;

import com.superman.common.persistence.TreeDao;
import com.superman.common.persistence.TreeEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * Service基类
 * @author Super.Sun
 * @version 1.0
 */
@Transactional(readOnly = true)
public abstract class TreeService<D extends TreeDao<T>, T extends TreeEntity<T>> extends CrudService<D, T> {

}
