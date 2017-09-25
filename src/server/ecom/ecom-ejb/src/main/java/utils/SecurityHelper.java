package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityHelper 
{
	public static String getMD5Hash(String toHash) throws NoSuchAlgorithmException
	{
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(StandardCharsets.UTF_8.encode(toHash));
		return String.format("%032x", new BigInteger(1, messageDigest.digest()));
	}
}
