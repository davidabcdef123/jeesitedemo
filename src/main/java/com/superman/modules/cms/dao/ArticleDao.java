package com.superman.modules.cms.dao;

import com.superman.common.persistence.CrudDao;
import com.superman.common.persistence.annotation.MyBatisDao;
import com.superman.modules.cms.entity.Article;
import com.superman.modules.cms.entity.Category;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * 文章DAO接口
 * @author Super.Sun
 * @version 1.0
 */
@MyBatisDao
public interface ArticleDao  extends CrudDao<Article> {
    public List<Article> findByIdIn(String[] ids);
//	{
//		return find("from Article where id in (:p1)", new Parameter(new Object[]{ids}));
//	}

    public int updateHitsAddOne(String id);
//	{
//		return update("update Article set hits=hits+1 where id = :p1", new Parameter(id));
//	}

    public int updateExpiredWeight(Article article);

    public List<Category> findStats(Category category);
//	{
//		return update("update Article set weight=0 where weight > 0 and weightDate < current_timestamp()");
//	}
}
