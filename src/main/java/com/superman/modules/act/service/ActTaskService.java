package com.superman.modules.act.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.superman.common.persistence.Page;
import com.superman.common.service.BaseService;
import com.superman.common.utils.StringUtils;
import com.superman.modules.act.dao.ActDao;
import com.superman.modules.act.entity.Act;
import com.superman.modules.sys.entity.User;
import com.superman.modules.sys.utils.UserUtils;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/28.</p>
 * 流程定义相关Service
 * @author Super.Sun
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ActTaskService  extends BaseService {

    @Autowired
    private ActDao actDao;

    @Autowired
    private ProcessEngineFactoryBean processEngine;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;

    /**
     * 获取待办列表
     * @return
     */
    public List<Act> todoList(Act act){
        String userId = UserUtils.getUser().getLoginName();//ObjectUtils.toString(UserUtils.getUser().getId());

        List<Act> result = new ArrayList<Act>();

        // =============== 已经签收的任务  ===============
        TaskQuery todoTaskQuery = taskService.createTaskQuery().taskAssignee(userId).active()
                .includeProcessVariables().orderByTaskCreateTime().desc();

        // 设置查询条件
        if (StringUtils.isNotBlank(act.getProcDefKey())){
            todoTaskQuery.processDefinitionKey(act.getProcDefKey());
        }
        if (act.getBeginDate() != null){
            todoTaskQuery.taskCreatedAfter(act.getBeginDate());
        }
        if (act.getEndDate() != null){
            todoTaskQuery.taskCreatedBefore(act.getEndDate());
        }

        // 查询列表
        List<Task> todoList = todoTaskQuery.list();
        for (Task task : todoList) {
            Act e = new Act();
            e.setTask(task);
            e.setVars(task.getProcessVariables());
//			e.setTaskVars(task.getTaskLocalVariables());
//			System.out.println(task.getId()+"  =  "+task.getProcessVariables() + "  ========== " + task.getTaskLocalVariables());
            e.setProcDef(ProcessDefCache.get(task.getProcessDefinitionId()));
//			e.setProcIns(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult());
//			e.setProcExecUrl(ActUtils.getProcExeUrl(task.getProcessDefinitionId()));
            e.setStatus("todo");
            result.add(e);
        }

        // =============== 等待签收的任务  ===============
        TaskQuery toClaimQuery = taskService.createTaskQuery().taskCandidateUser(userId)
                .includeProcessVariables().active().orderByTaskCreateTime().desc();

        // 设置查询条件
        if (StringUtils.isNotBlank(act.getProcDefKey())){
            toClaimQuery.processDefinitionKey(act.getProcDefKey());
        }
        if (act.getBeginDate() != null){
            toClaimQuery.taskCreatedAfter(act.getBeginDate());
        }
        if (act.getEndDate() != null){
            toClaimQuery.taskCreatedBefore(act.getEndDate());
        }

        // 查询列表
        List<Task> toClaimList = toClaimQuery.list();
        for (Task task : toClaimList) {
            Act e = new Act();
            e.setTask(task);
            e.setVars(task.getProcessVariables());
//			e.setTaskVars(task.getTaskLocalVariables());
//			System.out.println(task.getId()+"  =  "+task.getProcessVariables() + "  ========== " + task.getTaskLocalVariables());
            e.setProcDef(ProcessDefCache.get(task.getProcessDefinitionId()));
//			e.setProcIns(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult());
//			e.setProcExecUrl(ActUtils.getProcExeUrl(task.getProcessDefinitionId()));
            e.setStatus("claim");
            result.add(e);
        }
        return result;
    }

    /**
     * 获取流转历史列表
     * @param procInsId 流程实例
     * @param startAct 开始活动节点名称
     * @param endAct 结束活动节点名称
     */
    public List<Act> histoicFlowList(String procInsId, String startAct, String endAct){
        List<Act> actList = Lists.newArrayList();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInsId)
                .orderByHistoricActivityInstanceStartTime().asc().orderByHistoricActivityInstanceEndTime().asc().list();

        boolean start = false;
        Map<String, Integer> actMap = Maps.newHashMap();

        for (int i=0; i<list.size(); i++){

            HistoricActivityInstance histIns = list.get(i);

            // 过滤开始节点前的节点
            if (StringUtils.isNotBlank(startAct) && startAct.equals(histIns.getActivityId())){
                start = true;
            }
            if (StringUtils.isNotBlank(startAct) && !start){
                continue;
            }

            // 只显示开始节点和结束节点，并且执行人不为空的任务
            if (StringUtils.isNotBlank(histIns.getAssignee())
                    || "startEvent".equals(histIns.getActivityType())
                    || "endEvent".equals(histIns.getActivityType())){

                // 给节点增加一个序号
                Integer actNum = actMap.get(histIns.getActivityId());
                if (actNum == null){
                    actMap.put(histIns.getActivityId(), actMap.size());
                }

                Act e = new Act();
                e.setHistIns(histIns);
                // 获取流程发起人名称
                if ("startEvent".equals(histIns.getActivityType())){
                    List<HistoricProcessInstance> il = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInsId).orderByProcessInstanceStartTime().asc().list();
//					List<HistoricIdentityLink> il = historyService.getHistoricIdentityLinksForProcessInstance(procInsId);
                    if (il.size() > 0){
                        if (StringUtils.isNotBlank(il.get(0).getStartUserId())){
                            User user = UserUtils.getByLoginName(il.get(0).getStartUserId());
                            if (user != null){
                                e.setAssignee(histIns.getAssignee());
                                e.setAssigneeName(user.getName());
                            }
                        }
                    }
                }
                // 获取任务执行人名称
                if (StringUtils.isNotEmpty(histIns.getAssignee())){
                    User user = UserUtils.getByLoginName(histIns.getAssignee());
                    if (user != null){
                        e.setAssignee(histIns.getAssignee());
                        e.setAssigneeName(user.getName());
                    }
                }
                // 获取意见评论内容
                if (StringUtils.isNotBlank(histIns.getTaskId())){
                    List<Comment> commentList = taskService.getTaskComments(histIns.getTaskId());
                    if (commentList.size()>0){
                        e.setComment(commentList.get(0).getFullMessage());
                    }
                }
                actList.add(e);
            }

            // 过滤结束节点后的节点
            if (StringUtils.isNotBlank(endAct) && endAct.equals(histIns.getActivityId())){
                boolean bl = false;
                Integer actNum = actMap.get(histIns.getActivityId());
                // 该活动节点，后续节点是否在结束节点之前，在后续节点中是否存在
                for (int j=i+1; j<list.size(); j++){
                    HistoricActivityInstance hi = list.get(j);
                    Integer actNumA = actMap.get(hi.getActivityId());
                    if ((actNumA != null && actNumA < actNum) || StringUtils.equals(hi.getActivityId(), histIns.getActivityId())){
                        bl = true;
                    }
                }
                if (!bl){
                    break;
                }
            }
        }
        return actList;
    }

    /**
     * 获取流程列表
     * @param category 流程分类
     */
    public Page<Object[]> processList(Page<Object[]> page, String category) {
		/*
		 * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
		 */
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
                .latestVersion().active().orderByProcessDefinitionKey().asc();

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
     * 获取流程表单（首先获取任务节点表单KEY，如果没有则取流程开始节点表单KEY）
     * @return
     */
    public String getFormKey(String procDefId, String taskDefKey){
        String formKey = "";
        if (StringUtils.isNotBlank(procDefId)){
            if (StringUtils.isNotBlank(taskDefKey)){
                try{
                    formKey = formService.getTaskFormKey(procDefId, taskDefKey);
                }catch (Exception e) {
                    formKey = "";
                }
            }
            if (StringUtils.isBlank(formKey)){
                formKey = formService.getStartFormKey(procDefId);
            }
            if (StringUtils.isBlank(formKey)){
                formKey = "/404";
            }
        }
        logger.debug("getFormKey: {}", formKey);
        return formKey;
    }

    /**
     * 获取流程实例对象
     * @param procInsId
     * @return
     */
    @Transactional(readOnly = false)
    public ProcessInstance getProcIns(String procInsId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(procInsId).singleResult();
    }

    /**
     * 启动流程
     * @param procDefKey 流程定义KEY
     * @param businessTable 业务表表名
     * @param businessId	业务表编号
     * @return 流程实例ID
     */
    @Transactional(readOnly = false)
    public String startProcess(String procDefKey, String businessTable, String businessId) {
        return startProcess(procDefKey, businessTable, businessId, "");
    }

    /**
     * 启动流程
     * @param procDefKey 流程定义KEY
     * @param businessTable 业务表表名
     * @param businessId	业务表编号
     * @param title			流程标题，显示在待办任务标题
     * @return 流程实例ID
     */
    @Transactional(readOnly = false)
    public String startProcess(String procDefKey, String businessTable, String businessId, String title) {
        Map<String, Object> vars = Maps.newHashMap();
        return startProcess(procDefKey, businessTable, businessId, title, vars);
    }

    /**
     * 启动流程
     * @param procDefKey 流程定义KEY
     * @param businessTable 业务表表名
     * @param businessId	业务表编号
     * @param title			流程标题，显示在待办任务标题
     * @param vars			流程变量
     * @return 流程实例ID
     */
    @Transactional(readOnly = false)
    public String startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars) {
        String userId = UserUtils.getUser().getLoginName();//ObjectUtils.toString(UserUtils.getUser().getId())

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);

        // 设置流程变量
        if (vars == null){
            vars = Maps.newHashMap();
        }

        // 设置流程标题
        if (StringUtils.isNotBlank(title)){
            vars.put("title", title);
        }

        // 启动流程
        ProcessInstance procIns = runtimeService.startProcessInstanceByKey(procDefKey, businessTable+":"+businessId, vars);

        // 更新业务表流程实例ID
        Act act = new Act();
        act.setBusinessTable(businessTable);// 业务表名
        act.setBusinessId(businessId);	// 业务表ID
        act.setProcInsId(procIns.getId());
        actDao.updateProcInsIdByBusinessId(act);
        return act.getProcInsId();
    }

    /**
     * 获取任务
     * @param taskId 任务ID
     */
    public Task getTask(String taskId){
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    /**
     * 删除任务
     * @param taskId 任务ID
     * @param deleteReason 删除原因
     */
    @Transactional(readOnly = false)
    public void deleteTask(String taskId, String deleteReason){
        taskService.deleteTask(taskId, deleteReason);
    }

    /**
     * 签收任务
     * @param taskId 任务ID
     * @param userId 签收用户ID（用户登录名）
     */
    @Transactional(readOnly = false)
    public void claim(String taskId, String userId){
        taskService.claim(taskId, userId);
    }

    /**
     * 提交任务, 并保存意见
     * @param taskId 任务ID
     * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
     * @param comment 任务提交意见的内容
     * @param vars 任务变量
     */
    @Transactional(readOnly = false)
    public void complete(String taskId, String procInsId, String comment, Map<String, Object> vars){
        complete(taskId, procInsId, comment, "", vars);
    }

}
