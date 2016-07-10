package com.superman.modules.sys.service;

import com.superman.common.service.TreeService;
import com.superman.modules.sys.dao.AreaDao;
import com.superman.modules.sys.entity.Area;
import com.superman.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  区域Service
 * Created by Super on 2016/7/10.
 */
@Service
@Transactional(readOnly = true)
public class AreaService  extends TreeService<AreaDao, Area> {

    public List<Area> findAll(){
        return UserUtils.getAreaList();
    }

    @Transactional(readOnly = false)
    public void save(Area area) {
        super.save(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

    @Transactional(readOnly = false)
    public void delete(Area area) {
        super.delete(area);
        UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }
}
