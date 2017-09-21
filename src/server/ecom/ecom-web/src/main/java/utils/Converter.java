package utils;

import java.util.Collection;

import org.json.JSONArray;

import jobs.IJsonSerializable;

public class Converter 
{
	private static final int SPACE_SIZE = 2;
	
	/**
	 * Obtient si possible la valeur entière de la chaîne de charactères paramètre, null dans le cas contraire
	 * @param parameter La chaîne de charactères paramètre
	 * @return La valeur entière si possible, null dans le cas contraire
	 */
	public static Integer getIntegerOf(String parameter)
	{
		if(parameter == null)
			return null;
		
		try {
			return Integer.parseInt(parameter);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String convertToJson(Collection<IJsonSerializable> serializables)
    {
    	JSONArray arrayResult = new JSONArray();
		if(serializables != null)
		{
			for(IJsonSerializable serializable : serializables)
				arrayResult.put(serializable.toJson());
		}
		
		return arrayResult.toString(SPACE_SIZE).replace("\"{", "{").replace("}\"", "}").replace("\\", "");
    }
}
