package cn.edu.nchu.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.CodeEntity;

@Repository
public interface CodeMapper {

	int insertCode(CodeEntity codeEntity);
	List<CodeEntity> listAllCode(Integer typeID);
	List<CodeEntity> listCodeByUserID(Integer userID);
	CodeEntity findCodeByCodeID(Integer codeID);
	void countSelfIncrement(Integer codeID);
	List<CodeEntity> listCodeByArchivingUserID(Integer userID);
	List<CodeEntity> listAllCodeByTypeIDCodeID(@Param("typeID")Integer typeID,@Param("codeID")Integer codeID);
}
