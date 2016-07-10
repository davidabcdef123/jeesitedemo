package com.superman.test.service;

import com.superman.common.persistence.Page;
import com.superman.common.service.CrudService;
import com.superman.test.dao.TestDataDao;
import com.superman.test.entity.TestData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单表生成Service
 * Created by Super on 2016/7/10.
 */
@Service
@Transactional(readOnly = true)
public class TestDataService  extends CrudService<TestDataDao, TestData> {

    public TestData get(String id) {
        return super.get(id);
    }

    public List<TestData> findList(TestData testData) {
        return super.findList(testData);
    }

    public Page<TestData> findPage(Page<TestData> page, TestData testData) {
        return super.findPage(page, testData);
    }

    @Transactional(readOnly = false)
    public void save(TestData testData) {
        super.save(testData);
    }

    @Transactional(readOnly = false)
    public void delete(TestData testData) {
        super.delete(testData);
    }

}
