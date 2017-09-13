package bean.sessions;

import javax.ejb.Remote;

@Remote
public interface IWelcome
{
	public void initialize();
}
