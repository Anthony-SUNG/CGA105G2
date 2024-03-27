package com.emp.model.Root.dao;

import com.core.dao.CoreDao;
import com.emp.model.Root.pojo.Root;

import java.util.List;




public interface RootDAO_interface extends CoreDao<Root,Integer> {
    public void insert(Root pojo);
    public void update(Root pojo);
    public void deleteById(Integer id);
    public Root getById(Integer id);
    public List<Root> getByRoot(String name);
    public List<Root> getAll();

}
