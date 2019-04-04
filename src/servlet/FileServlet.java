package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FileService;
import service.impl.FileServiceImpl;
import utils.Command;
import utils.UploadFile;

public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TMP_PATH = System.getProperty("java.io.tmpdir");
	private static final int MEMORY_SIZE = 10*1024*1024;
	private static final int TOTAL_SIZE = 100*1024*1024;
	private static final int FILE_SIZE = 30*1024*1024;
    private static final File TMP_FOLDER = new File(TMP_PATH);
    private static FileService fs = new FileServiceImpl();
    
    public FileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> rMap = fs.parseText(request);
		Command.printJSON(response, rMap);
		doGet(request, response);
	}

}
