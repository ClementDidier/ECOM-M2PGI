package jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity @Table(name="RENTS")
public class Location implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RentId")
	private Integer RentId;
	//semArrivee + l.nbrSemaines
	//bungalowId
	@Column(name="BungalowId")
	private Integer BungalowId;

	@Column(name="BeginWeek")
	private Integer BeginWeek;
	
	@Column(name="WeekCount")
	private Integer WeekCount;
	
	@Column(name="Comment")
	private String Comment;
	
	@Column(name="Note")
	private String Note;
	
	public Location()
	{
		super();
	}
	//idLocation bungalowId semArrivee nbrSemaines Avis

	public Location(int bungalowId, int semArrivee, Integer nbrSemaines)
	{
		super();
		this.BungalowId = bungalowId;
		this.WeekCount = nbrSemaines;
		this.BeginWeek = semArrivee;
	}
	
	public Integer getId()
	{
		return this.RentId;
	}

	public Integer getBungalowId()
	{
		return this.BungalowId;
	}
	
	public Integer getSemArrivee() 
	{
		return BeginWeek;
	}

	public Integer getNbrSemaines() 
	{
		return WeekCount;
	}



	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("RentId", this.getId());
		obj.put("bungalowId", this.getBungalowId());
		obj.put("BeginWeek", this.getSemArrivee());
		obj.put("WeekCount", this.getNbrSemaines());
		
		return obj.toString();
	}
}
