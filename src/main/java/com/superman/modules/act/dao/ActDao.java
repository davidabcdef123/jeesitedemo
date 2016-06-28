package com.superman.modules.act.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.common.utils.StringUtils;
import com.superman.modules.act.entity.Act;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/16.</p>
 * 审批DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface ActDao extends CrudDao<Act> {

    public int updateProcInsIdByBusinessId(Act act);

}
