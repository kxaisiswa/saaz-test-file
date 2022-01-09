package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import model.Admin;
import dao.StudentDAO;
import dao.AdminDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	private AdminDAO admindao;
    HttpSession session;
    
    public LoginServlet() {
        super();
        studdao = new StudentDAO();
        admindao = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpassword = request.getParameter("userpassword");
		String usertype = request.getParameter("usertype");
		session = request.getSession(true);
		
		if(usertype.equals("Student")) {
			if(userid.matches("\\d+")) {
				if(userid.matches("\\d{7}")) {
					Student studinfo = new Student();
					
					studinfo.setStudid(Integer.parseInt(userid));
					studinfo.setStudpassword(userpassword);
					
					String loginstatus = studdao.loginstudent(studinfo);
					if(loginstatus.equals("success")) {
						session.setAttribute("session_id", userid);
						
						response.sendRedirect("StudentRedirectServlet?action=info");
					}
					else {
						session.setAttribute("session_status", loginstatus);
						response.sendRedirect("index.jsp");
					}
				}
				else {
					session.setAttribute("session_status", "Exactly seven numbers only");
					response.sendRedirect("index.jsp");
				}
			}
			else {
				session.setAttribute("session_status", "Number values only");
				response.sendRedirect("index.jsp");
			}
		}
		if(usertype.equals("Admin")) {
			if(userid.matches("\\d+")) {
				if(userid.matches("\\d{5}")) {
					Admin admininfo = new Admin();
					
					admininfo.setAdminid(Integer.parseInt(userid));
					admininfo.setAdminpassword(userpassword);
					
					String loginstatus = admindao.loginadmin(admininfo);
					
					admininfo = AdminDAO.viewadmin(Integer.parseInt(userid));
					
					if(loginstatus.equals("success")) {
						session.setAttribute("session_id", userid);
						session.setAttribute("session_level", admininfo.getAdminlevel());

						response.sendRedirect("AdminRedirectServlet?action=student");
					}
					else {
						session.setAttribute("session_status", loginstatus);
						response.sendRedirect("Admin_login.jsp");
					}
				}
				else {
					session.setAttribute("session_status", "Exactly five numbers only");
					response.sendRedirect("Admin_login.jsp");
				}
			}
			else {
				session.setAttribute("session_status", "Number values only");
				response.sendRedirect("Admin_login.jsp");
			}
		}
	}
}