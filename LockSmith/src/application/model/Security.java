package application.model;
 
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Group Project : LockSmith Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); Jonathan
 *          Villreal (zyj680); Pedro Jusino(ayt689) ; William G (Qoi678)
 *          
 *   This class takes care of the security concerns. It encrypts and decrypts the passwords
 *   in file as per requirement.
 *          
 *
 */
public abstract class Security {
	private static String salt = "ssshhhhhhhhhhh!!!!"; //used in AES
	
	/*
	 * uses MD5 algorithm to hash password entered without salt
	 * near carbon copy of https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#md5 
	 */
	public static String hash(String text)
	{
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(text.getBytes("UTF-8") );
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return(generatedPassword);
    }
	
	
	/*
	 * takes in a sting and key and returns encrypted text
	 * key should ideally be hashed master password
	 * direct copy of https://howtodoinjava.com/security/aes-256-encryption-decryption/
	 * Tried doing a blowish algorithm but that never worked
	 */
	public static String encryptS(String text, String key) throws Exception
	{
		
		try {
			
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	        
	        //Creating SecretKeySpec from key(argument)
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	        
	        //using key to encrypt via cipher
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
	        
	    }  catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	/*
	 * takes in a encrypted sting and key and returns decrypted text
	 * key should ideally be hashed master password
	 * direct copy of https://howtodoinjava.com/security/aes-256-encryption-decryption/
	 * Tried doing a blowish algorithm but that never worked
	 */
	public static String decryptS(String text, String key) throws Exception
	{
		
		String strData="";
		
		
		try {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	        
	        //Creating SecretKeySpec from key(argument)
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        //using key to decrypt via cipher
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
	        
	    }  catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	/*
	 * Takes in a path to a text file and a key to encrypt with
	 * Encrypts and saves the csv file
	 */
	static void encryptF(String filePath, String key )
	{
		try
		{
			
		String dataToWrite = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String enc = encryptS(line,key);
			dataToWrite = dataToWrite + enc + "\n";
			
		}
		br.close();
		
		File file = new File(filePath);
		FileWriter printer = new FileWriter(file, false);
		printer.write(dataToWrite);
		printer.close();
		
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
	}
	
	/*
	 * Takes in a path to a encrypted text file and a key to decrypt with
	 * decrypt and saves the csv file
	 */
	static void decryptF( String filePath, String key)
	{
		try
		{
			
		String dataToWrite = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String dec = decryptS(line,key);
			dataToWrite = dataToWrite + dec + "\n";
			
		}
		br.close();
		
		File file = new File(filePath);
		FileWriter printer = new FileWriter(file, false);
		printer.write(dataToWrite);
		printer.close();
		
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
	}
	
	/*
	 * Copies text to the system default clip board
	 * May not work on linux depending of what Java JDK is present
	 */
	public static void copyToClipboard(String text) {
		
		StringSelection selection = new StringSelection( text );
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		
		
	}
	
	
	

}