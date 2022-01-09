package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Admin;
import dao.AdminDAO;

@WebServlet("/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO admindao;
    
    public UpdateAdminServlet() {
        super();
        admindao = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin updtadmin = new Admin();
		if (request.getParameter("update_action").equalsIgnoreCase("account")) {
			updtadmin.setAdminid(Integer.parseInt(request.getParameter("adminid")));
			updtadmin.setAdminpassword(request.getParameter("adminpassword"));
			updtadmin.setAdminname(request.getParameter("adminname"));
			updtadmin.setAdminphonenum(Integer.parseInt(request.getParameter("adminphonenum")));
			updtadmin.setAdminemail(request.getParameter("adminemail"));
	
			if(Integer.parseInt(request.getParameter("superviseid")) != 0) {
				updtadmin.setSuperviseid(Integer.parseInt(request.getParameter("superviseid")));
			}
			
			String status = admindao.updateadmin(updtadmin);
			request.setAttribute("status", status);
	
			request.setAttribute("superviselist", admindao.viewadminlist());
			request.setAttribute("admininfo", admindao.viewadmin(Integer.parseInt(request.getParameter("adminid"))));
			RequestDispatcher view = request.getRequestDispatcher("Admin_update_account.jsp");
			view.forward(request, response);
		}
		else if (request.getParameter("update_action").equalsIgnoreCase("level")) {
			updtadmin.setAdminid(Integer.parseInt(request.getParameter("adminid")));
			updtadmin.setAdminlevel(request.getParameter("adminlevel"));
			
			String status = admindao.updateadminlevel(updtadmin);
			request.setAttribute("status", status);
			
			request.setAttribute("admininfo", admindao.viewadmin(Integer.parseInt(request.getParameter("adminid"))));
			RequestDispatcher view = request.getRequestDispatcher("Admin_update_admin.jsp");
			view.forward(request, response);
		}
	}
}