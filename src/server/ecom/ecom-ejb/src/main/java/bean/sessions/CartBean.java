package bean.sessions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jobs.CartItem;
import jpa.entities.Bungalow;

@Stateful
public class CartBean implements ICartBean
{
	@PersistenceContext(unitName="primary")
    private EntityManager manager;
	
	private List<CartItem> items;

	@PostConstruct
	@Override
	public void initialize() {
		this.items = new ArrayList<CartItem>();
		
		// TODO : Test cart
		this.items.add(new CartItem(new Bungalow(2, 3, 400), 12, 13, 1));
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
