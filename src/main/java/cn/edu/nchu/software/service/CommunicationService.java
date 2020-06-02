package cn.edu.nchu.software.service;

import java.util.List;

import cn.edu.nchu.software.entity.CommunicationEntity;

public interface CommunicationService {

	int insertCommunication(CommunicationEntity communicationEntity);
	List<CommunicationEntity> listCommunicationByCommentID(Integer commentID);
}
