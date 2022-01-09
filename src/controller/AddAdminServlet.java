package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Admin;
import dao.AdminDAO;

@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO admindao;
    
    public AddAdminServlet() {
        super();
        admindao = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin newadmin = new Admin();
		
		newadmin.setAdminid(Integer.parseInt(request.getParameter("adminid")));
		newadmin.setAdminname(request.getParameter("adminname"));
		newadmin.setAdminpassword(request.getParameter("adminpassword"));
		newadmin.setSuperviseid(Integer.parseInt(request.getParameter("superviseid")));
		newadmin.setAdminlevel(request.getParameter("adminlevel"));
		
		String status = admindao.addadmin(newadmin);
		request.setAttribute("status", status);
		request.setAttribute("adminlist", admindao.viewadminlist());
		
		RequestDispatcher view = request.getRequestDispatcher("Admin_add_admin.jsp");
		view.forward(request, response);
	}
}