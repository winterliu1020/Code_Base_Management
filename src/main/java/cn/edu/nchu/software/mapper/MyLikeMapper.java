package cn.edu.nchu.software.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.MyLikeEntity;

@Repository
public interface MyLikeMapper {

	int insertMyLike(MyLikeEntity myLikeEntity);
	Integer countByCommentID(Integer commentID);
	MyLikeEntity findMyLike(@Param("userID")Integer userID,@Param("commentID")Integer commentID);
	int deleteMyLike(MyLikeEntity myLikeEntity);
}
