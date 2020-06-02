package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.edu.nchu.software.entity.ArchivingEntity;
import cn.edu.nchu.software.mapper.ArchivingMapper;
import cn.edu.nchu.software.service.ArchivingService;
import cn.edu.nchu.software.util.Pages;

@Service
public class ArchivingServiceImpl implements ArchivingService{
	
	@Autowired
	private ArchivingMapper archivingMapper;

	@Override
	public int insertArchiving(ArchivingEntity archivingEntity) {
		return archivingMapper.insertArchiving(archivingEntity);
	}

	@Override
	public ArchivingEntity findArchivingByUserIDCodeID(Integer userID, Integer codeID) {
		return archivingMapper.findArchivingByUserIDCodeID(userID, codeID);
	}

	@Override
	public int deleteArchivingByUserIDCodeID(Integer userID, Integer codeID) {
		return archivingMapper.deleteArchivingByUserIDCodeID(userID, codeID);
	}

	@Override
	public List<ArchivingEntity> listArchivingByUserID(Integer userID, Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		List<ArchivingEntity> list= archivingMapper.listArchivingByUserID(userID);
		pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
		pages.setPageBeginToEnd();
		return list;
	}

}
