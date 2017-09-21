package bean.sessions;

import jpa.entities.User;

public interface IUserBean 
{
	public User getUser(Integer id);
	public User getUser(String mail);
}
