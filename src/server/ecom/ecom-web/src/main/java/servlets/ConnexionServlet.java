package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import jobs.Connexion;

import bean.sessions.IConnexionBean;


/**
 * Servlet implementation class ConnexionServlet
 */
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private static final int SPACE_SIZE = 2;


	@EJB(lookup="java:global/ecom-ear/ecom-ejb/ConnexionBean!bean.sessions.IConnexionBean")
	private IConnexionBean connexionBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String convertToJson(Collection<Connexion> bungalows)
    {
    	JSONArray arrayResult = new JSONArray();
		if(bungalows != null)
		{
			for(Connexion bungalow : bungalows)
				arrayResult.put(bungalow.toJson());
		}
		
		return arrayResult.toString(SPACE_SIZE).replace("\"{", "{").replace("}\"", "}").replace("\\", "");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = (String)request.getParameter("login");
		String password = (String)request.getParameter("password");
		List<Connexion> connexion = this.connexionBean.loginUser(login, password);
		//response.getWriter().append(this.convertToJson(connexion).toString());

		if(connexion != null) response.getWriter().append(" Welcome "+login+" ! ").toString();
		else response.getWriter().append(" Login failed ! ").toString();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
