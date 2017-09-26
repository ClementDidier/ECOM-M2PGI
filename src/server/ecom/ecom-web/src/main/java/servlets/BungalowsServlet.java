package servlets;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import bean.sessions.IBungalowsBean;
import jpa.entities.Bungalow;
import utils.Converter;

/**
 * Servlet implementation class BungalowsServlet
 */
public class BungalowsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final int SPACE_SIZE = 2;
	private static final int GET_ALL_BUNGALOWS_REQUEST_ID 			= 1;
	private static final int GET_BY_ID_BUNGALOW_REQUEST_ID 			= 2;
	private static final int GET_BY_CRITERIA_BUNGALOWS_REQUEST_ID 	= 3;
	
	@EJB(lookup="java:global/ecom-ear/ecom-ejb/BungalowsBean!bean.sessions.IBungalowsBean")
	private IBungalowsBean bungalowBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BungalowsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String convertToJson(Collection<Bungalow> bungalows)
    {
    	JSONArray arrayResult = new JSONArray();
		if(bungalows != null)
		{
			for(Bungalow bungalow : bungalows)
				arrayResult.put(bungalow.toJson());
		}
		
		return arrayResult.toString(SPACE_SIZE).replace("\"{", "{").replace("}\"", "}").replace("\\", "");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		// Usage du langage JPQL pour la projection contre les injections
		
		try
		{
			Integer requestid = Converter.getIntegerOf(request.getParameter("requestid"));
			if(requestid == null)
				throw new Exception("L'identifiant de requete est introuvable, impossible de determiner le type de la requete");
			
			switch(requestid)
			{
				case GET_ALL_BUNGALOWS_REQUEST_ID:
					this.doGetAllBungalows(request, response);
					break;
				case GET_BY_ID_BUNGALOW_REQUEST_ID:
					this.doGetByIdBungalows(request, response);
					break;
				case GET_BY_CRITERIA_BUNGALOWS_REQUEST_ID:
					this.doGetByCriteriaBungalows(request, response);
					break;
			}
		}
		catch(Exception e)
		{
			response.getWriter().append("{\"state\": \"0\", \"msg\" : \"" + e.getMessage() + "\" }");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doGetAllBungalows(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		/* REQUEST GET [] */
		Collection<Bungalow> bungalows = this.bungalowBean.getBungalows();
		response.getWriter().append(this.convertToJson(bungalows).toString());
	}
	
	protected void doGetByIdBungalows(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		/* REQUEST GET [id] */
		Integer id = Converter.getIntegerOf(request.getParameter("id"));
		if(id != null)
		{
			Bungalow bungalow = this.bungalowBean.getBungalows(id);
			if(bungalow != null)
				response.getWriter().append(bungalow.toJson());
			else
				response.getWriter().append("[]");
		}
		else throw new Exception("La fonction attends un identifiant");
	}
	
	protected void doGetByCriteriaBungalows(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Integer minbedcount = Converter.getIntegerOf(request.getParameter("minbedcount"));
		Integer islandid = Converter.getIntegerOf(request.getParameter("islandid"));
		Integer minprice = Converter.getIntegerOf(request.getParameter("minprice"));
		Integer maxprice = Converter.getIntegerOf(request.getParameter("maxprice"));
		Integer startweek =Converter.getIntegerOf(request.getParameter("startweek"));
		Integer endweek = Converter.getIntegerOf(request.getParameter("endweek"));

		if(minbedcount != null && islandid !=null && minprice !=null && maxprice != null && startweek != null && endweek != null)
		{
			Collection<Bungalow> bungalows = this.bungalowBean.getBungalows(minbedcount, islandid, minprice, maxprice, startweek, endweek);
			response.getWriter().append(this.convertToJson(bungalows).toString());
		}
		else throw new Exception("Un ou plusieurs des arguments attendus n'ont pas etes renseignes");
	}
}
