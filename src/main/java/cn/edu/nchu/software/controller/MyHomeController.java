package cn.edu.nchu.software.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.nchu.software.entity.ArchivingEntity;
import cn.edu.nchu.software.entity.ArticleEntity;
import cn.edu.nchu.software.entity.CodeEntity;
import cn.edu.nchu.software.entity.UserEntity;
import cn.edu.nchu.software.service.ArchivingService;
import cn.edu.nchu.software.service.ArticleService;
import cn.edu.nchu.software.service.CodeService;
import cn.edu.nchu.software.service.EvaluateService;
import cn.edu.nchu.software.service.TypeService;
import cn.edu.nchu.software.service.UserService;
import cn.edu.nchu.software.util.HTMLUtil;
import cn.edu.nchu.software.util.Pages;

@Controller
public class MyHomeController {
	
	@Autowired
	private TypeService typeService;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	@Autowired
	private ArchivingService archivingService;
	
	@RequestMapping("/my/code/list/{codeType}")
	public String listCode(Model model,HttpSession session,@PathVariable Integer codeType,@RequestParam(value = "pagenum", required = false) Integer pagenum) {
		Pages pages = new Pages(2);
		if (pagenum == null) {
			pages.setPageNum(1);
		} else {
			pages.setPageNum(pagenum);
		}
		UserEntity user = (UserEntity) session.getAttribute("User");
		List<CodeEntity> codes = null;
		if(codeType == 1) {
			codes = codeService.listCodeByUserID(user.getUserID(), pages);
		} else {
			List<ArchivingEntity> aList = archivingService.listArchivingByUserID(user.getUserID(), pages);
			codes = new ArrayList<CodeEntity>();
			for(int i = 0;i < aList.size();i++)
				codes.add(codeService.findCodeByCodeID(aList.get(i).getCodeID()));
		}
		model.addAttribute("codes", codes);
		model.addAttribute("codeType", codeType);
		model.addAttribute("pages", pages);
		model.addAttribute("typeService", typeService);
		model.addAttribute("htmlUtil", new HTMLUtil());
		model.addAttribute("evaluateService", evaluateService);
		model.addAttribute("userService", userService);
		return "/examples/profile";
	}
	
	@RequestMapping("/my/article/list/{articleType}")
	public String listArticle(@PathVariable Integer articleType,Model model,HttpSession session,@RequestParam(value = "pagenum", required = false) Integer pagenum) {
		model.addAttribute("articleType", articleType);
		Pages pages = new Pages(2);
		if (pagenum == null) {
			pages.setPageNum(1);
		} else {
			pages.setPageNum(pagenum);
		}
		UserEntity user = (UserEntity) session.getAttribute("User");
		List<ArticleEntity> articles = articleService.listArticleByArticleTypeUserID(articleType, user.getUserID(), pages);
		model.addAttribute("articles", articles);
		model.addAttribute("pages", pages);
		model.addAttribute("typeService", typeService);
		model.addAttribute("htmlUtil", new HTMLUtil());
		return "/examples/profile";
	}
}
