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
	/**
	 * Obtient la liste des bungalows disponibles dans la base de données
	 */
	public Collection<Bungalow> getBungalows() 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		Query queryBungalows = manager.createQuery(" FROM Bungalow");
		
		@SuppressWarnings("unchecked")
		Collection<Bungalow> bungalows = (Collection<Bungalow>) queryBungalows.getResultList();
		
		return bungalows;
	}

	@Override
	/**
	 * Obtient le bungalow disponible dans la base de données et disposant de l'identifiant donné, null dans le cas où le bungalow est inexistant
	 */
	public Bungalow getBungalows(Integer id) 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		@SuppressWarnings("unchecked")
		List<Bungalow> bungalows = (List<Bungalow>) manager.createQuery(
			    " FROM Bungalow b WHERE b.id=:id")
			    .setParameter("id", id)
			    .setMaxResults(1)
			    .getResultList();
		
		if(bungalows.isEmpty())
			return null;
		else 
			return bungalows.get(0);
	}

	@Override
	/**
	 * Obtient la liste des bungalows disponibles dans la base de données et disposant d'un nombre de lits définit, d'un prix inférieur au prix donné et d'une localisation équivalente à celle spécifiée
	 */
	public Collection<Bungalow> getBungalows(Integer bedcount, Integer maxprice, Integer islandid) 
	{
		@SuppressWarnings("unchecked")
		Collection<Bungalow> resultList = (Collection<Bungalow>) manager.createQuery(
			    " FROM Bungalow b WHERE b.bedCount=:bedcount AND b.islandId=:islandid AND b.price <= :maxprice")
			    .setParameter("bedcount", bedcount)
			    .setParameter("islandid", islandid)
			    .setParameter("maxprice", maxprice)
			    .getResultList();
		
		return resultList;
	}
}
