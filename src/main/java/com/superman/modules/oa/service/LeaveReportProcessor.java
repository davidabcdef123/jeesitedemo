package com.superman.modules.oa.service;

import com.superman.modules.oa.dao.LeaveDao;
import com.superman.modules.oa.entity.Leave;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 * 销假后处理器
 * @author Super.Sun
 * @version 1.0
 */
public class LeaveReportProcessor  implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 销假完成后执行，保存实际开始和结束时间
     */
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        Leave leave = new Leave(processInstance.getBusinessKey());
        leave.setRealityStartTime((Date) delegateTask.getVariable("realityStartTime"));
        leave.setRealityEndTime((Date) delegateTask.getVariable("realityEndTime"));
        leave.preUpdate();
        leaveDao.updateRealityTime(leave);
    }
}
