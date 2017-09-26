package servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.sessions.IBungalowsBean;
import bean.sessions.ICartBean;
import jobs.CartItem;
import jpa.entities.Bungalow;
import utils.Converter;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "cartSessionKey";
    private static final int ADD_CART_ITEM_REQUEST_ID 	= 1;
	private static final int EMPTY_CART_REQUEST_ID 		= 2;
	private static final int VALID_CART_REQUEST_ID 		= 3;
    
    @EJB(lookup="java:global/ecom-ear/ecom-ejb/BungalowsBean!bean.sessions.IBungalowsBean")
	private IBungalowsBean bungalowBean;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Appel exclusivement en doGet et doPost (ThreadSafe)
    private ICartBean getCartBean(HttpServletRequest request)
    {
    	ICartBean cartBean = (ICartBean) request.getSession().getAttribute(CART_SESSION_KEY);
		if(cartBean == null)
		{
			try 
			{
				InitialContext context = new InitialContext();
				cartBean = (ICartBean) context.lookup("global/ecom-ear/ecom-ejb/CartBean!bean.sessions.ICartBean");
				request.getSession().setAttribute(CART_SESSION_KEY, cartBean);
			} 
			catch (NamingException e) 
			{
				e.printStackTrace();
			}
		}
		
		return cartBean;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ICartBean cartBean = this.getCartBean(request);
		
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
		JSONObject obj = new JSONObject(requestBody);
		Integer requestId = Converter.getIntegerOf((String) obj.get("requestid"));

		try 
		{
			if(requestId == null)
				throw new Exception("L'identifiant de requête est invalide");
			
			switch(requestId)
			{
				case ADD_CART_ITEM_REQUEST_ID:
					this.doPostAddCartItem(request, response, obj);
					break;
				case EMPTY_CART_REQUEST_ID:
					this.doPostEmptyCart(request, response, obj);
					break;
				case VALID_CART_REQUEST_ID:
					this.doPostValidCart(request, response, obj);
					break;
			}
		}
		catch (Exception e)
		{
			response.getWriter().append("{\"state\": \"0\", \"msg\" : \"" + e.getMessage() + "\" }");
		}
	}

	private void doPostAddCartItem(HttpServletRequest request, HttpServletResponse response, JSONObject reqObject) throws Exception
	{
		Integer bungalowId = Converter.getIntegerOf((String) reqObject.get("bungalowid"));
		Integer startweek = Converter.getIntegerOf((String) reqObject.get("startweek"));
		Integer endweek = Converter.getIntegerOf((String) reqObject.get("endweek"));
		Integer duration = Converter.getIntegerOf((String) reqObject.get("duration"));
		
		if(startweek > endweek)
			throw new Exception("Impossible de realiser la requête, la date de debut de location est superieure a la date de fin");
		
		if(bungalowId == null)
			throw new Exception("Impossible d'interpreter l'identifiant donne, convertion impossible");
		
		if(this.bungalowBean.getBungalows(bungalowId) == null)
			throw new Exception("Le bungalow n'existe pas");
			
		Bungalow bungalow = this.bungalowBean.getBungalowNotRented(bungalowId, startweek, endweek);
		if(bungalow == null)
			throw new Exception("Le bungalow n'est pas disponible pour la periode demandee");
		
		ICartBean cartBean = this.getCartBean(request);
		cartBean.addItem(new CartItem(bungalow, startweek, endweek, duration));
		
		response.getWriter().append("{ \"state\" : \"1\" }");
	}
	
	private void doPostEmptyCart(HttpServletRequest request, HttpServletResponse response, JSONObject reqObject) throws IOException
	{
		ICartBean cartBean = this.getCartBean(request);
		cartBean.removeAllItems();
		
		response.getWriter().append("{ \"state\" : \"1\" }");
	}
	
	private void doPostValidCart(HttpServletRequest request, HttpServletResponse response, JSONObject reqObject) throws IOException
	{
		// TODO : À réaliser
		ICartBean cartBean = this.getCartBean(request);
		List<CartItem> validatedCarts = cartBean.validate(request.getSession().getId()); // Synchronized
		
		JSONArray resultArray = new JSONArray();
		for(CartItem item : validatedCarts)
			resultArray.put(item);
		
		response.getWriter().append("{ \"state\" : \"1\", \"cartitems\" : " + resultArray.toString() + " }");
	}
}
