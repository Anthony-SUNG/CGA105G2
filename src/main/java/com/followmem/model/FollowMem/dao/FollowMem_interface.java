package com.followmem.model.FollowMem.dao;

import com.core.dao.CoreDao;
import com.followmem.model.FollowMem.pojo.FollowMem;

import java.util.List;



public interface FollowMem_interface extends CoreDao<FollowMem,Integer> {
    public void insert(FollowMem pojo);
    public void update(FollowMem pojo);
    public void delete(Integer memId1, Integer memId2);
    public void deleteById(Integer id);
    public FollowMem getById(Integer memId);
    public List<FollowMem> getAllByMemId1(Integer memId);
    public List<FollowMem> getAllByMemId1MemId2(Integer memId1, Integer memId2);
    public List<FollowMem> getAll();
}
