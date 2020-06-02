package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nchu.software.entity.CommunicationEntity;
import cn.edu.nchu.software.mapper.CommunicationMapper;
import cn.edu.nchu.software.service.CommunicationService;

@Service
public class CommunicationServiceImpl implements CommunicationService{
	
	@Autowired
	private CommunicationMapper communicationMapper;

	@Override
	public int insertCommunication(CommunicationEntity communicationEntity) {
		return communicationMapper.insertCommunication(communicationEntity);
	}

	@Override
	public List<CommunicationEntity> listCommunicationByCommentID(Integer commentID) {
		return communicationMapper.listCommunicationByCommentID(commentID);
	}

}
