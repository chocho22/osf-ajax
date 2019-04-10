package service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.AddrDAO;
import dao.impl.AddrDAOImpl;
import service.AddrService;
import utils.Command;

public class AddrServiceImpl implements AddrService {
	private AddrDAO adao = new AddrDAOImpl();

	@Override
	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
		Map<String, String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		int blockCount = 10;
		if (paramMap.get("page") != null && !"".equals(paramMap.get("page"))) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if (paramMap.get("pageCount") != null && !"".equals(paramMap.get("pageCount"))) {
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		if (paramMap.get("blockCount") != null) {
			blockCount = Integer.parseInt(paramMap.get("blockCount"));
		}
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockCount", blockCount);
		
		int lNum = page * pageCount;
		int sNum = lNum - (pageCount - 1);
		paramMap.put("lNum", lNum + "");
		paramMap.put("sNum", sNum + "");
		
		List<Map<String, String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);

		int totalCnt = adao.selectTotalAddrCnt(paramMap);
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt / pageCount;
		if (totalCnt % pageCount > 0) {
			totalPageCnt++;
		}
		request.setAttribute("totalPageCnt", totalPageCnt);

		int lBlock = ((page - 1) / blockCount + 1) * blockCount;
		int fBlock = lBlock - (blockCount - 1);
//		int fBlock1 = ( page - 1 ) / blockCount + 1;
//		int lBlock1 = fBlock1 + ( blockCount - 1 );
		if (lBlock > totalPageCnt) {
			lBlock = totalPageCnt;
		}
		request.setAttribute("lBlock", lBlock);
		request.setAttribute("fBlock", fBlock);

		return addrList;
	}

	@Override
	public int selectTotalAddrCnt() {
//		return adao.selectTotalAddrCnt();
		return 0;
	}

	@Override
	public void selectAddr(HttpServletRequest request) {
		Map<String, String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		if (paramMap.get("page") != null) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if (paramMap.get("pageCount") != null) {
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("addr", adao.selectAddr(paramMap));
	}
	
	@Override
	public Map<String,String> updateAddr(HttpServletRequest request) throws IOException {
		Map<String, String> paramMap = Command.fromJSON(request);
		Map<String,String> response = new HashMap<>();
		String update = "false";
		String msg = "수정에 실패했습니다.";
		if(adao.updateAddr(paramMap)==1) {
			update = "true";
			msg = "수정에 성공했습니다.";
		}
		String url = "/addr2/list";
		response.put("update",update);
		response.put("msg",msg);
		response.put("url",url);
		
		return response;
	}

	@Override
	public Map<String, String> deleteAddr(HttpServletRequest request) throws IOException {
		Map<String, String> paramMap = Command.fromJSON(request);
		Map<String,String> response = new HashMap<>();
		String delete = "false";
		String msg = "삭제 실패했습니다.";
		if(adao.deleteAddr(paramMap)==1) {
			delete = "true";
			msg = "삭제 성공했습니다.";
		}
		String url = "/addr2/list";
		response.put("delete",delete);
		response.put("msg",msg);
		response.put("url",url);
		
		return response;
	}
}
