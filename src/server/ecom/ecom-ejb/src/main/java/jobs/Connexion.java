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
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;

	
	public Connexion()
	{
		super();
	}
	
	public Connexion(String login, String password)
	{
		super();
		this.login = login;
		this.password = password;
	}
	
	public Integer getId()
	{
		return this.userId;
	}

	public String getLogin() 
	{
		return login;
	}

	public String getPassword() 
	{
		return password;
	}

	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("userId", this.getId());
		obj.put("login", this.getLogin());
		obj.put("password", this.getPassword());
		
		return obj.toString();
	}
}
