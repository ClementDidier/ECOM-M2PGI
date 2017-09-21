package bean.sessions;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.entities.User;

@Stateless
@LocalBean
public class UserBean implements IUserBean
{
	@PersistenceContext(unitName="primary")
    private EntityManager manager;
	
	public UserBean() 
	{
		
	}

	// TODO : To Test
	@Override
	/**
	 * Obtient un utilisateur par son identifiant, null dans le cas où l'utilisateur n'existe pas
	 */
	public User getUser(Integer id) 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) manager.createQuery(
			    " FROM User u WHERE u.id=:id")
			    .setParameter("id", id)
			    .setMaxResults(1)
			    .getResultList();
		
		if(users.isEmpty())
			return null;
		else 
			return users.get(0);
	}

	// TODO : Doc & test
	@Override
	public User getUser(String mail) 
	{
		// TODO : Créer des requêtes préalablement (requêtes précompilées)
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) manager.createQuery(
			    " FROM User u WHERE u.mail=:mail")
			    .setParameter("mail", mail)
			    .setMaxResults(1)
			    .getResultList();
		
		if(users.isEmpty())
			return null;
		else 
			return users.get(0);
	}

}
