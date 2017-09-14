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

/**
 * Servlet implementation class BungalowsServlet
 */
public class BungalowsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	@EJB(lookup="java:global/ecom-ear/ecom-ejb/BungalowsBean!bean.sessions.IBungalowsBean")
	private IBungalowsBean bungalowBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BungalowsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Collection<Bungalow> bungalows;
		
		String id = request.getParameter("id");
		if(id != null)
			bungalows = this.bungalowBean.getBungalows(id);
		else 
			bungalows = this.bungalowBean.getBungalows();
			
		JSONArray arrayResult = new JSONArray();
		for(Bungalow bungalow : bungalows)
			arrayResult.put(bungalow.toJson());
		response.getWriter().append(arrayResult.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
