package jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TempOrder
 *
 */
@Entity @Table(name="TEMP_ORDERS")
public class TempOrder implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TempRentId")
	private Integer id;
	
	@Column(name="SessionId")
	private String sessionId;
	
	@Column(name="BungalowId")
	private Integer bungalowId;
	
	@Column(name="StartWeek")
	private Integer startWeek;
	
	@Column(name="EndWeek")
	private Integer endWeek;

	public TempOrder()
	{
		super();
	}
	
	public TempOrder(String sessionId, Integer bungalowId, Integer startWeek, Integer endWeek) 
	{
		super();
		this.sessionId = sessionId;
		this.bungalowId = bungalowId;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public Integer getBungalowId() {
		return bungalowId;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public Integer getEndWeek() {
		return endWeek;
	}
}
