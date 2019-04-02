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

}
