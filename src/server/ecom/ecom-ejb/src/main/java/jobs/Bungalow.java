package jobs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Bungalows")
public class Bungalow 
{
	@Id
	@Column(name="bungalowId")
	private String name;
	
	public Bungalow()
	{
		super();
	}
	
	public Bungalow(String name)
	{
		super();
		this.name = name;
	}
}
