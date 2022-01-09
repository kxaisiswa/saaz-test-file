package controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Parent;
import model.Student;
import dao.ParentDAO;
import dao.StudentDAO;

@WebServlet("/AddParentServlet")
public class AddParentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParentDAO parentdao;
	private StudentDAO studdao;
    
    public AddParentServlet() {
        super();
        parentdao = new ParentDAO();
        studdao = new StudentDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Parent newparent = new Parent();
		Student updtstudent = new Student();
		
		newparent.setParentic(Long.parseLong(request.getParameter("parentic")));
		newparent.setParentname(request.getParameter("parentname"));
		newparent.setParentnationality(request.getParameter("parentnationality"));
		newparent.setParentrace(request.getParameter("parentrace"));
		newparent.setParentreligion(request.getParameter("parentreligion"));
		newparent.setParentphonenum(Integer.parseInt(request.getParameter("parentphonenum")));
		newparent.setParentnumdependent(Integer.parseInt(request.getParameter("parentnumdependent")));
		newparent.setParentjob(request.getParameter("parentjob"));
		newparent.setParenttaxnumber(request.getParameter("parenttaxnumber"));
		newparent.setParentincome(Double.parseDouble(request.getParameter("parentincome")));
		newparent.setParentrelation(request.getParameter("parentrelation"));
		
		String parentstatus = parentdao.addparent(newparent);
		
		updtstudent.setStudid(Integer.parseInt(request.getParameter("studid")));
		updtstudent.setParentic(Long.parseLong(request.getParameter("parentic")));
		
		String studentstatus = studdao.updatestudentparent(updtstudent);
		
		String status = null;
		if(parentstatus == "success" && studentstatus == "success") {
			status = "Parent information added";
		}
		else {
			status = "Update parent information failed";
		}
		request.setAttribute("status", status);
		
		request.setAttribute("studentinfo", studdao.viewstudentparent(Integer.parseInt(request.getParameter("studid"))));
		RequestDispatcher view = request.getRequestDispatcher("Student_update_parent.jsp");
		view.forward(request, response);
	}
}