package com.superman.modules.oa.dao;

import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.oa.entity.TestAudit;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 * 审批DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface TestAuditDao {

    public TestAudit getByProcInsId(String procInsId);

    public int updateInsId(TestAudit testAudit);

    public int updateHrText(TestAudit testAudit);

    public int updateLeadText(TestAudit testAudit);

    public int updateMainLeadText(TestAudit testAudit);
}
