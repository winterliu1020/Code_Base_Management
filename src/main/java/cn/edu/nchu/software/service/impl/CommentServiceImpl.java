package cn.edu.nchu.software.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nchu.software.entity.CommentEntity;
import cn.edu.nchu.software.mapper.CommentMapper;
import cn.edu.nchu.software.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int insertComment(CommentEntity commentEntity) {
		return commentMapper.insertComment(commentEntity);
	}

	@Override
	public List<CommentEntity> listCommentByArticleID(Integer articleID) {
		return commentMapper.listCommentByArticleID(articleID);
	}

	@Override
	public int listCommentCountByArticleID(Integer articleID) {
		return commentMapper.listCommentCountByArticleID(articleID);
	}

	@Override
	public int updateRemarksByCommentID(Integer commentID,String remarks) {
		return commentMapper.updateRemarksByCommentID(commentID,remarks);
	}

	@Override
	public Date newCommentByArticleID(Integer articleID) {
		return commentMapper.newCommentByArticleID(articleID);
	}

}
