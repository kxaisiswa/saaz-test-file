package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Student;
import dao.StudentDAO;

@WebServlet("/UpdateStudentServlet")
@MultipartConfig(
		fileSizeThreshold = 1024* 1024 * 10, //10Mb
		maxFileSize = 1024 * 1024 * 1000, //1Gb
		maxRequestSize = 1024 * 1024 * 1000 //1Gb
		)

public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
    
    public UpdateStudentServlet() {
        super();
        studdao = new StudentDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student updtstudent = new Student();
		
		updtstudent.setStudid(Integer.parseInt(request.getParameter("studid")));
		updtstudent.setStudname(request.getParameter("studname"));
		updtstudent.setStudic(Long.parseLong(request.getParameter("studic")));
		updtstudent.setStudaddress(request.getParameter("studaddress"));
		updtstudent.setStudphonenum(Integer.parseInt(request.getParameter("studphonenum")));
		updtstudent.setStudemail(request.getParameter("studemail"));
		updtstudent.setStudgender(request.getParameter("studgender"));
		updtstudent.setStudbirthcertnum(request.getParameter("studbirthcertnum"));
		updtstudent.setStudnationality(request.getParameter("studnationality"));
		updtstudent.setStudrace(request.getParameter("studrace"));
		updtstudent.setStudreligion(request.getParameter("studreligion"));
		updtstudent.setStudsemester(Integer.parseInt(request.getParameter("studsemester")));
		
		Part filepart = request.getPart("studfile");
		
		if(filepart.getSize() > 0) {
			System.out.println("File uploaded");
			String foldername = "resources";
			String uploadpath = request.getServletContext().getRealPath("") + foldername;
			File directory = new File(uploadpath);
			if(!directory.exists()) {
				directory.mkdirs();
			}

			String filename = filepart.getSubmittedFileName();
			
			InputStream is = filepart.getInputStream();
			Files.copy(is, Paths.get(uploadpath + File.separator + filename), StandardCopyOption.REPLACE_EXISTING);
			updtstudent.setStuddocpath(filename);
		}
		else if(filepart.getSize() <= 0) {
			System.out.println("No file uploaded");
		}
		
		String input_date = request.getParameter("studbirthdate");
		Date dateformat;
		try {
			dateformat = new SimpleDateFormat("yyyy-MM-dd").parse(input_date);
			java.sql.Date sqlDate = new java.sql.Date(dateformat.getTime());
			updtstudent.setStudbirthdate(sqlDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		String livingstatus = request.getParameter("studlivingplace");
		updtstudent.setStudlivingplace(livingstatus);
		System.out.println(livingstatus);
		
		String status = studdao.updatestudent(updtstudent);
		if(livingstatus.equals("none")) {
			studdao.deletehomebyid(Integer.parseInt(request.getParameter("studid")));
			studdao.deletecollegebyid(Integer.parseInt(request.getParameter("studid")));
			
		}
		else if(livingstatus.equals("Kolej")) {
			updtstudent.setCollegecode(request.getParameter("collegecode"));
			updtstudent.setCollegename(request.getParameter("collegename"));
			
			studdao.addupdatecollege(updtstudent);
			studdao.deletehomebyid(Integer.parseInt(request.getParameter("studid")));
		}
		else if(livingstatus.equals("Rumah")) {
			updtstudent.setTransportmode(request.getParameter("transportmode"));
			updtstudent.setStudschooldistance(Integer.parseInt(request.getParameter("studschooldistance")));
			
			studdao.addupdatehome(updtstudent);
			studdao.deletecollegebyid(Integer.parseInt(request.getParameter("studid")));
		}
		
		request.setAttribute("status", status);
		request.setAttribute("studentinfo", studdao.viewstudent(Integer.parseInt(request.getParameter("studid"))));
		RequestDispatcher view = request.getRequestDispatcher("Student_update_info.jsp");
		view.forward(request, response);
	}
}