package cn.edu.nchu.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.nchu.software.entity.ArticleEntity;

@Repository
public interface ArticleMapper {
	
	int insertArticle(ArticleEntity articleEntity);
	List<ArticleEntity> listArticleByArticleType(@Param("articleType")Integer articleType,@Param("typeID")Integer typeID);
	List<ArticleEntity> listArticleByArticleTypeUserID(@Param("articleType")Integer articleType,@Param("userID")Integer userID);
	ArticleEntity findArticleByArticleID(Integer articleID);
	void countSelfIncrement(Integer articleID);
}
