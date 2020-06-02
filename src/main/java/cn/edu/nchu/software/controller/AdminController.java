package cn.edu.nchu.software.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nchu.software.entity.UserEntity;
import cn.edu.nchu.software.service.UserService;
import cn.edu.nchu.software.util.Pages;

@Controller
public class AdminController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/listUser")
	public String listUser(@RequestParam(value = "pagenum", required = false) Integer pagenum,Model model) {
		
		Pages pages = new Pages(3);
		if(pagenum == null) {
			pages.setPageNum(1);
		}else {
			pages.setPageNum(pagenum);
		}
		List<UserEntity> list = userService.listAll(pages);
		model.addAttribute("users",list);
		model.addAttribute("pages", pages);
		return "/admin";		
	}
	
	@RequestMapping("/multipleParametersQuery")
	public String multipleParametersQuery(@RequestParam(value = "pagenum", required = false) Integer pagenum,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "userType", required = false) Integer userType,
			Model model) {
		
		Pages pages = new Pages(1);
		if(pagenum == null) {
			pages.setPageNum(1);
		}else {
			pages.setPageNum(pagenum);
		}
		logger.info("--"+userType);
		List<UserEntity> list = userService.multipleParametersQuery(name, username, userType, pages);
		model.addAttribute("users",list);
		model.addAttribute("pages", pages);
		model.addAttribute("flag", 1);
		model.addAttribute("name", name);
		model.addAttribute("username", username);
		model.addAttribute("userType", userType);
		return "/admin";		
	}
	
	@RequestMapping("/addUser")
	public String addUser(UserEntity userEntity) {
		userService.insertUser(userEntity);
		return "redirect:/listUser";
	}
	
	@ResponseBody
	@RequestMapping("/deleteUser/{userNO}")
	public String deleteUser(@PathVariable Integer userNO) {
		int num = userService.deleteUser(userNO);
		if(num == 1) {
			return "删除成功！";
		} else {
			return "删除失败！";
		}
	}

	@RequestMapping("/updatetUser")
	public String updateUser(UserEntity user,Model model) {
		logger.info(user.toString());
		int num = userService.updateUser(user);
		return "redirect:/listUser";
	}
	
	@ResponseBody
	@RequestMapping("/updatetUserKey/{userID}/{state}")
	public String updatetUserKey(@PathVariable Integer userID,@PathVariable Integer state) {
		int num = userService.updatetUserKey(userID, state);
		return "成功！";
	}
	
}
