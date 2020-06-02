package cn.edu.nchu.software.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.edu.nchu.software.entity.ArticleEntity;
import cn.edu.nchu.software.mapper.ArticleMapper;
import cn.edu.nchu.software.service.ArticleService;
import cn.edu.nchu.software.util.Pages;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public int insertArticle(ArticleEntity articleEntity) {
		return articleMapper.insertArticle(articleEntity);
	}

	@Override
	public List<ArticleEntity> listArticleByArticleType(Integer articleType,Integer typeID,Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		List<ArticleEntity> list= articleMapper.listArticleByArticleType(articleType,typeID);
		pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
		pages.setPageBeginToEnd();
		return list;
	}

	@Override
	public List<ArticleEntity> listArticleByArticleTypeUserID(Integer articleType, Integer userID,Pages pages) {
		Page page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		List<ArticleEntity> list= articleMapper.listArticleByArticleTypeUserID(articleType, userID);
		pages.setPageNumAll((int)Math.ceil(page.getTotal()*1.0/pages.getPageSize()));
		pages.setPageBeginToEnd();
		return list;
	}

	@Override
	public ArticleEntity findArticleByArticleID(Integer articleID) {
		return articleMapper.findArticleByArticleID(articleID);
	}

	@Override
	public void countSelfIncrement(Integer articleID) {
		articleMapper.countSelfIncrement(articleID);
	}

}
