package bean.sessions;

import java.util.Collection;

import javax.ejb.Remote;
import jobs.Bungalow;

@Remote
public interface ICartBean 
{
	public void initialize();
	public void addBungalow(Bungalow bungalow);
	public void removeBungalow(Bungalow bungalow);
	public Collection<Bungalow> getContents();
	public void dispose();
}
