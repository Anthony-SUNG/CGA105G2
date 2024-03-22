package com.foodorder.model.Meal.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.core.util.HibernateUtil;
import com.foodorder.model.Meal.dao.MealDAO_interface;
import com.foodorder.model.Meal.pojo.Meal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;


public class MealHibernateDAO extends Common implements MealDAO_interface {

	@Override
	public void insert(Meal mealVO) {
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            session.persist(mealVO);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(session.toString()), e);
        }

	}

	@Override
	public void update(Integer id, Integer status) {
		
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE cga105g2.meal set MEAL_STATUS=:MEAL_STATUS where MEAL_ID =:MEAL_ID")
            		.setParameter("MEAL_STATUS", status)
            		.setParameter("MEAL_ID", id);
            nativeQuery.executeUpdate();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(session.toString()), e);
        }
	}

	@Override
	public List<Meal> getAll() {
        List<Meal> list=new ArrayList<Meal>();
        //Code是VO名稱
        final String hql="From Meal ORDER BY id";
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Meal.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(session.toString()), e);
        }
        return list;
		
	}

	@Override
	public List<Meal> getByStoreIdStatus(Integer id, Integer status) {
		List<Meal> list = new ArrayList<Meal>();
		String sql="select * from cga105g2.meal where";
		String where = null;
		if (status == 0) {
			where = " STORE_ID=:STORE_ID and MEAL_STATUS=0;";
		} else if (status == 1) {
			where = " STORE_ID=:STORE_ID and MEAL_STATUS=1;";
		}
		sql = sql + where;
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<Meal> nativeQuery = session.createNativeQuery(sql, Meal.class)
            		.setParameter("STORE_ID", id);
            list = nativeQuery.list();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(session.toString()), e);
        }
        return list;

	}

	@Override
	public Meal getByMealId(Integer id) {
		Meal meal = new Meal();
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            meal = session.get(Meal.class, id);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(session.toString()), e);
        }
        return meal;
	}

	@Override
	public void MealUpdateCommit(Meal mealVO, Integer mealid) {
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE cga105g2.meal set MEAL_STATUS=:MEAL_STATUS where MEAL_ID =:MEAL_ID")
            		.setParameter("MEAL_STATUS", 0)
            		.setParameter("MEAL_ID", mealid);
            nativeQuery.executeUpdate();
            session.persist(mealVO);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(session.toString()), e);
        }
	}

}
