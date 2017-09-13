import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import bean.sessions.IWelcome;

public class WelcomeTest 
{
	@Test
	public void welcomeTest1() throws NamingException
	{	
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory"); 
		prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		prop.put("jboss.naming.client.ejb.context", true);
        prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
		InitialContext context = new InitialContext(prop);
		IWelcome obj = (IWelcome) context.lookup("ejb:ecom-ear/ecom-ejb/Welcome!bean.sessions.IWelcome");
		obj.initialize();
		
		assertTrue(true);
	}
}
