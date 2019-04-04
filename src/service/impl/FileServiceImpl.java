package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import dao.FileDAO;
import dao.impl.FileDAOImpl;
import service.FileService;
import utils.UploadFile;

public class FileServiceImpl implements FileService {
	private static FileDAO fdao = new FileDAOImpl();
	
	public Map<String,String> parseText(HttpServletRequest request) throws ServletException {
		Map<String,Object> pMap = UploadFile.parseRequest(request);
		Iterator<String> it = pMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			Object obj = pMap.get(key);
			if(obj instanceof FileItem) {
				try {
					File tFile = UploadFile.writeFile((FileItem)obj);
					return insertAddrFromFile(tFile);
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		}
		return null;
	}
	
	public Map<String,String> insertAddrFromFile(File tFile) {
		List<String> colList = new ArrayList<>();
		colList.add("ad_code");
		colList.add("ad_sido");
		colList.add("ad_gugun");
		colList.add("ad_dong");
		colList.add("ad_lee");
		colList.add("ad_san");
		colList.add("ad_bunji");
		colList.add("ad_ho");
		colList.add("ad_roadcode");
		colList.add("ad_isbase");
		colList.add("ad_orgnum");
		colList.add("ad_subnum");
		colList.add("ad_jinum");
		colList.add("ad_etc");
		
		try {
			FileReader fr = new FileReader(tFile);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			List<Map<String,String>> addrList = new ArrayList<>();
			int cnt = 1;
			while((line=br.readLine())!=null) {
				String[] lines = line.split("\\|");
				Map<String,String> addrMap = new HashMap<>();
				for(int i=0;i<lines.length;i++) {
					try {
						addrMap.put(colList.get(i), lines[i]);
					}catch(IndexOutOfBoundsException iobe) {
						System.out.println(lines.length);
						System.out.println(i+ "번째 라인");
					}
				}
				addrList.add(addrMap);
				if(addrList.size()==10000) {
					int result = fdao.insertAddressList(addrList);
					addrList.clear();
				}
			}
			int result = fdao.insertAddressList(addrList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
