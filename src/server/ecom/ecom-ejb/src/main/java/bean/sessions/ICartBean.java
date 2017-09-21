package bean.sessions;

import java.util.Collection;

import javax.ejb.Remote;

import jobs.CartItem;

@Remote
public interface ICartBean 
{
	public void initialize();
	public void addItem(CartItem item);
	public void removeItem(CartItem item);
	public Collection<CartItem> getContents();
	public void dispose();
}
