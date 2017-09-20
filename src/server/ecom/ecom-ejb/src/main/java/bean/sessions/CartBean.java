package bean.sessions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jobs.Bungalow;

@Stateful
public class CartBean implements ICartBean
{
	@PersistenceContext(unitName="primary")
    private EntityManager manager;
	
	private List<Bungalow> bungalows;
	
	public CartBean() 
	{
		
	}

	@Override
	public void initialize() {
		this.bungalows = new ArrayList<Bungalow>();
	}

	@Override
	public void addBungalow(Bungalow bungalow) {
		this.bungalows.add(bungalow);
	}

	@Override
	public void removeBungalow(Bungalow bungalow) {
		this.bungalows.remove(bungalow);	
	}

	@Override
	public Collection<Bungalow> getContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
