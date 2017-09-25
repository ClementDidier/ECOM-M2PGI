package jpa.entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.persistence.*;

import org.json.JSONObject;

import jobs.IJsonSerializable;
import utils.SecurityHelper;

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
	
	@Column(name="postal")
	private String postal;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="isAdmin")
	private boolean isAdmin;

	@Column(name="password")
	private String saltHashPassword;
	
	@Column(name="salt")
	private String salt;
	
	public User() {
		super();
	}

	public User(String firstname, String lastname, String address, String postal, String mail, String phone, String password) throws NoSuchAlgorithmException {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.postal = postal;
		this.mail = mail;
		this.phone = phone;
		this.salt = this.firstname + this.lastname + this.postal;
		this.saltHashPassword = SecurityHelper.getMD5Hash(password + this.salt);
		this.isAdmin = false;
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
		obj.put("mail", this.getMail());
		obj.put("phone", this.getPhone());
		obj.put("isAdmin", this.isAdmin());
		
		return obj.toString();
	}
}
