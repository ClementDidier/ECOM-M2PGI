package jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity @Table(name="bungalows")
public class Bungalow implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
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
	
	public String getName()
	{
		return this.name;
	}

	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		
		return obj.toString();
	}
}
