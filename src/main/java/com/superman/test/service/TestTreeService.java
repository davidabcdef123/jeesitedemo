package com.superman.test.service;

import com.superman.common.service.TreeService;
import com.superman.common.utils.StringUtils;
import com.superman.test.dao.TestTreeDao;
import com.superman.test.entity.TestTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  树结构生成Service
 * Created by Super on 2016/7/10.
 */
@Service
@Transactional(readOnly = true)
public class TestTreeService  extends TreeService<TestTreeDao, TestTree> {

    public TestTree get(String id) {
        return super.get(id);
    }

    public List<TestTree> findList(TestTree testTree) {
        if (StringUtils.isNotBlank(testTree.getParentIds())){
            testTree.setParentIds(","+testTree.getParentIds()+",");
        }
        return super.findList(testTree);
    }

    @Transactional(readOnly = false)
    public void save(TestTree testTree) {
        super.save(testTree);
    }

    @Transactional(readOnly = false)
    public void delete(TestTree testTree) {
        super.delete(testTree);
    }
}
