package controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static int BUFFER_SIZE = 1024 * 100;
	public static final String UPLOAD_DIRECTORY = "resources";
	public static String filename = null;
	
    public DownloadFileServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filename = request.getParameter("filename");
		if (filename == null || filename.equals("")) {
			System.out.print("File is empty");
		}
		else {
			String applicationpath = getServletContext().getRealPath("");
			String downloadpath = applicationpath + File.separator + UPLOAD_DIRECTORY;
			String filepath = downloadpath + File.separator + filename;
			
			File file = new File(filepath);
			OutputStream os = null;
			FileInputStream is = null;
			
			if(file.exists()) {
				String mimeType = "application/octet-stream";
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);
                
                try {
                	os = response.getOutputStream();
                	is = new FileInputStream(file);
                	byte[] buffer = new byte[BUFFER_SIZE];
                	int bytesread = -1;
                	
                	while ((bytesread = is.read(buffer)) != -1 ) {
                		os.write(buffer, 0, bytesread);
                	}
                }
                catch(IOException e) {
                	e.getMessage();
                }
                finally {
                	if(is != null) {
                		is.close();
                	}
                	os.flush();
                	if(os != null) {
                		os.close();
                	}
                }
			}
			else {
				System.out.print("File not exist");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
