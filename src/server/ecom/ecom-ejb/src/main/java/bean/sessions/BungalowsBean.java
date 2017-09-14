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

	@Override
	public Collection<Bungalow> getBungalows(Integer id) 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		Collection<Bungalow> bungalows = manager.createQuery(
			    " FROM Bungalow b WHERE b.id=:id")
			    .setParameter("id", id)
			    .getResultList();
		return bungalows;
	}

	@Override
	public Collection<Bungalow> getBungalows(Integer bedcount, Integer maxprice, String islandname) 
	{
		Collection<Bungalow> resultList = manager.createQuery(
			    " FROM Bungalow b WHERE b.bedCount=:bedcount AND b.islandName=:islandname AND b.price <= :maxprice")
			    .setParameter("bedcount", bedcount)
			    .setParameter("islandname", islandname)
			    .setParameter("maxprice", maxprice)
			    .getResultList();
		return resultList;
	}
}
