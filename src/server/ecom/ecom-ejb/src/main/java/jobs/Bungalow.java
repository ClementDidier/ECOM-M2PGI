package jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity @Table(name="bungalows")
public class Bungalow implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="bedCount")
	private Integer bedCount;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="islandName")
	private String islandName;
	
	public Bungalow()
	{
		super();
	}
	
	public Bungalow(int bedCount, int price, String islandName)
	{
		super();
		this.bedCount = bedCount;
		this.price = price;
		this.islandName = islandName;
	}
	
	public Integer getId()
	{
		return this.id;
	}

	public Integer getBedCount() 
	{
		return bedCount;
	}

	public Integer getPrice() 
	{
		return price;
	}

	public String getIslandName() 
	{
		return islandName;
	}

	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("bedcount", this.getBedCount());
		obj.put("price", this.getPrice());
		obj.put("islandname", this.getIslandName());
		
		return obj.toString();
	}
}
