package com.superman.modules.cms.web;

import com.superman.common.web.BaseController;
import com.superman.modules.cms.entity.Category;
import com.superman.modules.cms.service.StatsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/5.</p>
 * 统计Controller
 * @author Super.Sun
 * @version 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/stats")
public class StatsController  extends BaseController {

    @Autowired
    private StatsService statsService;

    /**
     * 文章信息量
     * @param paramMap
     * @param model
     * @return
     */
    @RequiresPermissions("cms:stats:article")
    @RequestMapping(value = "article")
    public String article(@RequestParam Map<String, Object> paramMap, Model model) {
        List<Category> list = statsService.article(paramMap);
        model.addAttribute("list", list);
        model.addAttribute("paramMap", paramMap);
        return "modules/cms/statsArticle";
    }
}
