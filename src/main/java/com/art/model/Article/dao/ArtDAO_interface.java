package com.art.model.Article.dao;

import com.art.model.Article.pojo.Article;
import com.core.dao.CoreDao;

import java.util.List;


public interface ArtDAO_interface extends CoreDao<Article,Integer> {
    
	public void insert(Article pojo);
    public void update(Article pojo);
    public void deleteById(Integer artId);
    public Article getById(Integer artId);
    public List<Article> getAll();
    public Article getByMemId(Integer memId);
    public List<Article> getAllByMemId(Integer memId);
    public List<Article> getAllByStoreId(Integer storeId);
    public List<Article> getAllByMemIdStoreId(Integer memId, Integer storeId); //盟鎮的
    List<Article> getAllByMemIdArtId(Integer memId, Integer artId);
}
