package cn.edu.nchu.software.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CommentEntity {
	private Integer commentID;
	private Integer userID;
	private Integer articleID;
	private String commentContext;
	private Date commentTime;
	private String remarks;
	private Integer myLike;
	
	public CommentEntity(Integer commentID, Integer userID, Integer articleID, String commentContext, Date commentTime,
			String remarks) {
		super();
		this.commentID = commentID;
		this.userID = userID;
		this.articleID = articleID;
		this.commentContext = commentContext;
		this.commentTime = commentTime;
		this.remarks = remarks;
	}
}
