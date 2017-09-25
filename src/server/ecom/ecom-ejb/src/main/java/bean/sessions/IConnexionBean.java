package bean.sessions;

import java.util.List;

import javax.ejb.Remote;

import jobs.Connexion;

@Remote
public interface IConnexionBean {
	public Boolean loginUser(String login, String password);
	public Connexion getUser(Integer id);

	

}
