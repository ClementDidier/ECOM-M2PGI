package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.sessions.UserBean;
import jpa.entities.User;
import utils.Converter;

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
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		JSONObject obj = new JSONObject(requestBody);
		String mail = (String) obj.get("mail");
		if(mail != null)
		{
			User user = this.userBean.getUser(mail);
			if(user != null)
			{
				errorMessage = "Un compte existe déjà avec cette adresse email";
			}
			else
			{
				// TODO : VALIDER LES PARAMETRES + Cryptage
				
				String firstname = (String) obj.get("firstname");
				String lastname = (String) obj.get("lastname");
				String address = (String) obj.get("address");
				String postal = (String) obj.get("postal");
				String phone = (String) obj.get("phone");
				String hashedPassword = (String) obj.get("password");
				
				try {
					this.userBean.save(new User(firstname, lastname, address, postal, mail, phone, hashedPassword));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					errorMessage = "Erreur serveur";
				}
			}
		}
		else
		{
			errorMessage = "Un paramètre attendu n'a pas été définit";
		}
		
		if(errorMessage != null)
		{
			response.getWriter().append("{ 'state' : '0', 'msg' : '" + errorMessage + "' }");
		}
		else
		{
			response.getWriter().append("{ 'state' : '1' }");
		}
	}

}
