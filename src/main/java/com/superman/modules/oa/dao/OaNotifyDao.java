package com.superman.modules.oa.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.oa.entity.OaNotify;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {

    /**
     * 获取通知数目
     * @param oaNotify
     * @return
     */
    public Long findCount(OaNotify oaNotify);
}
