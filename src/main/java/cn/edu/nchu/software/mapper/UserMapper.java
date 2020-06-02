
package cn.edu.nchu.software.mapper;

import cn.edu.nchu.software.entity.UserEntity;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName StudentMapper
 * @Description TODO
 * @Auther 范孝发
 * @Date 2019/5/2 17:09
 * @Version 1.0
 */

@Repository
public interface UserMapper {
	
	 UserEntity findUserByUsernamePasswordUsertype(@Param("username") String username,@Param("password")String password,@Param("userType")Integer userType);

	 UserEntity findUserByUserID(Integer userID);
	 
	 UserEntity findUserByUsername(String username);
	 
	 List<UserEntity> listAll();
	    
	 int deleteUser(Integer userID);
	    
	 int insertUser(UserEntity userEntity);
	    
	 int updateUser(UserEntity userEntity);
	 
	 int updatetUserKey(@Param("userID")Integer userID,@Param("state")Integer state);
	 
	 List<UserEntity> multipleParametersQuery(@Param("name")String name,@Param("username")String username,@Param("userType")Integer userType);
    
}
