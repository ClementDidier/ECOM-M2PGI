package jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.json.JSONObject;

import jobs.IJsonSerializable;

/**
 * Entity implementation class for Entity: Rent
 *
 */
@Entity @Table(name="RENTS")
public class Rent implements Serializable, IJsonSerializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RentId")
	private Integer rentId;
	
	@Column(name="BeginWeek")
	private Integer beginWeek;
	
	@Column(name="WeekCount")
	private Integer weekCount;
	
	@Column(name="Comment")
	private String comment;
	
	@Column(name="Note")
	private Integer note;
	
	@ManyToOne
	private Bungalow bungalow;
	
	public Rent() {
		super();
	}
	
	public Integer getId() {
		return rentId;
	}

	public Integer getBeginWeek() {
		return beginWeek;
	}

	public Integer getWeekCount() {
		return weekCount;
	}

	public String getComment() {
		return comment;
	}

	public Integer getNote() {
		return note;
	}

	public Bungalow getBungalow() {
		return bungalow;
	}

	@Override
	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("RentId", this.getId());
		obj.put("BeginWeek", this.getBeginWeek());
		obj.put("WeekCount", this.getWeekCount());
		obj.put("Comment", this.getComment());
		obj.put("Note", this.getNote());
		
		return obj.toString();
	}
   
}
