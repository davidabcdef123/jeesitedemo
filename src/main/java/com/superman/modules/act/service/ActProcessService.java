package com.superman.modules.act.service;

import com.superman.common.persistence.Page;
import com.superman.common.service.BaseService;
import com.superman.common.utils.StringUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/16.</p>
 * 流程定义相关Controller
 * @author Super.Sun
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ActProcessService  extends BaseService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 流程定义列表
     */
    public Page<Object[]> processList(Page<Object[]> page, String category) {

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
                .latestVersion().orderByProcessDefinitionKey().asc();

        if (StringUtils.isNotEmpty(category)){
            processDefinitionQuery.processDefinitionCategory(category);
        }

        page.setCount(processDefinitionQuery.count());

        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(page.getFirstResult(), page.getMaxResults());
        for (ProcessDefinition processDefinition : processDefinitionList) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            page.getList().add(new Object[]{processDefinition, deployment});
        }

        return page;
    }

    /**
     * 流程定义列表
     */
    public Page<ProcessInstance> runningList(Page<ProcessInstance> page, String procInsId, String procDefKey) {

        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

        if (StringUtils.isNotBlank(procInsId)){
            processInstanceQuery.processInstanceId(procInsId);
        }

        if (StringUtils.isNotBlank(procDefKey)){
            processInstanceQuery.processDefinitionKey(procDefKey);
        }

        page.setCount(processInstanceQuery.count());
        page.setList(processInstanceQuery.listPage(page.getFirstResult(), page.getMaxResults()));
        return page;
    }

}
