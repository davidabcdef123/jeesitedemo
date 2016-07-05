package com.superman.modules.cms.web;

import com.superman.common.web.BaseController;
import com.superman.modules.cms.entity.Site;
import com.superman.modules.cms.service.FileTplService;
import com.superman.modules.cms.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/5.</p>
 * 站点Controller
 * @author Super.Sun
 * @version 1.0
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/template")
public class TemplateController  extends BaseController {

    @Autowired
    private FileTplService fileTplService;
    @Autowired
    private SiteService siteService;

    @RequiresPermissions("cms:template:edit")
    @RequestMapping(value = "")
    public String index() {
        return "modules/cms/tplIndex";
    }

    @RequiresPermissions("cms:template:edit")
    @RequestMapping(value = "tree")
    public String tree(Model model) {
        Site site = siteService.get(Site.getCurrentSiteId());
        model.addAttribute("templateList", fileTplService.getListForEdit(site.getSolutionPath()));
        return "modules/cms/tplTree";
    }

    @RequiresPermissions("cms:template:edit")
    @RequestMapping(value = "form")
    public String form(String name, Model model) {
        model.addAttribute("template", fileTplService.getFileTpl(name));
        return "modules/cms/tplForm";
    }

    @RequiresPermissions("cms:template:edit")
    @RequestMapping(value = "help")
    public String help() {
        return "modules/cms/tplHelp";
    }
}
