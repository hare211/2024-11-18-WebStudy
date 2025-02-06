package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static MusicDAO dao;
	
	public MusicDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static MusicDAO newInstance() {
		if (dao == null) {
			dao = new MusicDAO();
		}
		return dao;
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void disconnection() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 목록
	public List<MusicVO> musicListData(int page) {
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql = "SELECT mno, title, poster, idcrement, state, singer, num "
					   + "FROM (SELECT mno, title, poster, idcrement, state, singer, rownum as num "
					         + "FROM (SELECT /*+ INDEX_ASC(genie_music gm_mno_pk) */ mno, title, poster, idcrement, state, singer "
					               + "FROM genie_music)) "
					   + "WHERE num BETWEEN ? AND ?";
			
			ps = conn.prepareStatement(sql);
			
			int rowSize = 12;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				MusicVO vo = new MusicVO();
				
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setIdcrement(rs.getInt(4));
				vo.setState(rs.getString(5));
				vo.setSinger(rs.getString(6));
				
				list.add(vo);
			}
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return list;
	}
	// 총페이지
	public int getTotalPage() {
		int totalPage = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*) / 12.0) "
					   + "FROM genie_music";
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			totalPage = rs.getInt(1);
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return totalPage;
	}
	
	// 상세보기
	public MusicVO getMusicDetail(int mno) {
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql = "SELECT mno, title, singer, album, poster, idcrement, state, cno "
					   + "FROM genie_music "
					   + "WHERE mno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setAlbum(rs.getString(4));
			vo.setPoster(rs.getString(5));
			vo.setIdcrement(rs.getInt(6));
			vo.setState(rs.getString(7));
			
			int genre = rs.getInt(8);
			
			switch (genre) {
			case 1, 2:
				vo.setGenre("가요");
				break;
			case 3:
				vo.setGenre("POP");
				break;
			case 4:
				vo.setGenre("OST");
				break;
			case 5:
				vo.setGenre("트롯");
				break;
			case 6:
				vo.setGenre("JAZZ");
				break;
			case 7:
				vo.setGenre("클래식");
				break;
			default:
				vo.setGenre("VariousGenre");
			}
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return vo;
	}
	
	public MusicVO musicCookieData(int mno) {
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql = "SELECT mno, title, poster FROM genie_music WHERE mno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			vo.setMno(rs.getInt("mno"));
			vo.setTitle(rs.getString("title"));
			vo.setPoster(rs.getString("poster"));
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		
		return vo;
	}
	
	public List<MusicVO> getMusicGenreList(int cno, int page){
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			getConnection();
			String sql = "SELECT mno, title, poster, num "
					   + "FROM (SELECT mno, title, poster, rownum as num "
					         + "FROM (SELECT /*+ INDEX_ASC(genie_music gm_mno_pk) */ mno, title, poster "
					               + "FROM genie_music WHERE cno = ?)) "
					   + "WHERE num BETWEEN ? AND ?";
		
			int rowSize = 12;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cno);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				MusicVO vo = new MusicVO();
				
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return list;
	}
	
	public int getMusicGenreTotalPage(int cno) {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*) / 12.0) FROM genie_music WHERE cno = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cno);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			total = rs.getInt(1);
			
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnection();
		}
		return total;
	}
}