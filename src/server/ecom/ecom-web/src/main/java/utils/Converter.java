package utils;

public class Converter 
{
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
	
	public static String formatJson(String json)
    {
		return json.replace("\"{", "{").replace("}\"", "}").replace("\\", "");
    }
}
