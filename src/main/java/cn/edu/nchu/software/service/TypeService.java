package cn.edu.nchu.software.service;

import java.util.List;

import cn.edu.nchu.software.entity.TypeEntity;

public interface TypeService {

	List<TypeEntity> listAll();
	TypeEntity findTypeByTypeID(Integer typeID);
}
