package jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity @Table(name="BUNGALOWS")
public class Bungalow implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BungalowId")
	private Integer BungalowId;
	
	@Column(name="BedCount")
	private Integer BedCount;
	
	@Column(name="WeekPrice")
	private Integer WeekPrice;
	
	public Bungalow()
	{
		super();
	}
	
	public Bungalow(int bedCount, int price, Integer islandId)
	{
		super();
		this.BedCount = bedCount;
		this.WeekPrice = price;
	}
	
	public Integer getId()
	{
		return this.BungalowId;
	}

	public Integer getBedCount() 
	{
		return BedCount;
	}

	public Integer getPrice() 
	{
		return WeekPrice;
	}


	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("BungalowId", this.getId());
		obj.put("BedCount", this.getBedCount());
		obj.put("WeekPrice", this.getPrice());
		
		return obj.toString();
	}
}
