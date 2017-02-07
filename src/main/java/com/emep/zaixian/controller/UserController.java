package com.emep.zaixian.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emep.zaixian.model.User;
import com.emep.zaixian.security.web.shiro.filter.bind.annotation.CurrentUser;
import com.emep.zaixian.service.OrganizationService;
import com.emep.zaixian.service.RoleService;
import com.emep.zaixian.service.UserFacade;
import com.google.gson.Gson;

@Controller
@RequestMapping("/User")
public class UserController {
	@RequestMapping(value="/User",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@RequiresPermissions("user:view")
	public @ResponseBody
	String getIndex(@CurrentUser User user,Model model) {
		System.out.println(user.toString());
		model.addAttribute("name", user);
		Gson gson = new Gson();
		return gson.toJson(user);
	}
	
	@Autowired
    private UserFacade userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user/list";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new User());
        model.addAttribute("op", "新增");
        return "user/edit";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(User user, RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/user";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改");
        return "user/edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "删除");
        return "user/edit";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user";
    }


    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
        userService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }

    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}
