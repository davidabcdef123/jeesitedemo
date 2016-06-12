package com.superman.modules.act.service.ext;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/12.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
public class ActGroupEntityServiceFactory implements SessionFactory{

    @Autowired
    private ActGroupEntityService actGroupEntityService;

    public Class<?> getSessionType() {
        // 返回原始的GroupIdentityManager类型
        return GroupIdentityManager.class;
    }

    public Session openSession() {
        // 返回自定义的GroupEntityManager实例
        return actGroupEntityService;
    }
}
