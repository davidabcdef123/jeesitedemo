package com.superman.modules.cms.web;

import com.superman.common.web.BaseController;
import com.superman.modules.cms.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/5.</p>
 * 内容管理Controller
 * @author Super.Sun
 * @version 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/cms")
public class CmsController  extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequiresPermissions("cms:view")
    @RequestMapping(value = "")
    public String index() {
        return "modules/cms/cmsIndex";
    }

    @RequiresPermissions("cms:view")
    @RequestMapping(value = "tree")
    public String tree(Model model) {
        model.addAttribute("categoryList", categoryService.findByUser(true, null));
        return "modules/cms/cmsTree";
    }

    @RequiresPermissions("cms:view")
    @RequestMapping(value = "none")
    public String none() {
        return "modules/cms/cmsNone";
    }

}
