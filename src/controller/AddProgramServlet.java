package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Program;
import dao.ProgramDAO;

@WebServlet("/AddProgramServlet")
public class AddProgramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgramDAO progdao;
    
    public AddProgramServlet() {
        super();
        progdao = new ProgramDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Program newprog = new Program();
		
		newprog.setProgcode(request.getParameter("progcode"));
		newprog.setProgname(request.getParameter("progname"));
		
		String status = progdao.addprogram(newprog);
		request.setAttribute("status", status);

		request.setAttribute("programlist", progdao.viewprogramlist());
		RequestDispatcher view = request.getRequestDispatcher("Admin_program.jsp");
		view.forward(request, response);
	}
}