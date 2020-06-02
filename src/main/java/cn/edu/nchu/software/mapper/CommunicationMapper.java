package cn.edu.nchu.software.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.CommunicationEntity;

@Repository
public interface CommunicationMapper {

	int insertCommunication(CommunicationEntity communicationEntity);
	List<CommunicationEntity> listCommunicationByCommentID(Integer commentID);
}
