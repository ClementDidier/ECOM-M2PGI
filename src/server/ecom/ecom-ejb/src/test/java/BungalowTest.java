import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import bean.sessions.IBungalowsBean;
import jpa.entities.Bungalow;

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
		
		assertFalse("La liste ne devrait pas être vide", bungalows.isEmpty());
	}
	
	@Test
	public void getBungalowNotRented() throws NamingException
	{
		IBungalowsBean obj = (IBungalowsBean) context.lookup("ejb:ecom-ear/ecom-ejb/BungalowsBean!bean.sessions.IBungalowsBean");
		
		// DATABASE : LOCATION BUNGALOW 1 [201702 - 201703]
		
		Bungalow b = obj.getBungalowNotRented(1, 201702, 201703);
		assertEquals("L'objet est loué à cette date et ne devrait pas être retourné", b, null);
		
		b = obj.getBungalowNotRented(1, 201701, 201703);
		assertEquals("L'objet est loué pendant cette date et ne devrait pas être retourné", b, null);
		
		b = obj.getBungalowNotRented(1, 201702, 201704);
		assertEquals("L'objet est loué pendant cette date et ne devrait pas être retourné", b, null);
		
		// Dans la table TEMP_ORDERS
		b = obj.getBungalowNotRented(1, 201703, 201704);
		assertEquals("L'objet est loué pendant cette date et ne devrait pas être retourné", b, null);
		
		b = obj.getBungalowNotRented(1, 201704, 201705);
		assertNotEquals("L'objet n'est pas loué à cette date et devrait être retourné", b, null);
	}
}
