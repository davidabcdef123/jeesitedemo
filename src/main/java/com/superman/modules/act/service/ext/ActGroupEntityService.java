package com.superman.modules.act.service.ext;

import com.google.common.collect.Lists;
import com.superman.common.utils.SpringContextHolder;
import com.superman.modules.act.utils.ActUtils;
import com.superman.modules.sys.entity.Role;
import com.superman.modules.sys.entity.User;
import com.superman.modules.sys.service.SystemService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/12.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
@Service
public class ActGroupEntityService extends UserEntityManager{

    private SystemService systemService;

    public SystemService getSystemService() {
        if (systemService == null){
            systemService = SpringContextHolder.getBean(SystemService.class);
        }
        return systemService;
    }

    public Group createNewGroup(String groupId) {
        return new GroupEntity(groupId);
    }

    public void insertGroup(Group group) {
//		getDbSqlSession().insert((PersistentObject) group);
        throw new RuntimeException("not implement method.");
    }

    public void updateGroup(GroupEntity updatedGroup) {
//		CommandContext commandContext = Context.getCommandContext();
//		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
//		dbSqlSession.update(updatedGroup);
        throw new RuntimeException("not implement method.");
    }

    public void deleteGroup(String groupId) {
//		GroupEntity group = getDbSqlSession().selectById(GroupEntity.class, groupId);
//		getDbSqlSession().delete("deleteMembershipsByGroupId", groupId);
//		getDbSqlSession().delete(group);
        throw new RuntimeException("not implement method.");
    }

    public GroupQuery createNewGroupQuery() {
//		return new GroupQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
        throw new RuntimeException("not implement method.");
    }

    //	@SuppressWarnings("unchecked")
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
//		return getDbSqlSession().selectList("selectGroupByQueryCriteria", query, page);
        throw new RuntimeException("not implement method.");
    }

    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
//		return (Long) getDbSqlSession().selectOne("selectGroupCountByQueryCriteria", query);
        throw new RuntimeException("not implement method.");
    }

    public List<Group> findGroupsByUser(String userId) {
//		return getDbSqlSession().selectList("selectGroupsByUserId", userId);
        List<Group> list = Lists.newArrayList();
        User user = getSystemService().getUserByLoginName(userId);
        if (user != null && user.getRoleList() != null){
            for (Role role : user.getRoleList()){
                list.add(ActUtils.toActivitiGroup(role));
            }
        }
        return list;
    }

    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
//		return getDbSqlSession().selectListWithRawParameter("selectGroupByNativeQuery", parameterMap, firstResult, maxResults);
        throw new RuntimeException("not implement method.");
    }

    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
//		return (Long) getDbSqlSession().selectOne("selectGroupCountByNativeQuery", parameterMap);
        throw new RuntimeException("not implement method.");
    }
}
