package cn.edu.nchu.software.service;


import cn.edu.nchu.software.entity.UserEntity;
import cn.edu.nchu.software.util.Pages;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Auther 范孝发
 * @Date 2019/5/2 17:15
 * @Version 1.0
 */
public interface UserService {
	
	UserEntity findUserByUsernamePasswordUsertype(String username,String password,Integer userType);
	
	UserEntity findUserByUserID(Integer userID);
	
	UserEntity findUserByUsername(String username);
	
	List<UserEntity> listAll(Pages pages);
    
	int deleteUser(Integer userID);
	    
	int insertUser(UserEntity userEntity);
	    
	int updateUser(UserEntity userEntity);
	
	int updatetUserKey(Integer userID,Integer state);
	
	List<UserEntity> multipleParametersQuery(String name,String username,Integer userType,Pages pages);
}
