package cn.edu.nchu.software.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.CommentEntity;

@Repository
public interface CommentMapper {

	int insertComment(CommentEntity commentEntity);
	List<CommentEntity> listCommentByArticleID(Integer articleID);
	int listCommentCountByArticleID(Integer articleID);
	int updateRemarksByCommentID(@Param("commentID")Integer commentID,@Param("remarks")String remarks);
	Date newCommentByArticleID(Integer articleID);
	
}
