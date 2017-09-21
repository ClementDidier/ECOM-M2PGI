package bean.sessions;

import java.util.Collection;

import javax.ejb.Remote;

import jpa.entities.Bungalow;

@Remote
public interface IBungalowsBean 
{
	public Collection<Bungalow> getBungalows();
	public Bungalow getBungalows(Integer id);
	public Collection<Bungalow> getBungalows(Integer bedCount, Integer maxPrice, Integer islandId);
}
