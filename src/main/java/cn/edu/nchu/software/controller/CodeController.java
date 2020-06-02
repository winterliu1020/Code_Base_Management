package cn.edu.nchu.software.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.alibaba.druid.sql.visitor.functions.Left;

import cn.edu.nchu.software.entity.ArchivingEntity;
import cn.edu.nchu.software.entity.CodeAnalysisEntity;
import cn.edu.nchu.software.entity.CodeEntity;
import cn.edu.nchu.software.entity.EvaluateEntity;
import cn.edu.nchu.software.entity.TypeEntity;
import cn.edu.nchu.software.service.ArchivingService;
import cn.edu.nchu.software.service.CodeService;
import cn.edu.nchu.software.service.EvaluateService;
import cn.edu.nchu.software.service.TypeService;
import cn.edu.nchu.software.service.UserService;
import cn.edu.nchu.software.util.CMDUtil;
import cn.edu.nchu.software.util.FileUtil;
import cn.edu.nchu.software.util.HTMLUtil;
import cn.edu.nchu.software.util.Pages;

@Controller
public class CodeController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String[] codeType= {"java","c","cpp","txt"};
	
	@Value("${img.location}")
	private String folder;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	@Autowired
	private ArchivingService archivingService;
	
	@RequestMapping("/code")
	public String toCodeEdit(Model model) {
		List<TypeEntity> types = typeService.listAll();
		model.addAttribute("types", types);
		return "/forms/codePublish";
	}
	
	@ResponseBody
	@RequestMapping("/code/add")
	public String addCode(CodeEntity codeEntity,Model model) {
		logger.info(codeEntity.toString());
		String codeName = FileUtil.uploadCode(codeEntity.getCodeContext(), codeType[codeEntity.getTypeID()-1], folder + "/code");
		codeEntity.setAnalysisResults(codeName);
		int result = codeService.insertCode(codeEntity);
		if(result == 1) {
			return "发布成功！";
		} else {
			return "发布失败！";
		}
	}
	
	@RequestMapping("/code/list/{type}")
	public String listCode(Model model,@PathVariable Integer type,@RequestParam(value = "pagenum", required = false) Integer pagenum) {
		Pages pages = new Pages(2);
		if (pagenum == null) {
			pages.setPageNum(1);
		} else {
			pages.setPageNum(pagenum);
		}
		List<CodeEntity> codes = codeService.listAllCode(type,pages);
		model.addAttribute("type", type);
		model.addAttribute("codes", codes);
		model.addAttribute("pages", pages);
		model.addAttribute("typeService", typeService);
		model.addAttribute("htmlUtil", new HTMLUtil());
		model.addAttribute("evaluateService", evaluateService);
		model.addAttribute("userService", userService);
		return "codeShowArea";
	}
	
	@RequestMapping("/code/{codeID}")
	public String showCode(@PathVariable Integer codeID,Model model) {
		CodeEntity code = codeService.findCodeByCodeID(codeID);
		codeService.countSelfIncrement(codeID);
		model.addAttribute("code", code);
		model.addAttribute("userService", userService);
		model.addAttribute("htmlUtil", new HTMLUtil());
		return "/examples/code";
	}
	
	@ResponseBody
	@RequestMapping("/countSelfIncrement/{codeID}")
	public CodeEntity countSelfIncrement(@PathVariable Integer codeID) {
		CodeEntity code = codeService.findCodeByCodeID(codeID);
		codeService.countSelfIncrement(codeID);
		return code;
	}
	
	@ResponseBody
	@RequestMapping("/evaluate/add")
	public String addEvaluate(EvaluateEntity evaluateEntity) {
		int result = evaluateService.insertEvaluate(evaluateEntity);
		if(result == 1) {
			return "评论成功！";
		} else {
			return "评论失败！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/archiving/add")
	public String archivingCode(ArchivingEntity archivingEntity) {
		if(archivingService.findArchivingByUserIDCodeID(archivingEntity.getUserID(), archivingEntity.getCodeID())==null) {
			int result = archivingService.insertArchiving(archivingEntity);
			if(result == 1) {
				return "归档成功！";
			} else {
				return "归档失败！";
			}
		} else {
			return "您已经归档过该代码了！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/archiving/{userID}/{codeID}")
	public String showArchiving(@PathVariable Integer userID,@PathVariable Integer codeID) {
		ArchivingEntity archivingEntity = archivingService.findArchivingByUserIDCodeID(userID, codeID);
		if(archivingEntity != null) {
			return archivingEntity.getRemarks();
		} else {
			return "没有找到备注！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/cancelArchiving/{userID}/{codeID}")
	public String cancelArchiving(@PathVariable Integer userID,@PathVariable Integer codeID) {
		int result = archivingService.deleteArchivingByUserIDCodeID(userID, codeID);
		if(result == 1) {
			return "取消成功！";
		} else {
			return "取消失败！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/analysis/{codeID}")
	public List<CodeAnalysisEntity> codeAnalysis(@PathVariable Integer codeID) {
		CodeEntity code = codeService.findCodeByCodeID(codeID);
		String codeName = code.getAnalysisResults();
		List<CodeEntity> codes = codeService.listAllCodeByTypeIDCodeID(code.getTypeID(), code.getCodeID());
		List<CodeAnalysisEntity> cs= new ArrayList<CodeAnalysisEntity>();
		if(codes != null) {
			for(int i = 0;i < codes.size();i++) {
				CodeEntity codeEntity = codes.get(i);
				String value = CMDUtil.execCMD(code.getTypeID(), folder + "/code",codeName,codeEntity.getAnalysisResults(),"30");
				int left = -1;
				if((left = value.indexOf("consists")) != -1) {
					cs.add(new CodeAnalysisEntity(codeEntity.getCodeTitle(),codeEntity.getCodeContext(),value.substring(left + 13, value.indexOf("of") - 1)));
				}
			}
		}
		return cs;
	}
}
