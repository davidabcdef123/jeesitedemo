package com.superman.modules.cms.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.cms.entity.Comment;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * 评论DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface CommentDao  extends CrudDao<Comment> {

}
