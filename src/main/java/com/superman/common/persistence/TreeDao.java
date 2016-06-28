package com.superman.common.persistence;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/2.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
    public List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    public int updateParentIds(T entity);

}
