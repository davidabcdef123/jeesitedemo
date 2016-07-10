package com.superman.modules.sys.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.sys.entity.Dict;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/16.</p>
 * 字典DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface DictDao  extends CrudDao<Dict> {

    public List<String> findTypeList(Dict dict);
}
