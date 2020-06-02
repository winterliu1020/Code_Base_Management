package cn.edu.nchu.software.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ArchivingEntity {
	
	private Integer archivingID;
	private Integer userID;
	private Integer codeID;
	private String remarks;
	private Date archivingTime;
}
