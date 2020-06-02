package cn.edu.nchu.software.service;

import java.util.List;

import cn.edu.nchu.software.entity.ArticleEntity;
import cn.edu.nchu.software.util.Pages;

public interface ArticleService {
	
	int insertArticle(ArticleEntity articleEntity);
	List<ArticleEntity> listArticleByArticleType(Integer articleType,Integer typeID,Pages pages);
	List<ArticleEntity> listArticleByArticleTypeUserID(Integer articleType,Integer userID,Pages pages);
	ArticleEntity findArticleByArticleID(Integer articleID);
	void countSelfIncrement(Integer articleID);
}
