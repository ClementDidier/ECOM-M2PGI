package servlets;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import bean.sessions.ICartBean;
import jobs.CartItem;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try 
		{
			InitialContext context = new InitialContext();
			ICartBean userBean = (ICartBean)context.lookup("global/ecom-ear/ecom-ejb/CartBean!bean.sessions.ICartBean");
			
			JSONArray array = new JSONArray();
			for(CartItem item : userBean.getContents())
			{
				array.put(item.toJson());
			}
			response.getWriter().append(array.toString());
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
			response.getWriter().append("500 - Erreur serveur");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
