package servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.sessions.ICartBean;
import jobs.CartItem;
import utils.Converter;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "cartSessionKey";
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ICartBean cartBean = (ICartBean) request.getSession().getAttribute(CART_SESSION_KEY);
		if(cartBean == null)
		{
			try 
			{
				InitialContext context = new InitialContext();
				cartBean = (ICartBean) context.lookup("global/ecom-ear/ecom-ejb/CartBean!bean.sessions.ICartBean");
				request.getSession().setAttribute(CART_SESSION_KEY, cartBean);
				System.out.println("NEW CART !");
			} 
			catch (NamingException e) 
			{
				e.printStackTrace();
				response.getWriter().append("500 - Erreur serveur");
			}
		} else System.out.println("REUSE EXISTING CART");
		
		JSONArray array = new JSONArray();
		for(CartItem item : cartBean.getContents())
		{
			array.put(item.toJson());
		}
		response.getWriter().append(Converter.formatJson(array.toString()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		
		try 
		{
			JSONObject obj = new JSONObject(requestBody);
			String bungalowId = (String) obj.get("bungalowid");
			String startWeek = (String) obj.get("startweek");
			String endWeek = (String) obj.get("endweek");
			String duration = (String) obj.get("duration");
			
			while(obj.keys().hasNext())
			{
				String k = obj.keys().next();
				System.out.println(k);
			}
		}
		catch (JSONException e)
		{
			response.getWriter().append("400 - Bad request (Arguments error)");
		}
	}

}
