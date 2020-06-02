package cn.edu.nchu.software.entity;

import lombok.Data;

@Data
public class CodeAnalysisEntity {
	
	private String codeTitle;
	private String code;
	private String similarity;
	
	public CodeAnalysisEntity(String codeTitle, String code, String similarity) {
		super();
		this.codeTitle = codeTitle;
		this.code = code;
		this.similarity = similarity;
	}

}
