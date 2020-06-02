package cn.edu.nchu.software.service;

import java.util.List;

import cn.edu.nchu.software.entity.CodeEntity;
import cn.edu.nchu.software.util.Pages;

public interface CodeService {

	int insertCode(CodeEntity codeEntity);
	List<CodeEntity> listAllCode(Integer typeID,Pages pages);
	List<CodeEntity> listCodeByUserID(Integer userID,Pages pages);
	CodeEntity findCodeByCodeID(Integer codeID);
	void countSelfIncrement(Integer codeID);
	List<CodeEntity> listCodeByArchivingUserID(Integer userID,Pages pages);
	List<CodeEntity> listAllCodeByTypeIDCodeID(Integer typeID,Integer codeID);
}
