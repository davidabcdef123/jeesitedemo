package com.superman.modules.test.service;

import com.superman.common.service.CrudService;
import com.superman.modules.test.dao.TestDao;
import com.superman.modules.test.entity.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Super on 2016/7/10.
 */
@Service
@Transactional(readOnly = true)
public class TestService  extends CrudService<TestDao, Test> {
}
