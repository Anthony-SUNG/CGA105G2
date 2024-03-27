package com.code.model.Code.dao;

import com.advertise.model.Advertise.pojo.Advertise;
import com.code.model.Code.pojo.Code;
import com.core.dao.CoreDao;

import java.util.List;

public interface CodeDAO_interface extends CoreDao<Code,Integer> {
    public void insert(Code pojo);
    public void update(Code pojo);
    public void deleteById(Integer id);
    public Code getById(Integer id);
    List<Integer> getCodeId(String codeNum, Integer storeId);
    List<Code> getBy(Integer id, String string);
    List<Integer> getBycodeNum(String codeNum, Integer storeId);
    public List<Code> getAll();
}
