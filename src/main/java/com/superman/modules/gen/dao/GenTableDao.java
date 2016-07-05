package com.superman.modules.gen.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.gen.entity.GenTable;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/5.</p>
 * 业务表DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface GenTableDao  extends CrudDao<GenTable> {
}
