package bean.sessions;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jobs.Bungalow;

/**
 * Session Bean implementation class BungalowsBean
 */
@Stateless
@LocalBean
public class BungalowsBean implements IBungalowsBean {

	@PersistenceContext(unitName="primary")
    private EntityManager manager;
	
	
    public BungalowsBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Collection<Bungalow> getBungalows() 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		Query queryBungalows = manager.createQuery(" FROM Bungalow");
		Collection<Bungalow> bungalows = queryBungalows.getResultList();
		return bungalows;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Bungalow> getBungalows(String id) 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		Query queryBungalows = manager.createQuery(" FROM Bungalow");
		Collection<Bungalow> bungalows = manager.createQuery(
			    " FROM Bungalow b WHERE b.name=:id")
			    .setParameter("id", id)
			    .getResultList();
		return bungalows;
	}
}
