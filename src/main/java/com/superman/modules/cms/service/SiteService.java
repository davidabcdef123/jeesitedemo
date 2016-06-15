package com.superman.modules.cms.service;

import com.superman.modules.cms.entity.Site;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * 站点Service
 * @author Super.Sun
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SiteService  extends CrudService<SiteDao, Site>{
}
