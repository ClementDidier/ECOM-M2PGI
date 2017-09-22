package jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity @Table(name="locations")
public class Location implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idLocation")
	private Integer idLocation;
	//semArrivee + l.nbrSemaines
	//bungalowId
	@Column(name="bungalowId")
	private Integer bungalowId;

	@Column(name="semArrivee")
	private Integer semArrivee;
	
	@Column(name="nbrSemaines")
	private Integer nbrSemaines;
	
	@Column(name="Avis")
	private String Avis;
	
	public Location()
	{
		super();
	}
	//idLocation bungalowId semArrivee nbrSemaines Avis

	public Location(int bungalowId, int semArrivee, Integer nbrSemaines)
	{
		super();
		this.bungalowId = bungalowId;
		this.nbrSemaines = nbrSemaines;
		this.semArrivee = semArrivee;
	}
	
	public Integer getId()
	{
		return this.idLocation;
	}

	public Integer getBungalowId()
	{
		return this.bungalowId;
	}
	
	public Integer getSemArrivee() 
	{
		return semArrivee;
	}

	public Integer getNbrSemaines() 
	{
		return nbrSemaines;
	}



	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("idLocation", this.getId());
		obj.put("bungalowId", this.getBungalowId());
		obj.put("semArrivee", this.getSemArrivee());
		obj.put("semArrivee", this.getNbrSemaines());
		
		return obj.toString();
	}
}
