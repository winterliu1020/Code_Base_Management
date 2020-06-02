package cn.edu.nchu.software.controller;

import cn.edu.nchu.software.editor.info.FileInfo;
import cn.edu.nchu.software.entity.CommentEntity;
import cn.edu.nchu.software.service.CommentService;
import cn.edu.nchu.software.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Controller
public class FileController {

	@Value("${img.location}")
	private String folder;
	
	@Autowired
	private CommentService commentService;

	@ResponseBody
	@RequestMapping("/file")
	public FileInfo upload(HttpServletRequest request,
			@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) throws Exception {
		log.info("【FileController】 fileName={},fileOrginNmae={},fileSize={}", file.getName(),
				file.getOriginalFilename(), file.getSize());
		log.info(request.getContextPath());
		String newFileName = FileUtil.upload(folder, file, request);
		if(newFileName != null) {
			String fileURL = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")) + "/upload/" + newFileName;
			return new FileInfo(1, "上传成功!", fileURL);
		} else {
			return new FileInfo(0, "上传失败!", null);
		}
	}
	
	@ResponseBody
	@RequestMapping("file/code/{userID}/{articleID}")
	public FileInfo uploadCode(HttpServletRequest request,@PathVariable Integer userID,@PathVariable Integer articleID,
			@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
		log.info("【FileController】 fileName={},fileOrginNmae={},fileSize={}", file.getName(),
				file.getOriginalFilename(), file.getSize());
		log.info(request.getContextPath());
		String newFileName = FileUtil.upload(folder, file, request);
		if(newFileName != null) {
			String fileURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/")) + "/upload/" + newFileName;
			String HTML= "<a href='" + fileURL + "'>" + file.getOriginalFilename() + "</a>";
			System.out.println(HTML);
			CommentEntity commentEntity = new CommentEntity(null,userID,articleID,HTML,new Date(),"");
			commentService.insertComment(commentEntity);
			return new FileInfo(1, "上传成功!", fileURL);
		} else {
			return new FileInfo(0, "上传失败!", null);
		}
	}
	
	@RequestMapping("file/{fileName}")
	public void download(@PathVariable String fileName,HttpServletRequest request, HttpServletResponse response) {
		if (FileUtil.download(folder+fileName,fileName, request, response) == 0) {
			request.setAttribute("message", "下载资源失败！！");
		}
	}

}
