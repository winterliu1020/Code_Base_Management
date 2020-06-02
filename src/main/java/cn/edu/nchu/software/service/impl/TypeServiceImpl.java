package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nchu.software.entity.TypeEntity;
import cn.edu.nchu.software.mapper.TypeMapper;
import cn.edu.nchu.software.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeMapper typeMapper;

	@Override
	public List<TypeEntity> listAll() {
		return typeMapper.listAll();
	}

	@Override
	public TypeEntity findTypeByTypeID(Integer typeID) {
		return typeMapper.findTypeByTypeID(typeID);
	}

}
