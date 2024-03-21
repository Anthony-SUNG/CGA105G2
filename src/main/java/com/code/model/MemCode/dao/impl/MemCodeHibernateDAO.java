package com.code.model.MemCode.dao.impl;


import com.code.model.MemCode.dao.MemCodeDAO_interface;
import com.code.model.MemCode.pojo.MemCode;
import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemCodeHibernateDAO extends Common implements MemCodeDAO_interface {
    @Override
    public void insert(MemCode pojo) {
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            session.persist(pojo);
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        logger.error(ErrorTitle.UNDEF_TITLE.getTitle(getClass().getName()));
    }

    @Override
    public void delete(MemCode pojo) {
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            session.remove(pojo);
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle(), e);
        }

    }

    @Override
    public void update(MemCode pojo) {
        logger.error(ErrorTitle.UNDEF_TITLE.getTitle(getClass().getName()));
    }

    @Override
    public void update(MemCode pojo1, MemCode pojo2) {
        String sql = "update cga105g2.mem_code set CODE_ID=?,MEM_ID=? where CODE_ID=? and MEM_ID=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, pojo2.getCodeId());
            stmt.setInt(2, pojo2.getMemId());
            stmt.setInt(3, pojo1.getCodeId());
            stmt.setInt(4, pojo1.getMemId());
            stmt.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException se) {
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(sql), se);
        }
    }

    @Override
    public MemCode getById(Integer id) {
        return null;
    }


    @Override
    public List<MemCode> getByPK(Integer id, String pk) {
        List<MemCode> list = new ArrayList<>();
        //Code是VO名稱
        String hql = "From MemCode WHERE";
        if (pk.equals("codeId")) {
            hql += " codeId=" + id;
        } else {
            hql += " memId=" + id;
        }
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, MemCode.class).getResultList();
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(), e);
        }
        return list;
    }

    @Override
    public List<MemCode> getAll() {
        List<MemCode> list = new ArrayList<>();
        //Code是VO名稱
        final String hql = "From MemCode";
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, MemCode.class).getResultList();
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(), e);
        }
        return list;
    }


    public static void main(String[] args) {
        MemCodeHibernateDAO dao = new MemCodeHibernateDAO();
//        //getByPK
        //(1)codeId:查詢有優惠券id=1的會員有哪些
        List<MemCode> list1 = dao.getByPK(3, "codeId");
        logger.info("有此優惠券的會員如下:");
        for (MemCode e : list1) logger.info(e.getMemId() + "號、");
        logger.info("*******************************************");
        //(2)memId:查詢會員id=5的會員有哪些優惠券id
        List<MemCode> list2 = dao.getByPK(1, "memId");
        logger.info("此會員擁有的優惠券如下:");
        for (MemCode e : list2) logger.info(e.getCodeId() + "號、");
        logger.info("*******************************************");
    }
}
