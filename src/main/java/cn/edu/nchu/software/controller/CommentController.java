package cn.edu.nchu.software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nchu.software.entity.CommentEntity;
import cn.edu.nchu.software.entity.CommunicationEntity;
import cn.edu.nchu.software.entity.MyLikeEntity;
import cn.edu.nchu.software.service.CommentService;
import cn.edu.nchu.software.service.CommunicationService;
import cn.edu.nchu.software.service.MyLikeService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MyLikeService myLikeService;
	
	@Autowired
	private CommunicationService communicationService;
	
	@ResponseBody
	@RequestMapping("/comment/add")
	public String addComment(CommentEntity commentEntity) {
		commentEntity.setMyLike(0);
		int result = commentService.insertComment(commentEntity);
		if(result == 1) {
			return "评论成功！";
		} else {
			return "评论失败！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/like/add")
	public String addLike(MyLikeEntity myLikeEntity) {
		int result = myLikeService.insertMyLike(myLikeEntity);
		if(result == 1) {
			return "点赞成功！";
		} else {
			return "点赞失败！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/like/delete")
	public String deleteLike(MyLikeEntity myLikeEntity) {
		int result = myLikeService.deleteMyLike(myLikeEntity);
		if(result == 1) {
			return "取消点赞成功！";
		} else {
			return "取消点赞失败！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/communication/add")
	public String addComment(CommunicationEntity communicationEntity) {
		int result = communicationService.insertCommunication(communicationEntity);
		if(result == 1) {
			return "回复成功！";
		} else {
			return "回复失败！";
		}
	}
	
	@ResponseBody
	@RequestMapping("/updateRemarks/{commentID}/{remarks}")
	public String updateRemarks(@PathVariable Integer commentID,@PathVariable Integer remarks) {
		String str = "";
		if(remarks == 1) {
			str="已采纳";
		}
		int result = commentService.updateRemarksByCommentID(commentID,str);
		if(result == 1) {
			return "修改成功！";
		} else {
			return "修改失败！";
		}
	}
}
