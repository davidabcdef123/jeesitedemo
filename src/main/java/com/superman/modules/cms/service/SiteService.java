package com.superman.modules.cms.service;

import com.superman.common.persistence.Page;
import com.superman.common.service.CrudService;
import com.superman.modules.cms.dao.SiteDao;
import com.superman.modules.cms.entity.Site;
import com.superman.modules.cms.utils.CmsUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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
public class SiteService  extends CrudService<SiteDao, Site> {

    public Page<Site> findPage(Page<Site> page, Site site) {
//		DetachedCriteria dc = siteDao.createDetachedCriteria();
//		if (StringUtils.isNotEmpty(site.getName())){
//			dc.add(Restrictions.like("name", "%"+site.getName()+"%"));
//		}
//		dc.add(Restrictions.eq(Site.FIELD_DEL_FLAG, site.getDelFlag()));
//		//dc.addOrder(Order.asc("id"));
//		return siteDao.find(page, dc);

        site.getSqlMap().put("site", dataScopeFilter(site.getCurrentUser(), "o", "u"));

        return super.findPage(page, site);
    }

    @Transactional(readOnly = false)
    public void save(Site site) {
        if (site.getCopyright()!=null){
            site.setCopyright(StringEscapeUtils.unescapeHtml4(site.getCopyright()));
        }
        super.save(site);
        CmsUtils.removeCache("site_"+site.getId());
        CmsUtils.removeCache("siteList");
    }

    @Transactional(readOnly = false)
    public void delete(Site site, Boolean isRe) {
        //siteDao.updateDelFlag(id, isRe!=null&&isRe?Site.DEL_FLAG_NORMAL:Site.DEL_FLAG_DELETE);
        site.setDelFlag(isRe!=null&&isRe?Site.DEL_FLAG_NORMAL:Site.DEL_FLAG_DELETE);
        super.delete(site);
        //siteDao.delete(id);
        CmsUtils.removeCache("site_"+site.getId());
        CmsUtils.removeCache("siteList");
    }
}
