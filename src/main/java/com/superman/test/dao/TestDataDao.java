package com.superman.test.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.test.entity.TestData;

/**
 *  单表生成DAO接口
 * Created by Super on 2016/7/10.
 */
@MyBatisDao
public interface TestDataDao  extends CrudDao<TestData> {
}
