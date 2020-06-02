package cn.edu.nchu.software.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.edu.nchu.software.entity.TypeEntity;
import cn.edu.nchu.software.entity.UserEntity;
import cn.edu.nchu.software.service.TypeService;
import cn.edu.nchu.software.service.UserService;

@Controller
public class LoginController implements WebMvcConfigurer{

	@Autowired
	private UserService userService;
	
	@Autowired
	private TypeService typeService;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/examples/sign-in");
        registry.addViewController("/toRegister").setViewName("/examples/sign-up");
    }
	
	@RequestMapping("/register")
	public String register(UserEntity userEntity,Model model) {
		UserEntity u = userService.findUserByUsername(userEntity.getUsername());
		if(u == null) {
			int num = userService.insertUser(userEntity);
			if(num == 1) {
				model.addAttribute("message","注册成功！");
				return "/examples/sign-in";
			} else {
				model.addAttribute("message","注册失败！");
				return "/examples/sign-up";
			}
		} else {
			model.addAttribute("message","账户已存在！");
			return "/examples/sign-up";
		}
	}

	@RequestMapping(value = "/login")
	public String index(String username, String password, int userType, HttpSession session, Model model) {
		UserEntity userEntity = userService.findUserByUsernamePasswordUsertype(username, password, userType);
		if(userEntity == null) {
			model.addAttribute("message", "账号或密码错误！");
			return "/examples/sign-in";
		} else if(userEntity.getState() == 1){
			session.setAttribute("User", userEntity);
			List<TypeEntity> types = typeService.listAll();
			session.setAttribute("types", types);
			if(userEntity.getUserType() == 1) {
				return "redirect:/listUser";
			} else {
				return "redirect:/my/code/list/1";
			}
		} else {
			model.addAttribute("message", "账号已被锁定，无法登陆！");
			return "/examples/sign-in";
		}
	}
}
