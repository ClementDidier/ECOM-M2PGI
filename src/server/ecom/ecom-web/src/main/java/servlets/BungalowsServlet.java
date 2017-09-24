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
import jobs.Bungalow;
import utils.ParameterConverter;

/**
 * Servlet implementation class BungalowsServlet
 */
public class BungalowsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final int SPACE_SIZE = 2;
	
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
		
		/* REQUEST GET [id] */
		Integer id = ParameterConverter.getIntegerOf(request.getParameter("id"));
		if(id != null) 
		{
			Bungalow bungalow = this.bungalowBean.getBungalows(id);
			if(bungalow != null)
				response.getWriter().append(bungalow.toJson());
			return;
		}
		
	/* REQUEST GET [
  minbedcount: number;
  islandid:number;
  minprice:number;
  maxprice:number;
  startweek:number;
  endweek:number;
  ]
   */
		
		Integer minbedcount = ParameterConverter.getIntegerOf(request.getParameter("minbedcount"));
		Integer islandid = ParameterConverter.getIntegerOf(request.getParameter("islandid"));
		Integer minprice = ParameterConverter.getIntegerOf(request.getParameter("minprice"));
		Integer maxprice = ParameterConverter.getIntegerOf(request.getParameter("maxprice"));
		Integer startweek = ParameterConverter.getIntegerOf(request.getParameter("startweek"));
		Integer endweek = ParameterConverter.getIntegerOf(request.getParameter("endweek"));

		if(minbedcount != null && islandid !=null && minprice !=null & maxprice != null && startweek != null && endweek != null)
		{
			Collection<Bungalow> bungalows = this.bungalowBean.getBungalows(minbedcount,islandid,minprice,  maxprice, startweek, endweek);
			response.getWriter().append(this.convertToJson(bungalows).toString());
			return;
		}
		// La requête est invalide si un seul des paramètres de la requête précédante existe
		else if(!(minbedcount != null && islandid !=null && minprice !=null & maxprice != null && startweek != null && endweek != null))
		{
			Collection<Bungalow> bungalows = null;
			response.getWriter().append(this.convertToJson(bungalows).toString());
			return;
		}

		/* REQUEST GET [] */
		Collection<Bungalow> bungalows = this.bungalowBean.getBungalows();
		response.getWriter().append(this.convertToJson(bungalows).toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
