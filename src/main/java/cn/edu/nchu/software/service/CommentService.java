package cn.edu.nchu.software.service;

import java.util.Date;
import java.util.List;

import cn.edu.nchu.software.entity.CommentEntity;

public interface CommentService {

	int insertComment(CommentEntity commentEntity);
	List<CommentEntity> listCommentByArticleID(Integer articleID);
	int listCommentCountByArticleID(Integer articleID);
	int updateRemarksByCommentID(Integer commentID,String remarks);
	Date newCommentByArticleID(Integer articleID);
}
