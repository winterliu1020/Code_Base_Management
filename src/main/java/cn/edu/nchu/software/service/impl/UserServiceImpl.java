package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.edu.nchu.software.entity.UserEntity;
import cn.edu.nchu.software.mapper.UserMapper;
import cn.edu.nchu.software.service.UserService;
import cn.edu.nchu.software.util.Pages;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserEntity> listAll(Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
        List<UserEntity> list = userMapper.listAll();
        pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
        pages.setPageBeginToEnd();
        return list;
	}
	@Override
	public int deleteUser(Integer userID) {
		int num = userMapper.deleteUser(userID);
		return num;
	}

	@Override
	public int insertUser(UserEntity userEntity) {
		int num = userMapper.insertUser(userEntity);
		return num;
	}

	@Override
	public int updateUser(UserEntity userEntity) {
		int num = userMapper.updateUser(userEntity);
		return num;
	}

	@Override
	public UserEntity findUserByUsernamePasswordUsertype(String username, String password, Integer userType) {
		return userMapper.findUserByUsernamePasswordUsertype(username, password, userType);
	}
	@Override
	public UserEntity findUserByUserID(Integer userID) {
		return userMapper.findUserByUserID(userID);
	}
	@Override
	public int updatetUserKey(Integer userID, Integer state) {
		return userMapper.updatetUserKey(userID, state);
	}
	@Override
	public List<UserEntity> multipleParametersQuery(String name, String username, Integer userType, Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
        List<UserEntity> list = userMapper.multipleParametersQuery(name, username, userType);
        pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
        pages.setPageBeginToEnd();
        return list;
	}
	@Override
	public UserEntity findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}

}
