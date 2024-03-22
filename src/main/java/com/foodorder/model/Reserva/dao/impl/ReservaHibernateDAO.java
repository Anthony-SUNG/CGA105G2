package com.foodorder.model.Reserva.dao.impl;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.core.util.HibernateUtil;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.pojo.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ReservaHibernateDAO extends Common implements ReservaDAO_interface {

    @Override
    public void insert(Reserva reservaVO) {
        if (reservaVO.getCodeId() != null) {
            //宣告
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            //開啟資料庫
            Session session = sessionFactory.openSession();
            try {
                //開啟交易
                Transaction tx = session.beginTransaction();
                //交易物件
                NativeQuery<?> nativeQuery = session.createNativeQuery("INSERT INTO reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES(:STORE_ID, :MEM_ID, :REN_NAME, :REN_PHONE, :REN_TIME, :REN_DATE, :REN_HEADCOUNT, :CODE_ID, :REN_PRICE, :REN_FPRICE)")
                        .setParameter("STORE_ID", reservaVO.getStoreId())
                        .setParameter("MEM_ID", reservaVO.getMemId())
                        .setParameter("REN_NAME", reservaVO.getRenName())
                        .setParameter("REN_PHONE", reservaVO.getRenPhone())
                        .setParameter("REN_TIME", reservaVO.getRenTime())
                        .setParameter("REN_DATE", reservaVO.getRenDate())
                        .setParameter("REN_HEADCOUNT", reservaVO.getRenHeadcount())
                        .setParameter("CODE_ID", reservaVO.getCodeId())
                        .setParameter("REN_PRICE", reservaVO.getRenPrice())
                        .setParameter("REN_FPRICE", reservaVO.getRenFprice());
                nativeQuery.executeUpdate();
                //確認送出交易
                tx.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                logger.error(ErrorTitle.INSERT_TITLE.getTitle(session.toString()), e);
            }
        }

        if (reservaVO.getCodeId() == null) {
            //宣告
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            //開啟資料庫
            Session session = sessionFactory.openSession();
            try {
                //開啟交易
                Transaction tx = session.beginTransaction();
                //交易物件
                NativeQuery<?> nativeQuery = session.createNativeQuery("insert into reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) values(:STORE_ID, :MEM_ID, :REN_NAME, :REN_PHONE, :REN_TIME, :REN_DATE, :REN_HEADCOUNT, :REN_PRICE, :REN_FPRICE)")
                        .setParameter("STORE_ID", reservaVO.getStoreId())
                        .setParameter("MEM_ID", reservaVO.getMemId())
                        .setParameter("REN_NAME", reservaVO.getRenName())
                        .setParameter("REN_PHONE", reservaVO.getRenPhone())
                        .setParameter("REN_TIME", reservaVO.getRenTime())
                        .setParameter("REN_DATE", reservaVO.getRenDate())
                        .setParameter("REN_HEADCOUNT", reservaVO.getRenHeadcount())
                        .setParameter("REN_PRICE", reservaVO.getRenPrice())
                        .setParameter("REN_FPRICE", reservaVO.getRenFprice());
                nativeQuery.executeUpdate();
                //確認送出交易
                tx.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                logger.error(ErrorTitle.INSERT_TITLE.getTitle(session.toString()), e);
            }
        }

    }

    @Override
    public void update(Reserva reservaVO) {
        Reserva reserva_new = reservaVO;
        Reserva reserva_old = getById(reservaVO.getRenId());
        if (reserva_new.getRenStatus() != null) {
            reserva_old.setRenStatus(reserva_new.getRenStatus());
        }
        if (reserva_new.getRenTable() != null) {
            reserva_old.setRenTable(reserva_new.getRenTable());
        }
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE reserva set REN_STATUS= :REN_STATUS, REN_TABLE=:REN_TABLE, REN_DATE=:REN_DATE where REN_ID = :REN_ID")
                    .setParameter("REN_STATUS", reserva_old.getRenStatus())
                    .setParameter("REN_TABLE", reserva_old.getRenTable())
                    .setParameter("REN_DATE", reserva_old.getRenDate())
                    .setParameter("REN_ID", reserva_old.getRenId());
            nativeQuery.executeUpdate();
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.UPDATE_TITLE.getTitle(session.toString()), e);
        }

    }

    @Override
    public Reserva getById(Integer id) {
        Reserva reserva = new Reserva();
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            reserva = session.get(Reserva.class, id);
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(session.toString()), e);
        }
        return reserva;
    }

    @Override
    public List<Reserva> getAll() {
        List<Reserva> list = new ArrayList<>();
        //Code是VO名稱
        final String hql = "From Reserva ORDER BY id";
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Reserva.class).getResultList();
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(session.toString()), e);
        }
        return list;
    }

    public List<Reserva> getByStatus(Integer renStatus) {
        List<Reserva> list = new ArrayList<>();
        //Code是VO名稱
        final String hql = "From Reserva WHERE renStatus=" + renStatus;
        //宣告
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session = sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx = session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Reserva.class).getResultList();
            //確認送出交易
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(ErrorTitle.SELECT_TITLE.getTitle(session.toString()), e);
        }
        return list;
    }

    @Override
    public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertWithReservaDetails(Reserva reservaVO, List list) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateRenstatusByRenid(Integer renid, Integer renstatus) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Reserva> getBymemId(Integer memid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Reserva> getBystoreId(Integer storeid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus) {
        // TODO Auto-generated method stub
        return null;
    }


}
