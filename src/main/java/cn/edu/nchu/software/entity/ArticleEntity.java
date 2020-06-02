package cn.edu.nchu.software.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleEntity {
	private Integer articleID;
	private Integer userID;
	private Integer typeID;
	private Integer articleType;   //1-教程发布；2-代码征集；3-答疑解惑
	private String articleTitle;
	private String keyword;
	private String articleContext;
	private Date uploadTime;
	private Integer count;
}
