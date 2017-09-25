package jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity @Table(name="Connexion")
public class Connexion implements IJsonSerializable, Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UserId")
	private Integer UserId;
	
	@Column(name="Address")
	private String Address;
	
	@Column(name="Password")
	private String Password;

	
	public Connexion()
	{
		super();
	}
	
	public Connexion(String login, String password)
	{
		super();
		this.Address = login;
		this.Password = password;
	}
	
	public Integer getId()
	{
		return this.UserId;
	}

	public String getLogin() 
	{
		return Address;
	}

	public String getPassword() 
	{
		return Password;
	}

	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("UserId", this.getId());
		obj.put("Address", this.getLogin());
		obj.put("Password", this.getPassword());
		
		return obj.toString();
	}
}

	


