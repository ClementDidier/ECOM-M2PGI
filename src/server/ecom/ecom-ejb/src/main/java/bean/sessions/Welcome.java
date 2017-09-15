package bean.sessions;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import jobs.Bungalow;

/**
 * Session Bean implementation class Welcome
 */
@Stateless
@LocalBean
public class Welcome implements IWelcome
{
	@PersistenceContext(unitName="primary")
    private EntityManager manager;
	
    public Welcome() 
    {
        
    }

	@Override
	public void initialize() 
	{
        manager.persist(new Bungalow(2, 320, 1));
        manager.flush();
	}
}
