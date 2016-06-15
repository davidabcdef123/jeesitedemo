package com.superman.modules.cms.service;

import com.superman.common.service.CrudService;
import com.superman.modules.cms.dao.ArticleDataDao;
import com.superman.modules.cms.entity.ArticleData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 *
 * @author Super.Sun
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService  extends CrudService<ArticleDataDao, ArticleData> {
}
