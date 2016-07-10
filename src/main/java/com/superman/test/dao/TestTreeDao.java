package com.superman.test.dao;

import com.superman.common.persistence.TreeDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * Created by Super on 2016/7/10.
 */
@MyBatisDao
public interface TestTreeDao  extends TreeDao<TestTree> {
}
