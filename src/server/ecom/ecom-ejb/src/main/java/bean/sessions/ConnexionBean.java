package bean.sessions;
import  java.security.MessageDigest;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.sasl.util.Charsets;

import com.google.common.hash.Hashing;

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

    public String getHash(String txt, String hashType) {
        try {
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
                    byte[] array = md.digest(txt.getBytes());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                 }
                    return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
                //error action
            }
            return null;
    }
    
	@Override
	public Boolean loginUser(String login, String password) {
		
		// TODO Auto-generated method stub
		String hashPassword = getHash(password, "MD5");
		@SuppressWarnings("unchecked")
		List<Connexion> connexion = (List<Connexion>) manager.createQuery(
			    " FROM users b WHERE b.mail=:login and b.password=:password")
			    .setParameter("mail", login)
			    .setParameter("password", hashPassword)
			    .setMaxResults(1)
			    .getResultList();
		
		if(!connexion.isEmpty())
			return true;
		else 
			return false;
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
