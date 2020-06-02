package cn.edu.nchu.software.service;

import java.util.List;

import cn.edu.nchu.software.entity.EvaluateEntity;

public interface EvaluateService {

	int insertEvaluate(EvaluateEntity evaluateEntity);
	List<EvaluateEntity> listEvaluateByCodeID(Integer codeID);
	int countByGrade(Integer codeID,Float grade);
	int count(Integer codeID);
	int countRecord(Integer codeID);
	int listEvaluateCountByCodeID(Integer codeID);
}
