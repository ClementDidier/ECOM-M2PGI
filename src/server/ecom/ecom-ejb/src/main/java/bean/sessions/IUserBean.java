package bean.sessions;

import jobs.User;

public interface IUserBean 
{
	public User getUser(Integer id);
	public User getUser(String mail);
}
