package bean.sessions;

import java.util.Collection;

import javax.ejb.Remote;

import jpa.entities.Bungalow;

@Remote
public interface IBungalowsBean 
{
	public Collection<Bungalow> getBungalows();
	public Bungalow getBungalows(Integer id);
	public Bungalow getBungalowNotRented(Integer bungalowId, Integer startweek, Integer endweek);
	public Collection<Bungalow> getBungalows(Integer minbedcount, Integer islandid, Integer minprice, Integer maxprice,Integer startweek, Integer endweek);
}
