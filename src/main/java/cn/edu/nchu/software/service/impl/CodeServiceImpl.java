package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.edu.nchu.software.entity.CodeEntity;
import cn.edu.nchu.software.mapper.CodeMapper;
import cn.edu.nchu.software.service.CodeService;
import cn.edu.nchu.software.util.Pages;

@Service
public class CodeServiceImpl implements CodeService{

	@Autowired
	private CodeMapper codeMapper;
	
	@Override
	public int insertCode(CodeEntity codeEntity) {
		return codeMapper.insertCode(codeEntity);
	}

	@Override
	public List<CodeEntity> listAllCode(Integer typeID,Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		List<CodeEntity> list= codeMapper.listAllCode(typeID);
		pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
		pages.setPageBeginToEnd();
		return list;
	}

	@Override
	public List<CodeEntity> listCodeByUserID(Integer userID,Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		List<CodeEntity> list= codeMapper.listCodeByUserID(userID);
		pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
		pages.setPageBeginToEnd();
		return list;
	}

	@Override
	public CodeEntity findCodeByCodeID(Integer codeID) {
		return codeMapper.findCodeByCodeID(codeID);
	}

	@Override
	public void countSelfIncrement(Integer codeID) {
		codeMapper.countSelfIncrement(codeID);
	}

	@Override
	public List<CodeEntity> listCodeByArchivingUserID(Integer userID, Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		List<CodeEntity> list= codeMapper.listCodeByArchivingUserID(userID);
		pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
		pages.setPageBeginToEnd();
		return list;
	}

	@Override
	public List<CodeEntity> listAllCodeByTypeIDCodeID(Integer typeID,Integer codeID) {
		return codeMapper.listAllCodeByTypeIDCodeID(typeID,codeID);
	}

}
