package com.superman.modules.oa.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.oa.entity.OaNotifyRecord;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 * 通知通告记录DAO接口
 * @author Super.Sun
 * @version 1.0
 */

@MyBatisDao
public interface OaNotifyRecordDao extends CrudDao<OaNotifyRecord> {

    /**
     * 插入通知记录
     * @param oaNotifyRecordList
     * @return
     */
    public int insertAll(List<OaNotifyRecord> oaNotifyRecordList);

    /**
     * 根据通知ID删除通知记录
     * @param oaNotifyId 通知ID
     * @return
     */
    public int deleteByOaNotifyId(String oaNotifyId);
}
