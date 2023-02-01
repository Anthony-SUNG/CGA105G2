package com.advertise.model.Advertise.dao.impl;


import com.advertise.model.Advertise.dao.Advertise_interface;
import com.advertise.model.Advertise.pojo.Advertise;
import com.core.common.Common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdvertiseJDBCDAO implements Advertise_interface {

		
		@Override
		public void insert(Advertise pojo) {
			String sql = "INSERT INTO cga105g2.ADVERTISE (STORE_ID, EMP_ID, ADV_STATUS, ADV_IMG, ADV_TEXT, ADV_STIME, ADV_NTIME) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				 PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
				pstmt.setInt(1, pojo.getStoreId());
				pstmt.setInt(2, pojo.getEmpId());
				pstmt.setInt(3, pojo.getAdvStatus());
				pstmt.setBytes(4, pojo.getAdvImg());
				pstmt.setString(5, pojo.getAdvText());
				pstmt.setDate(6, pojo.getAdvStime());
				pstmt.setDate(7, pojo.getAdvNtime());
				pstmt.executeUpdate();
			}  catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
		}
		
		@Override
		public List<Advertise> getAll() {
			String sql = "SELECT * FROM cga105g2.ADVERTISE";
			List<Advertise> list = new ArrayList<Advertise>();
			Advertise Advertise = null;
			ResultSet rs = null;
			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Advertise = new Advertise();
					Advertise.setAdvId(rs.getInt("ADV_ID"));
					Advertise.setStoreId(rs.getInt("STORE_ID"));
					Advertise.setEmpId(rs.getInt("EMP_ID"));
					Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
					Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
					Advertise.setAdvText(rs.getString("ADV_TEXT"));
					Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
					Advertise.setAdvStime(rs.getDate("ADV_STIME"));
					Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
					list.add(Advertise);
				}

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
			return list;
		}

		public List<Advertise> getByStatus(Integer advStatus) {
			String sql = "SELECT * FROM cga105g2.ADVERTISE where ADV_STATUS = ?";
			List<Advertise> list = new ArrayList<Advertise>();
			Advertise Advertise = null;
			ResultSet rs = null;
			try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
				pstmt.setInt(1, advStatus);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Advertise = new Advertise();
					Advertise.setAdvId(rs.getInt("ADV_ID"));
					Advertise.setStoreId(rs.getInt("STORE_ID"));
					Advertise.setEmpId(rs.getInt("EMP_ID"));
					Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
					Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
					Advertise.setAdvText(rs.getString("ADV_TEXT"));
					Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
					Advertise.setAdvStime(rs.getDate("ADV_STIME"));
					Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
					list.add(Advertise);
				}
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
			return list;
		}
		
		@Override
		public Advertise getByAdvId(Integer advId) {
			String sql = "SELECT * FROM cga105g2.ADVERTISE where ADV_ID = ?";
			Advertise Advertise = null;
			ResultSet rs = null;
			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				pstmt.setInt(1, advId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Advertise = new Advertise();
					Advertise.setAdvId(rs.getInt("ADV_ID"));
					Advertise.setStoreId(rs.getInt("STORE_ID"));
					Advertise.setEmpId(rs.getInt("EMP_ID"));
					Advertise.setAdvStatus(rs.getInt("ADV_STATUS"));
					Advertise.setAdvImg(rs.getBytes("ADV_IMG"));
					Advertise.setAdvText(rs.getString("ADV_TEXT"));
					Advertise.setAdvTime(rs.getTimestamp("ADV_TIME"));
					Advertise.setAdvStime(rs.getDate("ADV_STIME"));
					Advertise.setAdvNtime(rs.getDate("ADV_NTIME"));
				}
			}  catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
			return Advertise;
		}

		public void update(Advertise Advertise) {
			Connection con = null;
			PreparedStatement pstmt = null;
			Advertise Advertise_new = Advertise;
			Advertise Advertise_old = new Advertise();
			String UPDATE = "UPDATE cga105g2.ADVERTISE set EMP_ID=?, ADV_STATUS=? where ADV_ID = ?";
			try {
				con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				pstmt = con.prepareStatement(UPDATE, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				Advertise_old = getByAdvId(Advertise_new.getAdvId());
				pstmt.setInt(1, Advertise_old.getEmpId());
				pstmt.setInt(2, Advertise_old.getAdvStatus());
				pstmt.setInt(3, Advertise_old.getAdvId());
				if (Advertise_new.getEmpId()!=null){
	                pstmt.setInt(1,Advertise_new.getEmpId());
	            };
	            if (Advertise_new.getAdvStatus()!=null){
	                pstmt.setInt(2,Advertise_new.getAdvStatus());
	            }
				pstmt.executeUpdate();
			}  catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}
		
	    public void failUpdate(Integer advStatus){
	        long miliseconds = System.currentTimeMillis();
	        Date today=new Date(miliseconds);
	        for (Advertise e:getByStatus(advStatus)){
	            if (e.getAdvNtime().before(today)){ //看日期是否大於今天
	                Advertise Advertise_old = new Advertise();
	                Advertise_old.setAdvId(e.getAdvId());
	                Advertise_old.setEmpId(e.getEmpId());
	                Advertise_old.setAdvStatus(3);
	                update(Advertise_old);
	            }
	        }
	    }



	@Override
		public void delete(Integer advId) {
			String sql = "DELETE FROM cga105g2.ADVERTISE where ADV_ID = ?";
			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
				pstmt.setInt(1, advId);
				pstmt.executeUpdate();
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
		}
	
	
	
	
	
		public static void main(String[] args) {
			AdvertiseJDBCDAO dao = new AdvertiseJDBCDAO();
//			try(InputStream in = Files.newInputStream(Path.of("images/men.png"))) {
//				byte[] buf = new byte[in.available()];
//				in.read(buf);//讀取圖片
//				AdvertiseJDBCDAO dao = new AdvertiseJDBCDAO();
				Advertise Advertise = new Advertise();
				
//				Advertise.setStoreId(77);
//				Advertise.setEmpId(2);
//				Advertise.setAdvStatus(2);
//				Advertise.setAdvImg(buf);
//				Advertise.setAdvText("一張很大很大的圖");
//				Advertise.setAdvStime(Date.valueOf("2022-12-11"));
//				Advertise.setAdvNtime(Date.valueOf("2023-01-11"));
//				dao.insert(Advertise);	//新增
				
//				dao.delete(7);
				
				//getbyId
				Advertise = dao.getByAdvId(1);
                System.out.println("ADV_ID:"+Advertise.getAdvId());
                System.out.println("STORE_ID:"+Advertise.getStoreId());
                System.out.println("EMP_ID:"+Advertise.getEmpId());
                System.out.println("ADV_STATUS:"+Advertise.getAdvStatus());
                System.out.println("ADV_IMG:"+Advertise.getAdvImg());
                System.out.println("ADVE_TEXT:"+Advertise.getAdvText());
                System.out.println("ADV_TIME:"+Advertise.getAdvTime());
                System.out.println("ADV_STIME:"+Advertise.getAdvStime());
                System.out.println("ADV_NTIME:"+Advertise.getAdvNtime());
				
		        //getall
//	            List<Advertise> list=dao.getByStatus(1);	
//	            for(Advertise e : list){
//	                System.out.println("ADV_ID:"+e.getAdvId());
//	                System.out.println("STORE_ID:"+e.getStoreId());
//	                System.out.println("EMP_ID:"+e.getEmpId());
//	                System.out.println("ADV_STATUS:"+e.getAdvStatus());
//	                System.out.println("ADV_IMG:"+e.getAdvImg());
//	                System.out.println("ADVE_TEXT:"+e.getAdvText());
//	                System.out.println("ADV_TIME:"+e.getAdvTime());
//	                System.out.println("ADV_STIME:"+e.getAdvStime());
//	                System.out.println("ADV_NTIME:"+e.getAdvNtime());
//	                System.out.println("======================================");
//	            }
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}


			//修改
//			dao.update(new Advertise(1,3));
//			dao.update(new Advertise(1,3,2));
			
			
		}
}
