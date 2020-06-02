package cn.edu.nchu.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.ArchivingEntity;

@Repository
public interface ArchivingMapper {

	int insertArchiving(ArchivingEntity archivingEntity);
	ArchivingEntity findArchivingByUserIDCodeID(@Param("userID")Integer userID,@Param("codeID")Integer codeID);
	int deleteArchivingByUserIDCodeID(@Param("userID")Integer userID,@Param("codeID")Integer codeID);
	List<ArchivingEntity> listArchivingByUserID(Integer userID);
}
