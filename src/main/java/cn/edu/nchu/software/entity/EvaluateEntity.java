package cn.edu.nchu.software.entity;

import java.util.Date;

import lombok.Data;

@Data
public class EvaluateEntity {
	
	private Integer evaluateID;
	private Integer codeID;
	private Integer userID;
	private String evaluateContext;
	private Float grade;
	private Date evaluateTime;

}
