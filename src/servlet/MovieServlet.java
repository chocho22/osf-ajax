package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MovieService;
import service.impl.MovieServiceImpl;

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MovieService ms = new MovieServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		if(idx==0) {
			throw new ServletException("원하는 서비스가 부정확합니다.");
		} else {
			String cmd = uri.substring(idx+1);
			if("insert".equals(cmd)) {
				HttpSession hs = request.getSession();
				if(hs.getAttribute("user")==null) {
					request.setAttribute("msg", "로그인 하세요.");
					request.setAttribute("url", "/");
					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
					rd.forward(request, response); 
					return;
				}
			}
			if("list".equals(cmd)) {
				List<Map<String,String>> movieList = ms.selectMovieList();
				request.setAttribute("movieList", movieList);
				RequestDispatcher rd = request.getRequestDispatcher("/views/movie/movie_list");
				rd.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		if(idx==0) {
			throw new ServletException("원하는 서비스가 부정확합니다.");
		} else {
			String cmd = uri.substring(idx+1);
			if("insert".equals(cmd)) {
				Map<String,String> movie = new HashMap<>();
				movie.put("mi_name",request.getParameter("miName"));
				movie.put("mi_year",request.getParameter("miYear"));
				movie.put("mi_national",request.getParameter("miNational"));
				movie.put("mi_vendor",request.getParameter("miVendor"));
				movie.put("mi_director",request.getParameter("miDirector"));
				request.setAttribute("msg", "영화 등록 실패");
				if(ms.insertMovie(movie)==1) {
					request.setAttribute("msg", "영화 등록 성공");
				}
				request.setAttribute("url", "/movie/list");
				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
				rd.forward(request, response);
			}
		}
		doGet(request, response);
	}
}
