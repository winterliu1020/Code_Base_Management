package cn.edu.nchu.software.service;

import cn.edu.nchu.software.entity.MyLikeEntity;

public interface MyLikeService {

	int insertMyLike(MyLikeEntity myLikeEntity);
	Integer countByCommentID(Integer commentID);
	MyLikeEntity findMyLike(Integer userID,Integer commentID);
	int deleteMyLike(MyLikeEntity myLikeEntity);
}
