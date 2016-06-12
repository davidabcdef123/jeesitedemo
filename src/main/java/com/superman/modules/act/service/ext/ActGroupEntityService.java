package com.superman.modules.act.service.ext;

import com.superman.common.utils.SpringContextHolder;
import com.superman.modules.sys.service.SystemService;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.stereotype.Service;

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
}
