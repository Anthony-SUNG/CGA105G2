package com.art.model.Article.dao.impl;

import com.art.model.Article.dao.ArtDAO_interface;
import com.art.model.Article.pojo.Article;
import com.core.common.Common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArtJDBCDAO implements ArtDAO_interface {
	
	static {
		  try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
	
	@Override
	public void insert(Article artVO) {
		String sql = 
				"INSERT INTO cga105g2.ARTICLE (MEM_ID, STORE_ID, ART_HEADER, ART_TEXT, ART_IMG, ART_SCORE) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setInt(1, artVO.getMemId());
			pstmt.setInt(2, artVO.getStoreId());
			pstmt.setString(3, artVO.getArtHeader());
			pstmt.setString(4, artVO.getArtText());
			pstmt.setBytes(5, artVO.getArtImg());
			pstmt.setInt(6, artVO.getArtScore());

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		} 
	}
		
		@Override
		public List<Article> getAll() {
			String sql = 
					"SELECT * FROM cga105g2.article";
			List<Article> list = new ArrayList<Article>();
			Article Article = null;

			ResultSet rs = null;

			try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					Article = new Article();
					Article.setArtId(rs.getInt("art_Id"));
					Article.setMemId(rs.getInt("mem_Id"));
					Article.setStoreId(rs.getInt("store_Id"));
					Article.setArtHeader(rs.getString("art_Header"));
					Article.setArtText(rs.getString("art_Text"));
					Article.setArtImg(rs.getBytes("art_Img"));
					Article.setArtTag(rs.getString("art_Tag"));
					Article.setArtStatus(rs.getInt("art_Status"));
					Article.setArtTime(rs.getTimestamp("art_Time"));
					Article.setArtRetime(rs.getTimestamp("art_Retime"));
					Article.setArtSumlike(rs.getInt("art_Sumlike"));
					Article.setArtScore(rs.getInt("art_Score"));
							
					list.add(Article);
				}

				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
			return list;
		}

		@Override
		public Article getByMemId(Integer memId) {
			String sql = 
					"SELECT * FROM cga105g2.article where MEM_ID = ?";
			Article Article = null;

			ResultSet rs = null;

			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))  {



				pstmt.setInt(1, memId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					Article = new Article();
					Article.setArtId(rs.getInt("art_Id"));
					Article.setMemId(rs.getInt("mem_Id"));
					Article.setStoreId(rs.getInt("store_Id"));
					Article.setArtHeader(rs.getString("art_Header"));
					Article.setArtText(rs.getString("art_Text"));
					Article.setArtImg(rs.getBytes("art_Img"));
					Article.setArtTag(rs.getString("art_Tag"));
					Article.setArtStatus(rs.getInt("art_Status"));
					Article.setArtTime(rs.getTimestamp("art_Time"));
					Article.setArtRetime(rs.getTimestamp("art_Retime"));
					Article.setArtSumlike(rs.getInt("art_Sumlike"));
					Article.setArtScore(rs.getInt("art_Score"));
				}

				
			}  catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
			} 
			return Article;
		}
		@Override
		public Article getByArtId(Integer artId) {
			String sql =
					"SELECT * FROM cga105g2.article where ART_ID = ?";
			Article Article = null;

			ResultSet rs = null;

			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))  {



				pstmt.setInt(1, artId);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					Article = new Article();
					Article.setArtId(rs.getInt("art_Id"));
					Article.setMemId(rs.getInt("mem_Id"));
					Article.setStoreId(rs.getInt("store_Id"));
					Article.setArtHeader(rs.getString("art_Header"));
					Article.setArtText(rs.getString("art_Text"));
					Article.setArtImg(rs.getBytes("art_Img"));
					Article.setArtTag(rs.getString("art_Tag"));
					Article.setArtStatus(rs.getInt("art_Status"));
					Article.setArtTime(rs.getTimestamp("art_Time"));
					Article.setArtRetime(rs.getTimestamp("art_Retime"));
					Article.setArtSumlike(rs.getInt("art_Sumlike"));
					Article.setArtScore(rs.getInt("art_Score"));
				}


			}  catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());

			}
			return Article;
		}

		public List<Article> getAllByMemId(Integer memId) {
			String sql = 
					"SELECT * FROM cga105g2.article where MEM_ID = ? order by ART_TIME DESC;";
			List<Article> list = new ArrayList<Article>();
			Article Article = null;

			ResultSet rs = null;

			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))  {

				pstmt.setInt(1, memId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					Article = new Article();
					Article.setArtId(rs.getInt("art_Id"));
					Article.setMemId(rs.getInt("mem_Id"));
					Article.setStoreId(rs.getInt("store_Id"));
					Article.setArtHeader(rs.getString("art_Header"));
					Article.setArtText(rs.getString("art_Text"));
					Article.setArtImg(rs.getBytes("art_Img"));
					Article.setArtTag(rs.getString("art_Tag"));
					Article.setArtStatus(rs.getInt("art_Status"));
					Article.setArtTime(rs.getTimestamp("art_Time"));
					Article.setArtRetime(rs.getTimestamp("art_Retime"));
					Article.setArtSumlike(rs.getInt("art_Sumlike"));
					Article.setArtScore(rs.getInt("art_Score"));

					list.add(Article);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			}
			return list;
		}

		@Override
		public List<Article> getAllByMemIdArtId(Integer memId,Integer artId) {
			String sql =
					"SELECT * FROM cga105g2.article where MEM_ID = ? and ART_ID = ?";
			List<Article> list = new ArrayList<Article>();
			Article Article = null;

			ResultSet rs = null;

			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))  {

				pstmt.setInt(1, memId);
				pstmt.setInt(2, artId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					Article = new Article();
					Article.setArtId(rs.getInt("art_Id"));
					Article.setMemId(rs.getInt("mem_Id"));
					Article.setStoreId(rs.getInt("store_Id"));
					Article.setArtHeader(rs.getString("art_Header"));
					Article.setArtText(rs.getString("art_Text"));
					Article.setArtImg(rs.getBytes("art_Img"));
					Article.setArtTag(rs.getString("art_Tag"));
					Article.setArtStatus(rs.getInt("art_Status"));
					Article.setArtTime(rs.getTimestamp("art_Time"));
					Article.setArtRetime(rs.getTimestamp("art_Retime"));
					Article.setArtSumlike(rs.getInt("art_Sumlike"));
					Article.setArtScore(rs.getInt("art_Score"));

					list.add(Article);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			} 
			return list;
		}

		public void update(Article Article) {
			String sql = 
					"UPDATE cga105g2.ARTICLE set MEM_ID= ?,STORE_ID= ?,ART_HEADER= ?, ART_TEXT= ?, ART_IMG= ?, ART_SCORE= ? where ART_ID = ?";

			try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

				pstmt.setInt(1, Article.getMemId());
				pstmt.setInt(2, Article.getStoreId());
				pstmt.setString(3, Article.getArtHeader());
				pstmt.setString(4, Article.getArtText());
				pstmt.setBytes(5, Article.getArtImg());
				pstmt.setInt(6, Article.getArtScore());
				pstmt.setInt(7, Article.getArtId());

				pstmt.executeUpdate();

			}  catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
			} 
		}

		@Override
		public void delete(Integer artId) {
			String sql = 
					"DELETE FROM cga105g2.ARTICLE where ART_ID = ?";

			try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
		            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))  {


				pstmt.setInt(1, artId);

				pstmt.executeUpdate();

				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			
			} 
		}
		public static void main(String[] args) {
			try(InputStream in = Files.newInputStream(Path.of("src/main/webapp/assets/images/men.png"))) { 
				byte[] buf = new byte[in.available()];
				in.read(buf);	//讀取圖片
				ArtJDBCDAO dao = new ArtJDBCDAO();
				Article Article = new Article();
				//新增
				Article.setMemId(7);
				Article.setStoreId(8);
				Article.setArtHeader("餐點");
				Article.setArtText("好吃好吃好吃好吃好吃好吃好吃好吃好吃好吃好吃");
				Article.setArtImg(buf);
				Article.setArtScore(5);
				dao.insert(Article);
				//刪除
//				dao.delete(8);
		
				//更改
//				Article.setArtId(6);
//				Article.setArtHeader("服務態度很好");
//				Article.setArtText("服務好服務好服務好服務好服務好服務好服務好服務好服務好服務好");
//				Article.setArtImg(buf);
//				Article.setArtTag(null);
//				Article.setArtStatus(0);
//				Article.setArtSumlike(0);
//				Article.setArtScore(3);
//				dao.update(Article);
				
//				查詢單一會員
				Article Article2 = dao.getByMemId(2);
//				System.out.println("ART_ID:"+ Article2.getArtId());
//				System.out.println("MEM_ID:"+ Article2.getMemId());
//				System.out.println("STORE_ID:"+ Article2.getStoreId());
//				System.out.println("ART_HEADER:"+ Article2.getArtHeader());
//				System.out.println("ART_TEXT:"+ Article2.getArtText());
//				System.out.println("ART_IMG:"+ Article2.getArtImg());
//				System.out.println("ART_TAG:"+ Article2.getArtTag());
//				System.out.println("ART_TIME:"+ Article2.getArtTime());
//				System.out.println("ART_STATUS:"+ Article2.getArtStatus());
//				System.out.println("ART_TIME:"+ Article2.getArtTime());
//				System.out.println("ART_RETIME:"+ Article2.getArtRetime());
//				System.out.println("ART_SUMLIKE:"+ Article2.getArtSumlike());
//				System.out.println("ART_SCORE:"+ Article2.getArtScore());
//				System.out.println("---------------------");
				
//				查詢某會員發過的全部文
//				List<Article> list = dao.getAllByMemId(1);
//				for (Article a : list) {
//					System.out.println("ART_ID:"+ a.getArtId());
//					System.out.println("MEM_ID:"+ a.getMemId());
//					System.out.println("STORE_ID:"+ a.getStoreId());
//					System.out.println("ART_HEADER:"+ a.getArtHeader());
//					System.out.println("ART_TEXT:"+ a.getArtText());
//					System.out.println("ART_IMG:"+ a.getArtImg());
//					System.out.println("ART_TAG:"+ a.getArtTag());
//					System.out.println("ART_TIME:"+ a.getArtTime());
//					System.out.println("ART_STATUS:"+ a.getArtStatus());
//					System.out.println("ART_TIME:"+ a.getArtTime());
//					System.out.println("ART_RETIME:"+ a.getArtRetime());
//					System.out.println("ART_SUMLIKE:"+ a.getArtSumlike());
//					System.out.println("ART_SCORE:"+ a.getArtScore());
//					System.out.println("================");
//				}
//				
				
				//全部查詢
//				List<Article> list2 = dao.getAll();
//				for (Article e : list2) {
//					System.out.print(e.getArtId() + ",");
//					System.out.print(e.getMemId() + ",");
//					System.out.print(e.getStoreId() + ",");
//					System.out.print(e.getArtHeader() + ",");
//					System.out.print(e.getArtText()+ ",");
//					System.out.print(e.getArtImg() + ",");
//					System.out.print(e.getArtTag() + ",");
//					System.out.print(e.getArtTime() + ",");
//					System.out.print(e.getArtStatus() + ",");
//					System.out.print(e.getArtTime() + ",");
//					System.out.print(e.getArtRetime() + ",");
//					System.out.print(e.getArtSumlike() + ",");
//					System.out.print(e.getArtScore());
//					System.out.println("---------------------");
//				}
				
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	
}
