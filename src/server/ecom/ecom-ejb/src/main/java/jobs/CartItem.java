package jobs;

import org.json.JSONObject;

import jpa.entities.Bungalow;

public class CartItem implements IJsonSerializable
{
	private Bungalow bungalow;
	private Integer startWeek;
	private Integer endWeek;
	private Integer duration;
	
	public CartItem(Bungalow bungalow, Integer startWeek, Integer endWeek, Integer duration)
	{
		this.bungalow = bungalow;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
		this.duration = duration;
	}
	
	public Bungalow getBungalow() {
		return bungalow;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public Integer getEndWeek() {
		return endWeek;
	}

	public Integer getDuration() {
		return duration;
	}

	@Override
	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("bungalow", this.getBungalow().toJson());
		obj.put("startweek", this.getStartWeek());
		obj.put("endweek", this.getEndWeek());
		obj.put("duration", this.getDuration());
		
		return obj.toString();
	}

}
