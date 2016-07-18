package com.superman.modules.gen.web;

import com.superman.common.persistence.Page;
import com.superman.common.utils.StringUtils;
import com.superman.common.web.BaseController;
import com.superman.modules.gen.entity.GenTemplate;
import com.superman.modules.gen.service.GenTemplateService;
import com.superman.modules.sys.entity.User;
import com.superman.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Super.Sun
 * Email: Super.Sun@163.com
 * <p>
 *  代码模板Controller
 * CreateTime: 2016/7/18
 * Version: 1.0
 * Describe:
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genTemplate")
public class GenTemplateController  extends BaseController {

    @Autowired
    private GenTemplateService genTemplateService;

    @ModelAttribute
    public GenTemplate get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return genTemplateService.get(id);
        }else{
            return new GenTemplate();
        }
    }

    @RequiresPermissions("gen:genTemplate:view")
    @RequestMapping(value = {"list", ""})
    public String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        if (!user.isAdmin()){
            genTemplate.setCreateBy(user);
        }
        Page<GenTemplate> page = genTemplateService.find(new Page<GenTemplate>(request, response), genTemplate);
        model.addAttribute("page", page);
        return "modules/gen/genTemplateList";
    }

    @RequiresPermissions("gen:genTemplate:view")
    @RequestMapping(value = "form")
    public String form(GenTemplate genTemplate, Model model) {
        model.addAttribute("genTemplate", genTemplate);
        return "modules/gen/genTemplateForm";
    }

    @RequiresPermissions("gen:genTemplate:edit")
    @RequestMapping(value = "save")
    public String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, genTemplate)){
            return form(genTemplate, model);
        }
        genTemplateService.save(genTemplate);
        addMessage(redirectAttributes, "保存代码模板'" + genTemplate.getName() + "'成功");
        return "redirect:" + adminPath + "/gen/genTemplate/?repage";
    }

    @RequiresPermissions("gen:genTemplate:edit")
    @RequestMapping(value = "delete")
    public String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
        genTemplateService.delete(genTemplate);
        addMessage(redirectAttributes, "删除代码模板成功");
        return "redirect:" + adminPath + "/gen/genTemplate/?repage";
    }

}
