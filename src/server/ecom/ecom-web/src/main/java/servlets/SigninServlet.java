package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.sessions.IBungalowsBean;
import bean.sessions.UserBean;
import jpa.entities.User;

/**
 * Servlet implementation class UserServlet
 */
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB(lookup="java:global/ecom-ear/ecom-ejb/UserBean!bean.sessions.UserBean")
	private UserBean userBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Usage du langage JPQL pour la projection contre les injections
		
		System.out.println("GET From SigninServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mail = request.getParameter("mail");
		if(mail != null)
		{
			// TODO : Not tested
			User user = this.userBean.getUser(mail);
			System.out.println(user.toJson());
		}
		else System.out.println("MAIL NULL");
	}

}
