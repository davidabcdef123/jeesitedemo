package com.superman.modules.cms.service;

import com.superman.common.persistence.Page;
import com.superman.common.service.CrudService;
import com.superman.modules.cms.dao.CommentDao;
import com.superman.modules.cms.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * 评论Service
 * @author Super.Sun
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class CommentService  extends CrudService<CommentDao, Comment> {

    public Page<Comment> findPage(Page<Comment> page, Comment comment) {
//		DetachedCriteria dc = commentDao.createDetachedCriteria();
//		if (StringUtils.isNotBlank(comment.getContentId())){
//			dc.add(Restrictions.eq("contentId", comment.getContentId()));
//		}
//		if (StringUtils.isNotEmpty(comment.getTitle())){
//			dc.add(Restrictions.like("title", "%"+comment.getTitle()+"%"));
//		}
//		dc.add(Restrictions.eq(Comment.FIELD_DEL_FLAG, comment.getDelFlag()));
//		dc.addOrder(Order.desc("id"));
//		return commentDao.find(page, dc);
        comment.getSqlMap().put("dsf", dataScopeFilter(comment.getCurrentUser(), "o", "u"));

        return super.findPage(page, comment);
    }

    @Transactional(readOnly = false)
    public void delete(Comment entity, Boolean isRe) {
        super.delete(entity);
    }
}
