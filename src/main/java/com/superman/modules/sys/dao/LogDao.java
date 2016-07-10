package com.superman.modules.sys.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.sys.entity.Dict;
import com.superman.modules.sys.entity.Log;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/9.</p>
 * 日志DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface LogDao extends  CrudDao<Log> {
}
