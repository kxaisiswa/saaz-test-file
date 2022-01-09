package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Class;
import dao.ClassDAO;
import dao.ProgramDAO;

@WebServlet("/AddClassServlet")
public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassDAO classdao;
	private ProgramDAO  progdao;
    
    public AddClassServlet() {
        super();
        classdao = new ClassDAO();
        progdao = new ProgramDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Class newclass = new Class();

		newclass.setClassid(request.getParameter("classid"));
		newclass.setClassname(request.getParameter("classname"));
		newclass.setClasscapacity(Integer.parseInt(request.getParameter("classcapacity")));
		newclass.setProgcode(request.getParameter("progcode"));
		
		String status = classdao.addclass(newclass);
		request.setAttribute("status", status);

		request.setAttribute("programlist", progdao.viewprogramlist());
		request.setAttribute("classlist", classdao.viewclasslist());
		RequestDispatcher view = request.getRequestDispatcher("Admin_class.jsp");
		view.forward(request, response);
	}
}