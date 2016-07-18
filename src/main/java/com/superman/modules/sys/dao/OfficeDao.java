package com.superman.modules.sys.dao;

import com.superman.common.persistence.TreeDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.sys.entity.Office;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/2.</p>
 * 机构DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
}
