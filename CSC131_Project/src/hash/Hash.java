package hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	
	String string;
	String algorithm;
	
	public Hash(String string, String algorithm) {
		this.string = string;
		this.algorithm = algorithm;
	}
	
	public static String calcString(String string, String algorithm) throws IOException, NoSuchAlgorithmException {
		//Choosing checksum algorith from MessageDigest
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		
		//Changes string to bytes and updates the bytes in the message digest
		digest.update(string.getBytes());
		
		//store digested digest into bytes
		byte[] bytes = digest.digest();
		//builds the string together
		StringBuilder sb = new StringBuilder();
		//Converts decimal into hexidecimal format and appends it to StringBuilder
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		//return String of StringBuilder
		return sb.toString();
	}
	
	public static String calcFile(String string, String algorithm) throws IOException, NoSuchAlgorithmException {
		//Choosing checksum algorith from MessageDigest
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		
		//Gets file path from String, turns the path into bytes, and updates in digest
		Path path = Paths.get(string);
		digest.update(Files.readAllBytes(path));
		
		//store digested digest into bytes
		byte[] bytes = digest.digest();
		//builds the string together
		StringBuilder sb = new StringBuilder();
		//Converts decimal into hexidecimal format and appends it to StringBuilder
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		//return String of StringBuilder
		return sb.toString();
	}

}
