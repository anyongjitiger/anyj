package com.emep.zaixian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.emep.zaixian.model.User;
import com.emep.zaixian.security.web.shiro.filter.bind.annotation.CurrentUser;

@Controller
@RequestMapping("")
public class IndexController {
	@RequestMapping("/index")
	String getIndex(@CurrentUser User user,Model model) {
		model.addAttribute("name", user);
		return "/index";
	}
//	public @ResponseBody
	@RequestMapping("/addNews")
	String getAddArticle() {
		return "/addNews";
	}
}
