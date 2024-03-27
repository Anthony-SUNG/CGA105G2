package com.core.dao;


import com.core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface CoreDao<P, I> {
    void insert(P pojo);
    void update(P pojo);
    void deleteById(I id);
    P getById(I id);
    List<P> getAll();
    default Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
}
