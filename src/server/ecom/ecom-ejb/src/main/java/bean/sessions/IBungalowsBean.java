package bean.sessions;

import java.util.Collection;

import javax.ejb.Remote;

import jobs.Bungalow;

@Remote
public interface IBungalowsBean 
{
	public Collection<Bungalow> getBungalows();
	public Collection<Bungalow> getBungalows(Integer id);
	public Collection<Bungalow> getBungalows(Integer bedCount, Integer maxPrice, String ile);
}
