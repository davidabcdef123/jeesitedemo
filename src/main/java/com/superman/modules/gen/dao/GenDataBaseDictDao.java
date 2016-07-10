package com.superman.modules.gen.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.gen.entity.GenTable;
import com.superman.modules.gen.entity.GenTableColumn;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/5.</p>
 * 业务表字段DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface GenDataBaseDictDao  extends CrudDao<GenTableColumn> {
    /**
     * 查询表列表
     * @param genTable
     * @return
     */
    List<GenTable> findTableList(GenTable genTable);

    /**
     * 获取数据表字段
     * @param genTable
     * @return
     */
    List<GenTableColumn> findTableColumnList(GenTable genTable);

    /**
     * 获取数据表主键
     * @param genTable
     * @return
     */
    List<String> findTablePK(GenTable genTable);
}