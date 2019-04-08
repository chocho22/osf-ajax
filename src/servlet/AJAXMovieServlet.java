package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MovieService;
import service.impl.MovieServiceImpl;
import utils.Command;

public class AJAXMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if ("list".equals(cmd)) {
			PrintWriter pw = response.getWriter();
			pw.println(gson.toJson(ms.selectMovieList()));
		} else {
			try {
				int miNum = Integer.parseInt(cmd);
				PrintWriter pw = response.getWriter();
				pw.println(gson.toJson(ms.selectMovieByNum(miNum)));
			} catch (Exception e) {
				throw new ServletException("올바른 상세조회 값이 아닙니다.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = Command.getCmd(request);
		String msg = "";
		String url = "";
		Map<String,String> rMap = new HashMap<>();
		if ("insert".equals(cmd)) {
			HttpSession hs = request.getSession();
			if (hs.getAttribute("user") == null) {
//				Command.goResultPage(request,response,"/","로그인 하세요.");
				Command.printJSON(response,rMap);  // 아래 4줄 대신
				return;
			}
			Map<String,String> movie = Command.getSingleMap(request);
			rMap.put("msg","영화 등록 실패");
			rMap.put("url","/views/movie/ajax_list");
//			msg = "영화 등록 실패";
//			url = "/views/movie/ajax_list";
			if (ms.insertMovie(movie) == 1) {
				rMap.put("msg","영화 등록 성공");
//				msg = "영화 등록 성공";
//				url = "/movie/list";  // 이건 ajax가 아닌 list
			}
			Command.printJSON(response,rMap);  // 아래 4줄 대신
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter pw = response.getWriter();
//			pw.println(gson.toJson(rMap));
//			Command.goResultPage(request, response, url, msg);
		} else if ("delete".equals(cmd)) {
			HttpSession hs = request.getSession();
			if (hs.getAttribute("user") == null) {
				Command.goResultPage(request,response,"/","로그인 하세요.");
				return;
			}
			int miNum = Integer.parseInt(request.getParameter("mi_num"));
			rMap = new HashMap<>();
			rMap.put("msg","영화 삭제 실패");
			rMap.put("url","/views/movie/ajax_list");
//			msg = "영화 삭제 실패";
//			url = "/movie/" + miNum;
			if (ms.deleteMovie(miNum) == 1) {
				rMap.put("msg","영화 삭제 성공");
//				msg = "삭제 성공";
//				url = "/movie/list";  // 이건 ajax가 아닌 list
			}
			Command.printJSON(response,rMap);  // 아래 4줄 대신
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter pw = response.getWriter();
//			pw.println(gson.toJson(rMap));
//			Command.goResultPage(request, response, url, msg);
		}
	}
}
