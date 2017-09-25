package jobs;

import java.io.Serializable;
import javax.persistence.*;

import org.json.JSONObject;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity @Table(name="users")
public class User implements Serializable, IJsonSerializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="address")
	private String address;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="isAdmin")
	private boolean isAdmin;
	
	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName()
	{
		return lastname;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}
	
	public String getMail() {
		return mail;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public String toJson() 
	{
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("firstname", this.getFirstName());
		obj.put("lastname", this.getLastName());
		obj.put("address", this.getAddress());
		obj.put("password", this.getPassword());
		obj.put("mail", this.getMail());
		obj.put("phone", this.getPhone());
		obj.put("isAdmin", this.isAdmin());
		
		return obj.toString();
	}
}
