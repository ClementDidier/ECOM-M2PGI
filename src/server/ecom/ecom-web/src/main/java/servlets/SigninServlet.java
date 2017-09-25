package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String errorMessage = null;
		String mail = request.getParameter("mail");
		if(mail != null)
		{
			User user = this.userBean.getUser(mail);
			if(user != null)
			{
				errorMessage = "Un compte existe déjà avec cette adresse email";
			}
			else
			{
				// TODO : VALIDER LES PARAMETRES + SECURITE
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String address = request.getParameter("address");
				String postal = request.getParameter("postal");
				String phone = request.getParameter("phone");
				String password = request.getParameter("password");
				User u = new User(firstname, lastname, address, postal, mail, phone, password);
				this.userBean.save(u);
			}
		}
		else
		{
			errorMessage = "Un paramètre attendu n'a pas été définit";
		}
		
		if(errorMessage != null)
		{
			response.getWriter().append("{ 'state' : '0', 'msg' : 'Un compte existe déjà avec cette adresse email' }");
		}
		else
		{
			response.getWriter().append("{ 'state' : '1' }");
		}
	}

}
