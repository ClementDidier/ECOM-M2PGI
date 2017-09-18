import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import bean.sessions.IBungalowsBean;
import jobs.Bungalow;

public class BungalowTest 
{
	public static Properties properties = null;
	public static InitialContext context = null;
	
	@Before
	public void init() throws NamingException
	{
		if(properties == null)
		{
			properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory"); 
			properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
			properties.put("jboss.naming.client.ejb.context", true);
			properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		}
		
		context = new InitialContext(properties);
	}
	
	@Test
	public void getAllBungalowsTest() throws NamingException
	{
		IBungalowsBean obj = (IBungalowsBean) context.lookup("ejb:ecom-ear/ecom-ejb/BungalowsBean!bean.sessions.IBungalowsBean");
		
		List<Bungalow> bungalows = (List<Bungalow>) obj.getBungalows();
		
		// TODO : Suite test
		
		assertTrue(true);
	}
}
