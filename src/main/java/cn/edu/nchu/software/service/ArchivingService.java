package cn.edu.nchu.software.service;

import java.util.List;

import cn.edu.nchu.software.entity.ArchivingEntity;
import cn.edu.nchu.software.util.Pages;

public interface ArchivingService {
	
	int insertArchiving(ArchivingEntity archivingEntity);
	ArchivingEntity findArchivingByUserIDCodeID(Integer userID,Integer codeID);
	int deleteArchivingByUserIDCodeID(Integer userID,Integer codeID);
	List<ArchivingEntity> listArchivingByUserID(Integer userID,Pages pages);
}
