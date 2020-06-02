package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nchu.software.entity.EvaluateEntity;
import cn.edu.nchu.software.mapper.EvaluateMapper;
import cn.edu.nchu.software.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService{
	
	@Autowired
	private EvaluateMapper evaluateMapper;

	@Override
	public int insertEvaluate(EvaluateEntity evaluateEntity) {
		return evaluateMapper.insertEvaluate(evaluateEntity);
	}

	@Override
	public List<EvaluateEntity> listEvaluateByCodeID(Integer codeID) {
		return evaluateMapper.listEvaluateByCodeID(codeID);
	}

	@Override
	public int countByGrade(Integer codeID,Float grade) {
		return evaluateMapper.countByGrade(codeID,grade);
	}

	@Override
	public int count(Integer codeID) {
		return evaluateMapper.countByGrade(codeID,new Float(1)) + 2*evaluateMapper.countByGrade(codeID,new Float(2))
		+ 3*evaluateMapper.countByGrade(codeID,new Float(3)) + 4*evaluateMapper.countByGrade(codeID,new Float(4))
		+ 5*evaluateMapper.countByGrade(codeID,new Float(5));
	}
	
	@Override
	public int countRecord(Integer codeID) {
		return evaluateMapper.countByGrade(codeID,new Float(1)) + evaluateMapper.countByGrade(codeID,new Float(2))
		+ evaluateMapper.countByGrade(codeID,new Float(3)) + evaluateMapper.countByGrade(codeID,new Float(4))
		+ evaluateMapper.countByGrade(codeID,new Float(5));
	}

	@Override
	public int listEvaluateCountByCodeID(Integer codeID) {
		return evaluateMapper.listEvaluateCountByCodeID(codeID);
	}

}
