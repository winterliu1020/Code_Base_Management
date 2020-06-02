package cn.edu.nchu.software.controller;

import java.util.Date;
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

import cn.edu.nchu.software.entity.ArticleEntity;
import cn.edu.nchu.software.entity.CommentEntity;
import cn.edu.nchu.software.entity.TypeEntity;
import cn.edu.nchu.software.service.ArticleService;
import cn.edu.nchu.software.service.CommentService;
import cn.edu.nchu.software.service.CommunicationService;
import cn.edu.nchu.software.service.MyLikeService;
import cn.edu.nchu.software.service.TypeService;
import cn.edu.nchu.software.service.UserService;
import cn.edu.nchu.software.util.HTMLUtil;
import cn.edu.nchu.software.util.Pages;

@Controller
public class ArticleController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String[] titles = {"教程发布","代码征集","答疑解惑"};
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MyLikeService myLikeService;
	
	@Autowired
	private CommunicationService communicationService;
	
	@RequestMapping("/article/{articleType}")
	public String toArticleEdit(@PathVariable Integer articleType,Model model) {
		List<TypeEntity> types = typeService.listAll();
		model.addAttribute("types", types);
		model.addAttribute("articleType", articleType);
		model.addAttribute("title", titles[articleType - 1]);
		return "/forms/articlePublish";
	}
	
	@ResponseBody
	@RequestMapping("/article/add")
	public String addArticle(ArticleEntity articleEntity,Model model) {
		logger.info(articleEntity.toString());
		int result = articleService.insertArticle(articleEntity);
		if(result == 1) {
			return "发布成功！";
		} else {
			return "发布失败！";
		}
	}
	
	@RequestMapping("/article/list/{articleType}/{type}")
	public String listArticle(@PathVariable Integer articleType,@PathVariable Integer type,Model model,@RequestParam(value = "pagenum", required = false) Integer pagenum) {
		model.addAttribute("articleType", articleType);
		model.addAttribute("title", titles[articleType - 1]);
		Pages pages = new Pages(2);
		if (pagenum == null) {
			pages.setPageNum(1);
		} else {
			pages.setPageNum(pagenum);
		}
		List<ArticleEntity> articles = articleService.listArticleByArticleType(articleType,type,pages);
		model.addAttribute("type", type);
		model.addAttribute("articles", articles);
		model.addAttribute("pages", pages);
		model.addAttribute("typeService", typeService);
		model.addAttribute("htmlUtil", new HTMLUtil());
		return "articleShowArea";
	}
	
	@RequestMapping("/article/{articleType}/{articleID}")
	public String showArticle(@PathVariable Integer articleType,@PathVariable Integer articleID,Model model) {
		ArticleEntity articleEntity = articleService.findArticleByArticleID(articleID);
		articleService.countSelfIncrement(articleID);
		List<CommentEntity> comments = commentService.listCommentByArticleID(articleID);
		model.addAttribute("article", articleEntity);
		model.addAttribute("comments", comments);
		model.addAttribute("userService", userService);
		model.addAttribute("htmlUtil", new HTMLUtil());
		model.addAttribute("myLikeService", myLikeService);
		if(articleType == 1) {
			return "examples/article";	
		} else if(articleType == 2) {
			return "examples/codeCollection";
		} else {
			int count = commentService.listCommentCountByArticleID(articleID);
			Date oldTime = commentService.newCommentByArticleID(articleID);
			if(oldTime == null)
				oldTime = articleService.findArticleByArticleID(articleID).getUploadTime();
			Date newTimeDate = new Date();
			long time = (long)((newTimeDate.getTime() - oldTime.getTime()) / (1000 * 60 * 60 *24) + 0.5);
			logger.info("Time" +oldTime + time);
			model.addAttribute("count", count);
			model.addAttribute("time", time);
			model.addAttribute("communicationService", communicationService);
			return "examples/question";
		}
	}
}
