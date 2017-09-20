package servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.sessions.IBungalowsBean;
import bean.sessions.UserBean;
import jobs.User;

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
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		JSONObject obj = new JSONObject(requestBody);
		String mail = (String) obj.get("mail");
		String firstname = (String) obj.get("firstname");
		String lastname = (String) obj.get("lastname");
		String address = (String) obj.get("address");
		String postal = (String) obj.get("postal");
		String city = (String) obj.get("city");
		String country = (String) obj.get("country");
		while(obj.keys().hasNext())
		{
			String k = obj.keys().next();
			System.out.println(k);
		}
		
		if(mail != null)
		{
			// TODO : Not tested
			User user = this.userBean.getUser(mail);
			if(user != null)
				System.out.println(user.toJson());
		}
		else System.out.println("MAIL NULL");
	}

}
