package jpa.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONObject;
import jobs.IJsonSerializable;

@Entity @Table(name="BUNGALOWS")
public class Bungalow implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BungalowId")
	private Integer id;

	@Column(name="BedCount")
	private Integer bedCount;
	
	@Column(name="WeekPrice")
	private Integer price;
	
	@ManyToOne
	private Island island;
	
	@OneToMany
	private Collection<Rent> rents;
	
	public Bungalow()
	{
		super();
	}
	
	public Bungalow(Integer id, Integer bedCount, Integer price)
	{
		this.id = id;
		this.bedCount = bedCount;
		this.price = price;
	}
	
	public Integer getId()
	{
		return id;
	}

	public Integer getBedCount() 
	{
		return bedCount;
	}

	public Integer getPrice() 
	{
		return price;
	}

	public Island getIsland() {
		return island;
	}

	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("bungalowid", this.getId());
		obj.put("bedcount", this.getBedCount());
		obj.put("weekprice", this.getPrice());
		
		return obj.toString();
	}
}
