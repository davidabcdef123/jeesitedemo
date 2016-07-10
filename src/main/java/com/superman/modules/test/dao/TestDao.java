package com.superman.modules.test.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.test.entity.Test;

/**
 *  测试DAO接口
 * Created by Super on 2016/7/10.
 */
@MyBatisDao
public interface TestDao  extends CrudDao<Test> {
}
