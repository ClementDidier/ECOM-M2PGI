package bean.sessions;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jobs.Bungalow;
import jobs.Connexion;

/**
 * Session Bean implementation class Connexion
 */
@Stateless
@LocalBean
public class ConnexionBean implements IConnexionBean {

	@PersistenceContext(unitName="primary")
    private EntityManager manager;
    /**
     * Default constructor. 
     */
    public ConnexionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Connexion> loginUser(String login, String password) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Connexion> connexion = (List<Connexion>) manager.createQuery(
			    " FROM users b WHERE b.mail=:login and b.password=:password")
			    .setParameter("mail", login)
			    .setParameter("password", password)
			    .setMaxResults(1)
			    .getResultList();
		
		if(!connexion.isEmpty())
			return connexion;
		else 
			return null;
	}

	@Override
	public Connexion getUser(Integer id) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<Connexion> bungalows = (List<Connexion>) manager.createQuery(
			    " FROM CONNEXION b WHERE b.UserId=:id")
			    .setParameter("UserId", id)
			    .setMaxResults(1)
			    .getResultList();
		
		if(bungalows.isEmpty())
			return null;
		else 
			return bungalows.get(0);
	}
	

}
