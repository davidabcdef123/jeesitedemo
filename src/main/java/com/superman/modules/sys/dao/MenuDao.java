package com.superman.modules.sys.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/2.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface MenuDao extends CrudDao {

    public List<Menu> findByParentIdsLike(Menu menu);

    public List<Menu> findByUserId(Menu menu);

    public int updateParentIds(Menu menu);

    public int updateSort(Menu menu);

}
