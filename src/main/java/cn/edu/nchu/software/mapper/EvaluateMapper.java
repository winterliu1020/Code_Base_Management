package cn.edu.nchu.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.EvaluateEntity;


@Repository
public interface EvaluateMapper {

	int insertEvaluate(EvaluateEntity evaluateEntity);
	List<EvaluateEntity> listEvaluateByCodeID(Integer codeID);
	int countByGrade(@Param("codeID")Integer codeID,@Param("grade")Float grade);
	int listEvaluateCountByCodeID(Integer codeID);
}
