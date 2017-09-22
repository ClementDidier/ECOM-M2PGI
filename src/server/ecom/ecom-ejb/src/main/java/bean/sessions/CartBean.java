package bean.sessions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jobs.CartItem;
import jpa.entities.Bungalow;
import jpa.entities.TempOrder;

@Stateful
public class CartBean implements ICartBean
{
	@PersistenceContext(unitName="primary")
    private EntityManager manager;
	
	@EJB(lookup="java:global/ecom-ear/ecom-ejb/BungalowsBean!bean.sessions.IBungalowsBean")
	private IBungalowsBean bungalowBean;
	
	private List<CartItem> items;

	@PostConstruct
	@Override
	public void initialize() {
		this.items = new ArrayList<CartItem>();
		this.items.add(new CartItem(new Bungalow(1, 2, 50), 201702, 201703, 1));
		this.items.add(new CartItem(new Bungalow(1, 2, 50), 201704, 201705, 1));
	}

	@Override
	public void addItem(CartItem item) {
		this.items.add(item);
	}

	@Override
	public void removeItem(CartItem item) {
		this.items.remove(item);	
	}

	@Override
	public Collection<CartItem> getContents() {
		return this.items;
	}
	
	@Override
	public void removeAllItems() {
		this.items.clear();
	}
	
	/**
	 * 
	 * @param sessionId La session de l'utilisteur
	 * @return
	 */
	@Override
	public List<CartItem> validate(String sessionId) 
	{
		List<CartItem> successfullyValidatedCarts = new ArrayList<CartItem>();
		synchronized(this)
		{
			for(CartItem item : this.getContents())
			{
				// On s'assure que le bungalow est toujours disponible
				Bungalow b = this.bungalowBean.getBungalowNotRented(item.getBungalow().getId(), item.getStartWeek(), item.getStartWeek());
				if(b != null)
				{
					this.manager.persist(new TempOrder(sessionId, b.getId(), item.getStartWeek(), item.getEndWeek()));
					successfullyValidatedCarts.add(item);
				}
			}
		}
		
		return successfullyValidatedCarts;
	}
	
	@Override @Remove
	public void dispose() {
		this.items.clear();
	}

	
}
