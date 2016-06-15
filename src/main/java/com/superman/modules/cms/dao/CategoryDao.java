package com.superman.modules.cms.dao;

import com.superman.common.persistence.TreeDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.cms.entity.Category;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * 栏目DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface CategoryDao  extends TreeDao<Category> {

}
