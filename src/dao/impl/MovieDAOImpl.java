package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import db.DBCon;

public class MovieDAOImpl implements MovieDAO {
	private static String selectMovieList = "select * from movie_info order by mi_num desc";
	private static String selectMovieByNum = "select * from movie_info where mi_num=?";
	private static String insertMovie = "insert into movie_info( " +
			" mi_num,mi_name,mi_year,mi_national,mi_vendor,mi_director) " +
			" values(seq_mi_num.nextval,?,?,?,?,?) ";
	private static String deleteMovie = "delete from movie_info where mi_num=?";
	
	@Override
	public List<Map<String, String>> selectMovieList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectMovieList);
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> movieList = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> movie = new HashMap<>();
				movie.put("miNum",rs.getString("mi_num"));
				movie.put("miName",rs.getString("mi_name"));
				movie.put("miYear",rs.getString("mi_year"));
				movie.put("miNational",rs.getString("mi_national"));
				movie.put("miVendor",rs.getString("mi_vendor"));
				movie.put("miDirector",rs.getString("mi_director"));
				movieList.add(movie);
			}
			return movieList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}
	
	public Map<String,String> selectMovieByNum(int miNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectMovieByNum);
			ps.setInt(1, miNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> selectMovie = new HashMap<>();
				selectMovie.put("miNum",rs.getString("mi_num"));
				selectMovie.put("miName",rs.getString("mi_name"));
				selectMovie.put("miYear",rs.getString("mi_year"));
				selectMovie.put("miNational",rs.getString("mi_national"));
				selectMovie.put("miVendor",rs.getString("mi_vendor"));
				selectMovie.put("miDirector",rs.getString("mi_director"));
				return selectMovie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}
	
	@Override
	public int insertMovie(Map<String,String> movie) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertMovie);
			ps.setString(1, movie.get("mi_name"));
			ps.setString(2, movie.get("mi_year"));
			ps.setString(3, movie.get("mi_national"));
			ps.setString(4, movie.get("mi_vendor"));
			ps.setString(5, movie.get("mi_director"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}
//	public static void main(String[] args) {
//		MovieDAO mdao = new MovieDAOImpl();
//		Map<String,String> movie = new HashMap<>();
//		movie.put("mi_name","test");
//		movie.put("mi_year","2019");
//		movie.put("mi_national","t");
//		movie.put("mi_vendor","test");
//		movie.put("mi_director","test");
//		System.out.println(mdao.insertMovie(movie));
//	}

	@Override
	public int deleteMovie(int miNum) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(deleteMovie);
			ps.setInt(1, miNum);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}
	
}
