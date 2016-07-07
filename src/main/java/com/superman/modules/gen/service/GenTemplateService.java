package com.superman.modules.gen.service;

import com.superman.common.persistence.Page;
import com.superman.common.service.BaseService;
import com.superman.common.utils.StringUtils;
import com.superman.modules.gen.dao.GenTemplateDao;
import com.superman.modules.gen.entity.GenTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 * 代码模板Service
 * @author Super.Sun
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class GenTemplateService  extends BaseService {

    @Autowired
    private GenTemplateDao genTemplateDao;

    public GenTemplate get(String id) {
        return genTemplateDao.get(id);
    }

    public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
        genTemplate.setPage(page);
        page.setList(genTemplateDao.findList(genTemplate));
        return page;
    }

    @Transactional(readOnly = false)
    public void save(GenTemplate genTemplate) {
        if (genTemplate.getContent()!=null){
            genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
        }
        if (StringUtils.isBlank(genTemplate.getId())){
            genTemplate.preInsert();
            genTemplateDao.insert(genTemplate);
        }else{
            genTemplate.preUpdate();
            genTemplateDao.update(genTemplate);
        }
    }

    @Transactional(readOnly = false)
    public void delete(GenTemplate genTemplate) {
        genTemplateDao.delete(genTemplate);
    }
}
