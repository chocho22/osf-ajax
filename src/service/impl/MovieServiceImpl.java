package service.impl;

import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import dao.impl.MovieDAOImpl;
import service.MovieService;

public class MovieServiceImpl implements MovieService {
	MovieDAO mdao = new MovieDAOImpl();
	
	@Override
	public List<Map<String, String>> selectMovieList() {
		return mdao.selectMovieList();
	}

	@Override
	public int insertMovie(Map<String,String> movie) {
		return mdao.insertMovie(movie);
	}

	@Override
	public Map<String, String> selectMovieByNum(int miNum) {
		return mdao.selectMovieByNum(miNum);
	}

	@Override
	public int deleteMovie(int miNum) {
		return mdao.deleteMovie(miNum);
	}
	
}
