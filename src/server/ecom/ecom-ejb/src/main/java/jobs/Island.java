package jobs;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.json.JSONObject;

/**
 * Entity implementation class for Entity: Island
 *
 */
@Entity @Table(name="ISLANDS")
public class Island implements Serializable, IJsonSerializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IslandId")
	private Integer id;
	
	@Column(name="Wording")
	private String wording;
	
	@OneToMany
	private Collection<Bungalow> bungalows;
	
	public Island() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getWording() {
		return wording;
	}

	public Collection<Bungalow> getBungalows() {
		return bungalows;
	}

	@Override
	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("IslandId", this.getId());
		obj.put("Wording", this.getWording());
		
		return obj.toString();
	}
}
