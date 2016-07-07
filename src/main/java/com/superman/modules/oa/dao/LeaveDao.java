package com.superman.modules.oa.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.oa.entity.Leave;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 * 请假DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface LeaveDao  extends CrudDao<Leave> {

    /**
     * 更新流程实例ID
     * @param leave
     * @return
     */
    public int updateProcessInstanceId(Leave leave);

    /**
     * 更新实际开始结束时间
     * @param leave
     * @return
     */
    public int updateRealityTime(Leave leave);
}
