package cn.edu.nchu.software.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.TypeEntity;

@Repository
public interface TypeMapper {

	List<TypeEntity> listAll();
	TypeEntity findTypeByTypeID(Integer typeID);
}
