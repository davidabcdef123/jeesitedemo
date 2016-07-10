package com.superman.modules.sys.service;

import com.superman.common.service.CrudService;
import com.superman.common.utils.CacheUtils;
import com.superman.modules.sys.dao.DictDao;
import com.superman.modules.sys.entity.Dict;
import com.superman.modules.sys.utils.DictUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  字典Service
 * Created by Super on 2016/7/10.
 */
@Service
@Transactional(readOnly = true)
public class DictService  extends CrudService<DictDao, Dict> {

    /**
     * 查询字段类型列表
     * @return
     */
    public List<String> findTypeList(){
        return dao.findTypeList(new Dict());
    }

    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void delete(Dict dict) {
        super.delete(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }
}
