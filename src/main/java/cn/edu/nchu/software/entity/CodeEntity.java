package cn.edu.nchu.software.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CodeEntity {

	private Integer codeID;
	private Integer userID;
	private Integer typeID;
	private String codeTitle;
	private String keyword;
	private String codeDescribe;
	private String codeContext;
	private Date uploadTime;
	private Integer count;
	private String analysisResults;
}
