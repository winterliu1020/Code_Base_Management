package cn.edu.nchu.software.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nchu.software.entity.MyLikeEntity;
import cn.edu.nchu.software.mapper.MyLikeMapper;
import cn.edu.nchu.software.service.MyLikeService;

@Service
public class MyLikeServiceImpl implements MyLikeService{

	@Autowired
	private MyLikeMapper myLikeMapper;
	
	@Override
	public int insertMyLike(MyLikeEntity myLikeEntity) {
		return myLikeMapper.insertMyLike(myLikeEntity);
	}

	@Override
	public Integer countByCommentID(Integer commentID) {
		return myLikeMapper.countByCommentID(commentID);
	}

	@Override
	public MyLikeEntity findMyLike(Integer userID, Integer commentID) {
		return myLikeMapper.findMyLike(userID, commentID);
	}

	@Override
	public int deleteMyLike(MyLikeEntity myLikeEntity) {
		return myLikeMapper.deleteMyLike(myLikeEntity);
	}

}
