package cn.edu.nchu.software.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CommunicationEntity {

	private Integer communicationID;
	private Integer commentID;
	private Integer userID;
	private Integer replyUserID;
	private String communicationContext;
	private Date time;
}
